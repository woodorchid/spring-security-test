package com.wood.springsecuritytest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 韩志雄
 * @date 2023/7/10 17:23
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/add")
	public String hello(){
		return "Hello world";
	}
}
