/**
 * OnBindReceiver.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.receiver;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yuncore.android.andremote.message.center.MessageCenter;
import com.yuncore.android.andremote.util.Log;

/**
 * The class <code>OnBindReceiver</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class OnBindReceiver extends BroadcastReceiver {

	static final String TAG = "OnBindReceiver";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context mContext, Intent intent) {
		final Bundle bundle = intent.getExtras();
		final int errorCode = bundle.getInt("ErrorCode");
		final String appid = bundle.getString("AppId");
		final String userId = bundle.getString("UserId");
		final String channelId = bundle.getString("ChannelId");
		final String requestId = bundle.getString("RequestId");

		final long time = System.currentTimeMillis();
		Log.d(TAG,
				String.format(
						"errorCode:%s appid:%s userId:%s channelId:%s requestId:%s time:%s",
						errorCode, appid, userId, channelId, requestId, time));
		mContext.getSharedPreferences(mContext.getPackageName(), 0).edit()
		.putInt("errorCode", errorCode)
		.putString("appid", appid)
		.putString("userId", userId)
		.putString("channel_id",channelId)
		.putString("requestId", requestId).commit();

		try {
			final JSONObject jsonObject = new JSONObject();
			jsonObject.put("type", 1);
			jsonObject.put("identify", System.currentTimeMillis());
			MessageCenter.getInstance().addMessageJSON(jsonObject);
		} catch (JSONException e) {
		}

	}

}
