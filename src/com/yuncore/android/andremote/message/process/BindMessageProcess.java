/**
 * BindMessageProcess.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.message.process;

import java.net.URLEncoder;

import org.json.JSONObject;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;

import com.yuncore.android.andremote.conf.AppConf;
import com.yuncore.android.andremote.http.HttpClient;
import com.yuncore.android.andremote.message.BindMessage;
import com.yuncore.android.andremote.message.Message;
import com.yuncore.android.andremote.util.Log;

/**
 * The class <code>BindMessageProcess</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class BindMessageProcess extends MessageProcess<BindMessage> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.process.MessageProcess#process()
	 */
	@Override
	public boolean process() {

		final SharedPreferences preferences = mContext.getSharedPreferences(
				mContext.getPackageName(), 0);
		try {
			final PackageInfo pInfo = mContext.getPackageManager()
					.getPackageInfo(mContext.getPackageName(), 0);
			getMessage().setErrorCode(preferences.getInt("errorCode", 0));
			getMessage().setAppid(preferences.getString("appid", ""));
			getMessage().setUserId(preferences.getString("userId", ""));
			getMessage().setChannelId(preferences.getString("channel_id", ""));
			getMessage().setRequestId(preferences.getString("requestId", ""));
			getMessage().setExt(
					"Android " + android.os.Build.VERSION.RELEASE + " , "
							+ android.os.Build.MODEL + " , "
							+ android.os.Build.VERSION.SDK + " , "
							+ mContext.getPackageName() + " , "
							+ pInfo.versionName);
			final String url = String.format("%s?action=receiver&msg=%s",
					AppConf.UPLOAD_SERVER, URLEncoder.encode(getMessage()
							.toJSON(new JSONObject()).toString(), "UTF-8"));
			Log.d(TAG, "upload msg:" + url);
			return new HttpClient().get(url);
		} catch (Exception e) {
			Log.w(getClass().getSimpleName(), e);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.process.MessageProcess#getMessageClass
	 * ()
	 */
	@Override
	protected Class<? extends Message> getMessageClass() {
		return BindMessage.class;
	}

}
