package com.mashibing.applicationprotection.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 漏桶算法
 *
 * @author xcy
 * @date 2023/3/9 - 14:10
 */
public class LeakyBucket_Algorithm {

	/**
	 * 桶的容量
	 */
	private final int capacity = 100;

	/**
	 * 桶中的队列长度，初始化的时候的空的桶
	 */
	private AtomicInteger water = new AtomicInteger(0);

	/**
	 * 水滴的流出的速率,这个可以在 构造方法种设置,比如每秒10个请求
	 */
	private int outRate;

	/**
	 * 记录上次成功接收到请求的时间
	 * 用于计算当前系统时间减去上次请求时间 乘以outRate 所处理的请求数.
	 */
	private long receivedTime;

	/**
	 * 判断该controller是否能继续接收请求
	 *
	 * @return true表示可以处理该请求，false表示不能处理该请求，该桶已经满了
	 */
	public boolean acquire() {
		//如果是空桶，那么直接添加进一个桶
		if (water.get() == 0) {
			receivedTime = System.currentTimeMillis();

			water.addAndGet(1);
			return true;
		}

		//先计算下上成功接受到当前时间已经流出的记录数
		int outNum = (int) ((System.currentTimeMillis() - receivedTime) / 1000 * outRate);
		int waterLeft = water.get() - outNum;
		water.set(Math.max(0, waterLeft));

		//重新更新leakTimeStamp
		//outNum是否大于0
		if (outNum > 0) {
			receivedTime = System.currentTimeMillis();
		}

		//尝试加水，并且水还未满
		if (water.get() < capacity) {
			water.addAndGet(1);
			return true;
		}else {
			//水已满，拒绝加水
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println((((System.currentTimeMillis() - 100) / 1000) * 2));
		System.out.println(System.currentTimeMillis() - 100);
		System.out.println((System.currentTimeMillis() - 100) / 1000);
	}
}
