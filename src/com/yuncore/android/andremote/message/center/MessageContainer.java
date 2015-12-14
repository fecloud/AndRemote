/**
 * MessageContainer.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.message.center;

import java.util.ArrayList;

import com.yuncore.android.andremote.message.process.MessageProcess;
import com.yuncore.android.andremote.message.process.MessageProcess.ProcessStatu;

/**
 * The class <code>MessageContainer</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class MessageContainer {

	private OnTaskAddedListener onTaskAddedListener;

	private ArrayList<MessageProcess<?>> tasks = new ArrayList<MessageProcess<?>>();

	public synchronized MessageProcess<?> getTask() {
		if (!tasks.isEmpty()) {
			for (MessageProcess<?> process : tasks) {
				if (process != null
						&& process.getStatu() == ProcessStatu.UNPROCESS) {
					process.setStatu(ProcessStatu.PROCESSING);
					return process;
				}
			}
		}
		return null;
	}

	public synchronized void addTask(MessageProcess<?> process) {
		if (process != null && process.getMessage() != null) {
			tasks.add(process);
			if (onTaskAddedListener != null) {
				onTaskAddedListener.onTaskAdded();
			}
		}
	}

	public synchronized void removeTask(MessageProcess<?> process) {
		if (null != process) {
			tasks.add(process);
		}
	}

	public synchronized OnTaskAddedListener getOnTaskAddedListener() {
		return onTaskAddedListener;
	}

	public synchronized void setOnTaskAddedListener(
			OnTaskAddedListener onTaskAddedListener) {
		this.onTaskAddedListener = onTaskAddedListener;
	}

	public interface OnTaskAddedListener {

		public void onTaskAdded();
	}

}
