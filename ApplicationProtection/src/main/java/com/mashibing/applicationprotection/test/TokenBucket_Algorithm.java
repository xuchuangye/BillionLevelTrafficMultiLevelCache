package com.mashibing.applicationprotection.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 令牌桶算法
 *
 * @author xcy
 * @date 2023/3/9 - 14:23
 */
public class TokenBucket_Algorithm {
	// 令牌桶的容量----同时表示该controller的同一时间的并发量
	private int capacity = 10;

	// 令牌桶
	private AtomicInteger tokenBucket = new AtomicInteger(0);

	// 每秒钟产生的令牌数量
	private int tokenNum;

	public void doWhile() throws InterruptedException {
		// 多少毫秒产生一个令牌
		int forNum = 1000 / tokenNum;
		while (true) {
			tokenBucket.set(0);
			while (forNum < 1000) {
				Thread.sleep(forNum);
				forNum += forNum;
				tokenBucket.getAndAdd(1);
			}
		}
	}

	public synchronized boolean getToken() {
		if (tokenBucket.get() <= 0) {
			return false;
		}

		tokenBucket.decrementAndGet();

		return true;
	}

	public static void main(String[] args) {
		System.out.println((((System.currentTimeMillis() - 100) / 1000) * 2));
		System.out.println(System.currentTimeMillis() - 100);
		System.out.println((System.currentTimeMillis() - 100) / 1000);

	}
}
