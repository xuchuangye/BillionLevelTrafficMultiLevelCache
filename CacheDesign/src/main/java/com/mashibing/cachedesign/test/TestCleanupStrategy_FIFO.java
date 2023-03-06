package com.mashibing.cachedesign.test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 测试数目阈值式清理机制的清理策略：FIFO（先进先出）
 * @author xcy
 * @date 2023/3/6 - 16:27
 */
public class TestCleanupStrategy_FIFO {
	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<>();
		for (int i = 0; i < 100; i++) {
			setCache(queue, i + "");
		}

	}

	public static void setCache(Queue<String> queue, String cache) {
		int size = queue.size();
		if (size >= 3) {
			queue.poll();
		}

		queue.add(cache);

		System.out.println("缓存中的值如下：");

		for (String q : queue) {
			System.out.println(q);
		}
	}
}
