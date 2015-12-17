/**
 * MessageCenter.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.message.center;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;

import com.yuncore.android.andremote.message.center.MessageContainer.OnTaskAddedListener;
import com.yuncore.android.andremote.message.process.BindMessageProcess;
import com.yuncore.android.andremote.message.process.InstallAppMessageProcess;
import com.yuncore.android.andremote.message.process.MessageProcess;
import com.yuncore.android.andremote.message.process.MessageProcessType;
import com.yuncore.android.andremote.message.process.PackageInfoMessageProcess;
import com.yuncore.android.andremote.message.process.ToastMessageProcess;
import com.yuncore.android.andremote.util.Log;

/**
 * The class <code>MessageCenter</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class MessageCenter implements OnTaskAddedListener {

	static final String TAG = "MessageCenter";

	private Context mContext;

	private MessageContainer container = new MessageContainer();

	private static final MessageCenter MESSAGE_CONTAINER = new MessageCenter();

	private ArrayList<MessageThread> messageThreads = new ArrayList<MessageThread>();

	public static synchronized void init(Context mContext) {
		MESSAGE_CONTAINER.mContext = mContext;
	}

	public static synchronized MessageCenter getInstance() {
		return MESSAGE_CONTAINER;
	}

	private MessageCenter() {
		container.setOnTaskAddedListener(this);
		startMessageThread(1);
	}

	public synchronized void addMessageJSON(JSONObject jsonObject) {
		Log.d(TAG, "addMessageJSON");
		if (jsonObject.has("type")) {
			try {
				final int type = jsonObject.getInt("type");
				MessageProcess<?> process = null;
				switch (type) {
				case MessageProcessType.BIND:
					process = new BindMessageProcess();
					break;
				case MessageProcessType.PACKAGEINFO:
					process = new PackageInfoMessageProcess();
					break;
				case MessageProcessType.INSTALLAPP:
					process = new InstallAppMessageProcess();
					break;
				case MessageProcessType.TOAST:
					process = new ToastMessageProcess();
					break;
				default:
					Log.d(TAG, "not found message process");
					break;
				}
				if (null != process) {
					process.setContext(mContext);
					process.setMessageJSON(jsonObject);
					container.addTask(process);
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.e(TAG, "addMessageJSON parse json error");
			}
		}
	}

	private void startMessageThread(int num) {
		MessageThread thread = null;
		for (int i = 0; i < num; i++) {
			thread = new MessageThread(container);
			messageThreads.add(thread);
			thread.start();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yuncore.android.andremote.message.center.MessageContainer.
	 * OnTaskAddedListener#onTaskAdded()
	 */
	@Override
	public synchronized void onTaskAdded() {
		for (MessageThread once : messageThreads) {
			if (once.isWaiting()) {
				synchronized (once) {
					once.notifyAll();
				}
			}
		}
	}

}
