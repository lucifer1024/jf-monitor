package org.proxy.utils.cache;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CacheUtils {
	// 队列大小
	public static final int QUEUE_MAX_SIZE = 1000;
	// 阻塞队列
	private static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>(QUEUE_MAX_SIZE);

	/**
	 * 消息入队
	 * 
	 * @param alarmMessageVO
	 * @return
	 */
	public static boolean push(String msg) {
		boolean bool = blockingQueue.offer(msg);
		return bool;
	}

	/**
	 * 消息出队
	 * 
	 * @return
	 */
	public static String poll() {
		String result = null;
		try {
			result = blockingQueue.take();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
