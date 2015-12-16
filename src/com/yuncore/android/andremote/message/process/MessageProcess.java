/**
 * MessageProcess.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.message.process;

import java.lang.reflect.Constructor;
import java.net.URLEncoder;

import org.json.JSONObject;

import android.content.Context;

import com.yuncore.android.andremote.conf.AppConf;
import com.yuncore.android.andremote.http.HttpClient;
import com.yuncore.android.andremote.message.Message;
import com.yuncore.android.andremote.util.Log;

/**
 * The class <code>MessageProcess</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public abstract class MessageProcess<T extends Message> {

	static final String TAG = "MessageProcess";

	protected Context mContext;

	protected T message;

	private ProcessStatu statu = ProcessStatu.UNPROCESS;

	public synchronized ProcessStatu getStatu() {
		return statu;
	}

	public synchronized void setStatu(ProcessStatu statu) {
		this.statu = statu;
	}

	public synchronized T getMessage() {
		return message;
	}

	public synchronized void setMessage(T message) {
		this.message = message;
	}

	public Context getContext() {
		return mContext;
	}

	public void setContext(Context mContext) {
		this.mContext = mContext;
	}

	@SuppressWarnings("unchecked")
	public synchronized void setMessageJSON(JSONObject message) {
		final Class<? extends Message> clz = getMessageClass();
		try {
			final Constructor<? extends Message> constructor = clz
					.getConstructor(JSONObject.class);
			this.message = (T) constructor.newInstance(message);
		} catch (Exception e) {
			Log.e(TAG, "setMessageJSON error", e);
		}
	}

	protected abstract Class<? extends Message> getMessageClass();

	/**
	 * 上传消息
	 * 
	 * @return
	 */
	public boolean recevierMsg() {
		try {
			final String url = String.format("%s?action=receiver&msg=%s",
					AppConf.UPLOAD_SERVER, URLEncoder.encode(getMessage()
							.toJSON(new JSONObject()).toString(), "UTF-8"));
			Log.d(TAG, "upload msg:" + url);
			return new HttpClient().get(url);
		} catch (Exception e) {
			Log.e(TAG, "recevierMsg", e);
		}
		return false;
	}

	/**
	 * 处理消息
	 * 
	 * @param mContext
	 */
	public abstract boolean process();

	/**
	 * The class <code>ProcessStatu</code> 处理器的状态
	 * 
	 * @author Feng OuYang
	 * @version 1.0
	 */
	public enum ProcessStatu {
		/**
		 * 未处理
		 */
		UNPROCESS,
		/**
		 * 处理中
		 */
		PROCESSING,
		/**
		 * 处理完成
		 */
		FINISH
	}
}
