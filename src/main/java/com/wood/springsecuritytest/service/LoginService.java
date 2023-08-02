package com.wood.springsecuritytest.service;

import com.wood.springsecuritytest.common.ResponseResult;
import com.wood.springsecuritytest.entities.User;

/**
 * @author 韩志雄
 * @date 2023/7/21 18:16
 */
public interface LoginService {
	ResponseResult login(User user);

	ResponseResult logout(User user);
}
