package com.wood.springsecuritytest.config.security.authenticationhandler;

import com.alibaba.fastjson.JSON;
import com.wood.springsecuritytest.common.ResponseResult;
import com.wood.springsecuritytest.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 韩志雄
 * @date 2023/7/23 17:38
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "认证失败请重新登录");
		String json = JSON.toJSONString(result);
		WebUtils.renderString(response,json);
	}
}
