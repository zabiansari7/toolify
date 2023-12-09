package de.srh.toolify.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import de.srh.toolify.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> {
				auth.requestMatchers(AntPathRequestMatcher.antMatcher("/public/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/private/**")).authenticated()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/private/admin/**")).hasRole("ADMIN")
					.requestMatchers(AntPathRequestMatcher.antMatcher("/v2/api-docs")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/configuration/ui")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-resources/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/configuration/security")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/swagger-ui.html")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/webjars/**")).permitAll()
					.anyRequest().authenticated();
			})
			.formLogin(form -> form.loginPage("http://localhost:8081/login").permitAll())
			.logout(logout -> {
				logout.logoutUrl("/logout").permitAll();
				logout.logoutSuccessUrl("/login?logout").permitAll();
				logout.invalidateHttpSession(true);
				logout.deleteCookies("JSESSIONID");               
			})
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.build();
	}
	
	
	@Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

}
