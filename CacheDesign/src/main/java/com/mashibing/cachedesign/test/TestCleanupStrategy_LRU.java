package com.mashibing.cachedesign.test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 测试数目阈值式清理机制的清理策略：LRU（最近最少使用）
 *
 * @author xcy
 * @date 2023/3/6 - 16:28
 */
public class TestCleanupStrategy_LRU {
	public static void main(String[] args) {
		/**
		 * 构造一个空的<tt>LinkedHashMap<tt>实例，具有指定的初始容量、负载因子和排序模式。
		 * @param initialCapacity 初始容量
		 * @param loadFactor 负载因子
		 * @param accessOrder 排序模式：true对应访问顺序，false对应插入顺序
		 */
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(5, 0.75F, true) {
			@Override
			protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
				//LinkedHashMap的容量大于等于5时，再插入新的元素时就删除旧的元素
				return this.size() >= 5;
			}
		};

		map.put("1", "aa");
		map.put("2", "bb");
		map.put("3", "cc");
		map.put("4", "dd");

		System.out.println("原始顺序：");
		print(map);

		//最近访问的元素，key是2
		String key = "2";
		System.out.println("最近访问的元素是" + key);
		map.get(key);
		print(map);

		//最近访问的元素，key是3
		key = "3";
		System.out.println("最近访问的元素是" + key);
		map.get(key);
		print(map);


		map.put("5", "ee");
		System.out.println("加入新的元素");
		print(map);
	}

	public static void print(LinkedHashMap<String, String> source) {
		source.keySet().iterator().forEachRemaining(System.out::println);
	}
}
