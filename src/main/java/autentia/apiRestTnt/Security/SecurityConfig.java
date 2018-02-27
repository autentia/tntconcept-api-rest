package autentia.apiRestTnt.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
/*import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;*/
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//          .withUser("admin").password("admin")
//          .authorities("ROLE_USER");
//    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.ldapAuthentication()
			.userSearchBase("ou=users")
			.userSearchFilter("(uid={0})")
			.groupSearchBase("ou=groups")
			.groupSearchFilter("member={0}")
			.contextSource()
			.root("o=autentia")
			.ldif("classpath:users.ldif");
	}
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    		http.authorizeRequests().anyRequest().authenticated();
    		http.httpBasic();
    		//http.csrf().disable();
    		http.logout();
    }

}
