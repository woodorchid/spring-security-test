package com.wood.springsecuritytest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wood.springsecuritytest.entities.User;
import com.wood.springsecuritytest.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringSecurityTestApplicationTests {

	@Autowired
	public UserMapper userMapper;
	@Test
	public void testUserMapper(){
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(User::getUserName,"sg");
		User user = userMapper.selectOne(wrapper);
		System.out.println(user);
	}

	@Test
	void contextLoads() {
	}

}
