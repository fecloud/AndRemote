/**
 * PackageInfoMessageProcess.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.message.process;

import org.json.JSONObject;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.yuncore.android.andremote.message.Message;
import com.yuncore.android.andremote.message.PackageInfoMessage;

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
		PackageInfo pInfo;
		try {
			pInfo = mContext.getPackageManager().getPackageInfo(pkName, 0);
			message.setName(pkName);
			message.setPkgName(pkName);
			message.setVersionCode(String.valueOf(pInfo.versionCode));
			message.setVersionName(pInfo.versionName);
			message.setFirstInstallTime(String.valueOf(pInfo.firstInstallTime));
			System.out.println(message.toJSON(new JSONObject()).toString());
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return true;
	}

}
