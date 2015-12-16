/**
 * PackageInfoMessageProcess.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.message.process;

import android.content.pm.PackageInfo;

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
			return recevierMsg();
		} catch (Exception e) {
			Log.w(getClass().getSimpleName(), e);
		}
		return false;
	}

}
