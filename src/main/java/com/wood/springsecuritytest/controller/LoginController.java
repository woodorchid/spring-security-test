package com.wood.springsecuritytest.controller;

import com.wood.springsecuritytest.common.ResponseResult;
import com.wood.springsecuritytest.entities.User;
import com.wood.springsecuritytest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 韩志雄
 * @date 2023/7/21 18:12
 */
@RestController
@RequestMapping()
public class LoginController {

	@Resource
	private LoginService loginService;

	@PostMapping("/user/login")
	public ResponseResult login(@RequestBody User user){
		return loginService.login(user);
	}

	@PostMapping("/user/logout")
	public  ResponseResult logout(@RequestBody User user){
		return loginService.logout(user);
	}

	@GetMapping("/hello")
	@PreAuthorize("hasAuthority('hello')")
	public String hello(){
		return "hello";
	}

	@GetMapping("/hello2")
	@PreAuthorize("hasAuthority('hello2')")
	public String hello2(){
		return "hello2";
	}
}

