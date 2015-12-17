/**
 * ToastMessageProcess.java Created on 2015-12-16
 */
package com.yuncore.android.andremote.message.process;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.Toast;

import com.yuncore.android.andremote.message.Message;
import com.yuncore.android.andremote.message.ToastMessage;

/**
 * The class <code>ToastMessageProcess</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ToastMessageProcess extends MessageProcess<ToastMessage> {

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			final int duration = msg.what;
			final String toast = (String) msg.obj;
			Toast.makeText(mContext.getApplicationContext(), toast, duration).show();
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.process.MessageProcess#getMessageClass
	 * ()
	 */
	@Override
	protected Class<? extends Message> getMessageClass() {
		return ToastMessage.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.process.MessageProcess#process()
	 */
	@Override
	public boolean process() {
		if (message.getToast() != null) {

			handler.sendMessage(handler.obtainMessage(message.getDuration(), 0,
					0, message.getToast()));
			message.setProcess("显示完成");
			recevierMsg();
		}
		return true;
	}

}
