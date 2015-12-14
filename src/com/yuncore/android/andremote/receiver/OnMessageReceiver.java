/**
 * OnMessageReceiver.java Created on 2015-12-14
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
 * The class <code>OnMessageReceiver</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class OnMessageReceiver extends BroadcastReceiver {

	static final String TAG = "OnMessageReceiver";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		final Bundle bundle = intent.getExtras();
		final String message = bundle.getString("Message");
		final String customContentString = bundle
				.getString("CustomContentString");
		Log.d(TAG, String.format("message:%s customContentString:%s", message,
				customContentString));
		if (message != null) {
			try {
				final JSONObject jsonObject = new JSONObject(message);
				MessageCenter.getInstance().addMessageJSON(jsonObject);
			} catch (JSONException e) {
			}
		}
	}

}
