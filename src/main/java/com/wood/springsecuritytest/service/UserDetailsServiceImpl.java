package com.wood.springsecuritytest.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wood.springsecuritytest.entities.LoginUser;
import com.wood.springsecuritytest.entities.User;
import com.wood.springsecuritytest.mapper.MenuMapper;
import com.wood.springsecuritytest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author 韩志雄
 * @date 2023/7/21 18:08
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private MenuMapper menuMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//根据用户名查询用户信息
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(User::getUserName,username);
		User user = userMapper.selectOne(wrapper);
		//如果查询不到数据就通过抛出异常来给出提示
		if(Objects.isNull(user)){
			throw new RuntimeException("用户名或密码错误");
		}
		List<String> permissionKeyList =  menuMapper.selectPermsByUserId(user.getId());
		//封装成UserDetails对象返回
		return new LoginUser(user,permissionKeyList);
	}
}

