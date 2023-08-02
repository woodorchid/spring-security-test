package com.wood.springsecuritytest.config.security;

import com.wood.springsecuritytest.entities.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 韩志雄
 * @date 2023/7/23 17:59
 * 在SPEL表达式中使用 @ex相当于获取容器中bean的名字未ex的对象。然后再调用这个对象的hasAuthority方法
 */

@Component("ex")
public class SGExpressionRoot {

	public boolean hasAuthority(String authority){
		//获取当前用户的权限
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		List<String> permissions = loginUser.getPermissions();
		//判断用户权限集合中是否存在authority
		return permissions.contains(authority);
	}
}
