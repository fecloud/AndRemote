/**
 * MessageThread.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.message.center;

import com.yuncore.android.andremote.message.process.MessageProcess;
import com.yuncore.android.andremote.message.process.MessageProcess.ProcessStatu;
import com.yuncore.android.andremote.util.Log;

/**
 * The class <code>MessageThread</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class MessageThread extends Thread {

	static final String TAG = "MessageThread";

	private boolean flag = true;

	private MessageContainer container;

	private boolean waiting = true;

	/**
	 * @param container
	 */
	public MessageThread(MessageContainer container) {
		super();
		this.container = container;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		Log.d(TAG, getName() + " run....");
		while (isFlag()) {
			setWaiting(false);
			final MessageProcess<?> task = container.getTask();
			if (task == null) {
				synchronized (this) {
					try {
						setWaiting(true);
						Log.d(TAG, getName() + " waiting...");
						wait();
					} catch (InterruptedException e) {
					}
				}
			} else {
				Log.d(TAG, getName() + " working...");
				final boolean process = task.process();
				if (process) {
					task.setStatu(ProcessStatu.FINISH);
					container.removeTask(task);
				} else {
					task.setStatu(ProcessStatu.UNPROCESS);
				}

			}
		}
		Log.d(TAG, getName() + " stop");
	}

	public synchronized boolean isFlag() {
		return flag;
	}

	public synchronized void setFlag(boolean flag) {
		this.flag = flag;
	}

	public synchronized boolean isWaiting() {
		return waiting;
	}

	private synchronized void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}

}
