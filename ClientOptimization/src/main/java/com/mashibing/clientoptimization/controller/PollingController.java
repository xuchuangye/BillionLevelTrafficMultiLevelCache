package com.mashibing.clientoptimization.controller;

import com.mashibing.clientoptimization.service.AsyncRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 测试长轮询
 *
 * @author xcy
 * @date 2023/3/2 - 14:18
 */
@RestController
@EnableAsync
public class PollingController {

	@Autowired
	private AsyncRequestService asyncRequestService;

	@GetMapping("/value")
	public String longPolling() {
		String msg = null;
		Future<String> result = null;
		try {
			result = asyncRequestService.getValue();
			msg = result.get(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.cancel(true);
			}
		}

		return msg;
	}

	@PostMapping("/value")
	public void postValue(String msg) {
		asyncRequestService.postValue(msg);
	}
}
