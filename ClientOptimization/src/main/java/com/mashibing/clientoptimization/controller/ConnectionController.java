package com.mashibing.clientoptimization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 测试长连接和短连接
 * @author xcy
 * @date 2023/3/2 - 11:23
 */
@RestController
public class ConnectionController {

	@GetMapping("/test-long-connection")
	public String longConnection(HttpServletResponse response) {
		response.setHeader("connection", "keep-alive");
		return "长连接";
	}

	@GetMapping("/test-short-connection")
	public String shortConnection(HttpServletResponse response) {
		response.setHeader("connection", "close");
		return "短连接";
	}
}
