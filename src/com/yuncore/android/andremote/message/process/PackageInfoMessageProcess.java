/**
 * PackageInfoMessageProcess.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.message.process;

import java.net.URLEncoder;

import org.json.JSONObject;

import android.content.pm.PackageInfo;

import com.yuncore.android.andremote.conf.AppConf;
import com.yuncore.android.andremote.http.HttpClient;
import com.yuncore.android.andremote.message.Message;
import com.yuncore.android.andremote.message.PackageInfoMessage;
import com.yuncore.android.andremote.util.Log;

/**
 * The class <code>PackageInfoMessageProcess</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class PackageInfoMessageProcess extends
		MessageProcess<PackageInfoMessage> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.process.MessageProcess#getMessageClass
	 * ()
	 */
	@Override
	protected Class<? extends Message> getMessageClass() {
		return PackageInfoMessage.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.process.MessageProcess#process()
	 */
	@Override
	public boolean process() {
		final String pkName = mContext.getPackageName();
		try {
			final PackageInfo pInfo = mContext.getPackageManager()
					.getPackageInfo(pkName, 0);
			message.setName(mContext.getApplicationInfo()
					.loadLabel(mContext.getPackageManager()).toString());
			message.setPkgName(pkName);
			message.setVersionCode(String.valueOf(pInfo.versionCode));
			message.setVersionName(pInfo.versionName);
			message.setFirstInstallTime(String.valueOf(pInfo.firstInstallTime));
			final String url = String.format("%s?action=receiver&msg=%s",
					AppConf.UPLOAD_SERVER, URLEncoder.encode(
							message.toJSON(new JSONObject()).toString(),
							"UTF-8"));
			Log.d(TAG, "upload msg:" + url);
			return new HttpClient().get(url);
		} catch (Exception e) {
			Log.w(getClass().getSimpleName(), e);
		}

		return false;
	}

}
