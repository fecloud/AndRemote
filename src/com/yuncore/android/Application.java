/**
 * Application.java Created on 2015-12-11
 */
package com.yuncore.android;

import android.content.Intent;
import android.util.Log;

import com.yuncore.android.service.BaiduPushService;

/**
 * The class <code>Application</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class Application extends android.app.Application {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		new DemoThread().start();
	}

	private class DemoThread extends Thread {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			Log.e(DemoThread.class.getSimpleName(), "thread start");
			while (true) {
				try {
					final Intent service = new Intent(getApplicationContext(),
							BaiduPushService.class);
					startService(service);
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
			}
		}

	}

}
