package com.mashibing.clientoptimization.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author xcy
 * @date 2023/3/2 - 14:21
 */
@Service
public class AsyncRequestService {


	private String msg = null;

	@Async
	public Future<String> getValue() throws InterruptedException {
		//检查数据变化的代码
		while (true) {
			synchronized (this) {
				if (msg != null) {
					String resultMsg = msg;
					msg = null;
					return new AsyncResult<>(resultMsg);
				}
			}
			Thread.sleep(100);
		}
	}

	public synchronized void postValue(String msg) {
		this.msg = msg;
	}
}
