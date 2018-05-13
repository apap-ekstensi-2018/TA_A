 package com.siasisten;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.and()
		.authorizeRequests()
		.antMatchers("/", "/css/**", "/js/**", "/images/**", "/new/**", "/asisten-dosen/**").permitAll()
		.antMatchers("/lowongan/view/**", "/lowongan/viewall", "/pengajuan/view/**", "/pengajuan/viewall"
				, "/mata-kuliah/**").hasAnyRole("mahasiswa", "dosen")
		.antMatchers("/lowongan/tambah", "/lowongan/ubah/**", "/lowongan/hapus/**", "/lowongan/review/**").hasRole("dosen")
		.antMatchers("/pengajuan/tambah", "/pengajuan/hapus/**").hasRole("mahasiswa")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/home")
		.permitAll()
		.and()
		.logout()
		.logoutUrl("/login?logout")
		.permitAll();
	}
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("select username, password, 1 from user_account where username=?")
			.authoritiesByUsernameQuery("select username, role from user_account where username=?");
	}
	
	/*@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = passwordEncoder();
		auth.inMemoryAuthentication()
			.withUser("admin").password(encoder.encode("admin"))
			.roles("ADMIN");
		auth.inMemoryAuthentication()
			.withUser("user").password(encoder.encode("user"))
			.roles("USER");
	}*/
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
