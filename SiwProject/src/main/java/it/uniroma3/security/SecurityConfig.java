package it.uniroma3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private DataSource dataSource;
 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)

		.usersByUsernameQuery("SELECT username,password,1 FROM user_model where username=?")
		.authoritiesByUsernameQuery("SELECT username,ruolo FROM user_model JOIN ruolo_model ON user_model.ruolo_id=ruolo_model.id where username=?");
	}
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/successLogin")
        .failureUrl("/login?error=true");
        http.csrf().disable();
    }
    
//    @Bean
//	public PasswordEncoder passwordEncoder(){
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		return encoder;
//	}
}