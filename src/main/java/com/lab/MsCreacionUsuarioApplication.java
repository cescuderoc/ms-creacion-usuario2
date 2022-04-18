package com.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lab.security.JWTAuthorizationFilter;

@SpringBootApplication
public class MsCreacionUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCreacionUsuarioApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
	        .and().csrf().ignoringAntMatchers("/h2-console/**")
	        .and().headers().frameOptions().sameOrigin();
			
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/obtenerToken").permitAll()
				.anyRequest().authenticated();
		}
	}

}
