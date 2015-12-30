package com.cardprototype.bootstrap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.anyRequest().permitAll()
		.and()
		.rememberMe().rememberMeServices(rememberMeServices());
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication();
	}

	@Bean
	public RememberMeServices rememberMeServices() {
		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("password", userDetailsService());
		rememberMeServices.setCookieName("oceans_grave_remember");
		rememberMeServices.setParameter("rememberMe");
		return rememberMeServices;
	}
}