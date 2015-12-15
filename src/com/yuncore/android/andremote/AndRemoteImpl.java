/**
 * AndRemoteImpl.java Created on 2015-12-14
 */
package com.yuncore.android.andremote;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.yuncore.android.AndRemote;
import com.yuncore.android.andremote.conf.AppConf;
import com.yuncore.android.andremote.message.center.MessageCenter;
import com.yuncore.android.andremote.receiver.OnBindReceiver;
import com.yuncore.android.andremote.receiver.OnMessageReceiver;
import com.yuncore.android.andremote.util.Log;

/**
 * The class <code>AndRemoteImpl</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class AndRemoteImpl implements AndRemote {

	static final String TAG = "AndRemoteImpl";
	/**
	 * 绑定推送成功
	 */
	public static final String ACTION_ONBIND = "com.yuncore.andremote.action.OnBind";

	/**
	 * 透传消息推送成功
	 */
	public static final String ACTION_ONMESSAGE = "com.yuncore.andremote.action.OnMessage";

	private Context mContext;

	private OnBindReceiver onBindReceiver;

	private OnMessageReceiver onMessageReceiver;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yuncore.android.AndRemote#init(android.content.Context)
	 */
	@Override
	public void init(Context mContext) {
		this.mContext = mContext;
		setEnv();
		MessageCenter.init(mContext);
		register();
	}

	private void register() {
		Log.d(TAG, "register receivers");
		onBindReceiver = new OnBindReceiver();
		mContext.registerReceiver(onBindReceiver, new IntentFilter(
				ACTION_ONBIND));

		onMessageReceiver = new OnMessageReceiver();
		mContext.registerReceiver(onMessageReceiver, new IntentFilter(
				ACTION_ONMESSAGE));
	}

	private void setEnv() {

		try {
			final PackageInfo pInfo = mContext.getPackageManager()
					.getPackageInfo(mContext.getPackageName(), 0);
			final String useragent = "Android "
					+ android.os.Build.VERSION.RELEASE + " , " + android.os.Build.MODEL
					+ " , " + android.os.Build.VERSION.SDK + " , "
					+ mContext.getPackageName() + " , " + pInfo.versionName;
			AppConf.USER_AGENT = useragent;
		} catch (NameNotFoundException e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yuncore.android.AndRemote#destory(android.content.Context)
	 */
	@Override
	public void destory(Context mContext) {
		this.mContext.unregisterReceiver(onBindReceiver);
		this.mContext.unregisterReceiver(onMessageReceiver);
	}

}
