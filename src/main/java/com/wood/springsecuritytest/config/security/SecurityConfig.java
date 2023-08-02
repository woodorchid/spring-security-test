package com.wood.springsecuritytest.config.security;

import com.wood.springsecuritytest.config.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;

/**
 * @author 韩志雄
 * @date 2023/7/21 18:11
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource
	private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

	//认证成功处理器
	@Resource
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	//认证失败处理器
	@Resource
	private AuthenticationFailureHandler authenticationFailureHandler;

	/**
	 * 异常处理相关
	 */
	//认证失败处理器
	@Resource
	private AuthenticationEntryPoint authenticationEntryPoint;
	//访问拒绝处理器
	@Resource
	private AccessDeniedHandler accessDeniedHandler;

	//登出成功处理器
	@Resource
	private LogoutSuccessHandler logoutSuccessHandler;
	@Bean
	public PasswordEncoder passwordEncoder(){
		return  NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				//把token校验过滤器添加到过滤器链中
				.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
				//关闭csrf
				.csrf().disable()
				//不通过Session获取SecurityContext
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				// 对于登录接口 允许匿名访问
				.antMatchers("/user/login").anonymous()
				// 除上面外的所有请求全部需要鉴权认证
				.anyRequest().authenticated();

		http.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint).
				accessDeniedHandler(accessDeniedHandler);
		//允许跨域
		http.cors();
		http.formLogin()
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler);
		http.logout()
				//配置注销成功处理器
				.logoutSuccessHandler(logoutSuccessHandler);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}

