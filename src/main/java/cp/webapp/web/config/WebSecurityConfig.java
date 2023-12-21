package cp.webapp.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/specialization").permitAll()
				.antMatchers("/map").permitAll()
				.antMatchers("/htw").permitAll()
				.antMatchers("/filter/**").permitAll()
				.antMatchers("/item/new").authenticated()
				.antMatchers("/item/*/edit").authenticated()
				.antMatchers("/item/*/delete").hasRole("ADMIN")
				.antMatchers("/item/**").permitAll()
				.antMatchers("/item/search").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/fonts/**").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/scss/**").permitAll()
				.antMatchers("/login", "/register").permitAll()
				.antMatchers("/items/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login") // TODO: inserisci la pagina di errore di login
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/");
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customUserDetailService);
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());

		auth.authenticationProvider(authProvider);
	}

}