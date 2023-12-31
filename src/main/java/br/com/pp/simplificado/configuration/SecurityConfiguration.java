package br.com.pp.simplificado.configuration;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.pp.simplificado.filter.SecurityFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
	@Autowired
	private SecurityFilter securityFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    	/*
    	 PRECISA ESTAR AUTENTICADO
    	 http://localhost:8080/user/all
    	 http://localhost:8080/transaction/all
    	 http://localhost:8080/transaction/save
    	 
    	 PRECISA SER DA ROLE ADMIN MAS NÃO PRECISA ESTAR AUTENTICADO
    	 http://localhost:8080/user/save
    	 
    	 NÃO PRECISA ESTAR AUTENTICADO
    	 http://localhost:8080/user/login
    	 * */
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                		.requestMatchers(antMatcher(HttpMethod.POST, "/user/login")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.POST, "/user/save")).hasAnyRole(
                        		Constants.ROLE_ADMIN, 
                        		Constants.ROLE_ADMIN.toUpperCase(), 
                        		Constants.ROLE_ADMIN.toLowerCase())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .getOrBuild();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
