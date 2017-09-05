package me.momarija.bioui.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("daoAuthenticationProvider")
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private Environment environment;

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService) {

		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers("/webjars/**", "/css/**", "/js/**", "/files/**", "/**/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").authenticated()
				.antMatchers("/admin/**").hasAuthority("Admin")
				.antMatchers("/gerant/**").hasAnyAuthority("Gerant", "Admin")
				.antMatchers("/user/**").hasAuthority("User")
				.and()
				.formLogin().loginPage("/login").successForwardUrl("/").permitAll()
				.and()
				.logout().permitAll();

		//Configutations for dev mode (default profile)
		//H2 Security Config
		if (environment.getActiveProfiles().length == 0) {
			http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
			http.csrf().ignoringAntMatchers("/h2-console/**");
			http.headers().frameOptions().disable();
		}
	}
}
