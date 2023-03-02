package com.mashibing.clientoptimization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 测试页面缓存
 *
 * @author xcy
 * @date 2023/3/2 - 15:28
 */
@RestController
public class PageCacheController {

	@GetMapping("/test-nocache")
	public String testNoCache(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		return "测试页面没有缓存";
	}

	@GetMapping("/test-cache")
	public String testCache(HttpServletResponse response) {
		response.setHeader("Cache-Control", "public,max-age=315360000");
		return "测试页面有缓存";
	}
}
