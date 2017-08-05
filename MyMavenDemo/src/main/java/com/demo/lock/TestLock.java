package com.demo.lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestLock {
	public static void main(String[] args) {
		final TestLock testLock = new TestLock();
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100; i++) {
			service.execute(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					StringBuffer sBuffer = new StringBuffer();
					boolean ext=testLock.doSomething("1001", sBuffer);
					System.out.println(ext+"\t"+sBuffer.toString());
				}
			});
		}
		service.shutdown();
	}

	public Boolean doSomething(String departmentId, StringBuffer message) {
		// 同一个部门同时只能有一个处理, 不同部门可以并行处理
		String lockName = "doSomething_" + departmentId;
		Boolean result;
		if (LockStore.getLock(lockName)) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				LockStore.releaseLock(lockName);
				result = true;
			}
		} else {
			Date lastLockDate = LockStore.getLockDate(lockName);
			String messageStr = "您所在的部门已经在处理中, 启动时间为:" + getDateDetailDesc(lastLockDate);
			message.append(messageStr);
			result = false;
		}

		return result;
	}

	/*
	 * 获取日期的具体时间描述
	 */
	private String getDateDetailDesc(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(date);
	}
}
