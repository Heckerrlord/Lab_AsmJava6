package com.poly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.poly.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userService;

	/* Mã hóa mật khẩu */
	@Bean
	BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
	}

	/* Quản lý dữ liệu người sử dụng */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	/* Phân quyền sử dụng và hình thức đăng nhập */
	protected void configure(HttpSecurity http) throws Exception {
		// CSRF, CORS
		http.csrf().disable().cors().disable();
		// Phân quyền sử dụng
		http.authorizeRequests().antMatchers("/rest/authorities", "/rest/authorities/**").hasAnyRole("DIRE", "STAF")
				.anyRequest().permitAll(); // anonymous
		http.httpBasic();
		// Điều khiển lỗi truy cập không đúng role
		http.exceptionHandling().accessDeniedPage("/auth/access/denied"); // error
		// Đăng nhập
		http.formLogin().loginPage("/auth/login/form").loginProcessingUrl("/auth/login") // login
				.defaultSuccessUrl("/auth/login/success", true).failureUrl("/auth/login/error")
				.usernameParameter("username") // username
				.passwordParameter("password"); // password
		http.rememberMe().rememberMeParameter("remember"); // remember me
		// Đăng xuất
		http.logout().logoutUrl("/auth/logoff").logoutSuccessUrl("/auth/logoff/success");

		// OAuth2- Đăng nhâp từ mang xã hôi
		http.oauth2Login().loginPage("/auth/login/form").defaultSuccessUrl("/oauth2/login/success", true)
				.failureUrl("/auth/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");
	}
}
