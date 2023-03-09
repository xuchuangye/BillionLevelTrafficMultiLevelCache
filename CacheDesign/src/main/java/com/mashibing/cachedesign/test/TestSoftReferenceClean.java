package com.mashibing.cachedesign.test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 测试软引用清理
 *
 * @author xcy
 * @date 2023/3/7 - 7:50
 */
public class TestSoftReferenceClean {
	public static void main(String[] args) {
		soft();
	}

	public static void soft() {
		HashMap<Integer, SoftRefedStudent> map = new HashMap<>();
		ReferenceQueue<Student> queue = new ReferenceQueue<>();

		int i = 0;
		while (i < 10000000) {
			Student student = new Student();
			map.put(i, new SoftRefedStudent(i, student, queue));

			SoftRefedStudent pollRef = (SoftRefedStudent) queue.poll();
			if (pollRef != null) {
				System.out.println("回收：" + pollRef.key);
				map.remove(pollRef.key);

				System.out.println(i + "新一轮============================");
				Iterator<Map.Entry<Integer, SoftRefedStudent>> iterator = map.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<Integer, SoftRefedStudent> next = iterator.next();
					if ((int) next.getValue().key == pollRef.key) {
						System.out.println("出错了");
					}
				}
				System.out.println(i + "新一轮========================");
			}
			i++;
		}
	}

}

class Student {

}

class SoftRefedStudent extends SoftReference<Student> {

	public Integer key;

	public SoftRefedStudent(Integer key, Student referent, ReferenceQueue<? super Student> q) {
		super(referent, q);
		this.key = key;
	}
}
