package de.srh.toolify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests(auth -> {
				auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/admin/**")).hasRole("ADMIN")
					.anyRequest().hasRole("USER");
				//auth.requestMatchers(AntPathRequestMatcher.antMatcher("/admin/**")).hasRole("ADMIN");
			})
			.formLogin(form -> form.loginPage("/login").permitAll())
			.build();
	}


}
