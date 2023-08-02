package com.wood.springsecuritytest.config.security.authenticationhandler;

import com.alibaba.fastjson.JSON;
import com.wood.springsecuritytest.common.ResponseResult;
import com.wood.springsecuritytest.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 韩志雄
 * @date 2023/7/23 17:37
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "权限不足");
		String json = JSON.toJSONString(result);
		WebUtils.renderString(response,json);

	}
}



