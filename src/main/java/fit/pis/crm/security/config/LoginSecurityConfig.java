package fit.pis.crm.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import fit.pis.crm.security.config.UserAuthenticationSuccessHandler;

@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserAuthenticationSuccessHandler authHandler;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select email, password, enabled from useraccount where email=?")
		.authoritiesByUsernameQuery("select email, role from useraccount where email=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()	
		.antMatchers("/resources**").permitAll()
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER')")
		.antMatchers("/supervisor/**").access("hasRole('ROLE_SUPERVISOR')")
		.and().formLogin().loginPage("/login").successHandler(authHandler).permitAll()
		.failureUrl("/login?error").permitAll()
		.usernameParameter("email").passwordParameter("password")
		.and().logout()
		.logoutSuccessUrl("/login?logout").permitAll()
		.and().exceptionHandling().accessDeniedPage("/403");;
	}
}
