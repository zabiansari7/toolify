package de.srh.toolify.config;

import de.srh.toolify.filters.AccessTokenValidationFilter;
import de.srh.toolify.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private ToolifySuccessAuthenticationHandler toolifySuccessAuthenticationHandler;
	@Autowired
	private ToolifyFailureAuthenticationHandler toolifyFailureAuthenticationHandler;
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(authenticationProvider());
		return authenticationManagerBuilder.build();
	}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity

			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> {
				auth.requestMatchers(AntPathRequestMatcher.antMatcher("/public/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/private/**")).authenticated()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/private/admin/**")).hasAuthority("ADMIN")
					.requestMatchers(AntPathRequestMatcher.antMatcher("/error/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui.html")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui/**")).permitAll()
					.anyRequest().permitAll();
			})
				.addFilterBefore(accessTokenValidationFilter(), UsernamePasswordAuthenticationFilter.class)
				//.exceptionHandling(basic -> basic.authenticationEntryPoint(customAuthenticationEntryPoint()))
				.exceptionHandling(e -> e.accessDeniedPage("/error/accessdenied"))
			.formLogin(form -> form.loginPage("http://localhost:8081/login").permitAll()
					.successHandler(toolifySuccessAuthenticationHandler)
					.failureHandler(toolifyFailureAuthenticationHandler)
			)
			.logout(logout -> {
				logout.logoutUrl("/logout").permitAll();
				logout.logoutSuccessUrl("/login?logout").permitAll();
				logout.invalidateHttpSession(true);
				logout.deleteCookies("JSESSIONID");               
			})
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
			.build();


	}

	@Bean
	public AccessTokenValidationFilter accessTokenValidationFilter(){
		return new AccessTokenValidationFilter();
	}

}

