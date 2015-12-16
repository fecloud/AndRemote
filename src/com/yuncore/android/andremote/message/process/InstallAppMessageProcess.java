/**
 * InstallAppMessageProcess.java Created on 2015-12-16
 */
package com.yuncore.android.andremote.message.process;

import java.io.File;
import java.io.IOException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;

import com.yuncore.android.andremote.http.HttpClient;
import com.yuncore.android.andremote.message.InstallAppMessage;
import com.yuncore.android.andremote.message.Message;
import com.yuncore.android.andremote.util.Log;

/**
 * The class <code>InstallAppMessageProcess</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class InstallAppMessageProcess extends MessageProcess<InstallAppMessage> {

	static final String TAG = "InstallAppMessageProcess";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.process.MessageProcess#getMessageClass
	 * ()
	 */
	@Override
	protected Class<? extends Message> getMessageClass() {
		return InstallAppMessage.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.process.MessageProcess#process()
	 */
	@Override
	public boolean process() {
		if (null != message && message.getDlurl() != null) {
			try {
				if (downloadApp()) {
					message.setProcess("下载应用完成");
					recevierMsg();
					if (getApkInfo()) {
						message.setProcess("获取应用信息中完成");
						recevierMsg();
						if (reuqestInsatll()) {
							waitApkInstalled();
						}
					}
				}
			} catch (IOException e) {
				Log.e(TAG, "process error", e);
				message.setProcess("安装应用出现错误");
				recevierMsg();
			}
		} else {
			Log.e(TAG, "check param");
			message.setProcess("参数有错误");
			recevierMsg();
		}
		return true;
	}

	/**
	 * 下载apk
	 * 
	 * @return
	 * @throws IOException
	 */
	private boolean downloadApp() throws IOException {
		message.setProcess("下载应用中...");
		recevierMsg();
		final HttpClient client = new HttpClient();
		final String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "andremote"
				+ File.separator + System.currentTimeMillis() + ".apk";
		message.setLocalurl(path);
		Log.d(TAG, "get " + message.getDlurl() + " to " + path);
		return client.getfile(message.getDlurl(), path);
	}

	/**
	 * 取包名等
	 * 
	 * @return
	 */
	private boolean getApkInfo() {
		message.setProcess("获取应用信息中...");
		final String path = message.getLocalurl();
		final PackageManager pm = mContext.getPackageManager();
		final PackageInfo info = pm.getPackageArchiveInfo(path,
				PackageManager.GET_ACTIVITIES);
		if (info != null) {
			final ApplicationInfo appInfo = info.applicationInfo;
			final CharSequence applicationLabel = pm
					.getApplicationLabel(appInfo);
			if (applicationLabel != null) {
				final String appName = applicationLabel.toString();
				message.setAppName(appName);
			}
			final String packageName = appInfo.packageName; // 得到包名
			message.setPackageName(packageName);
			final String version = info.versionName; // 得到版本信息
			message.setVersion(version);
		}
		return true;
	}

	/**
	 * 拉起安装
	 * 
	 * @return
	 */
	private boolean reuqestInsatll() {
		message.setProcess("请求安装应用");
		Intent intent = new Intent();
		intent.setAction(android.content.Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(message.getLocalurl())),
				"application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(intent);
		return true;
	}

	/**
	 * 等用户确认安装
	 * 
	 * @return
	 */
	private boolean waitApkInstalled() {
		final ReceiverInstall receiverInstall = new ReceiverInstall();
		IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
		filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
		filter.addDataScheme("package");
		mContext.registerReceiver(receiverInstall, filter);

		synchronized (receiverInstall) {
			try {
				receiverInstall.wait(40000);
			} catch (InterruptedException e) {
			}
		}

		mContext.unregisterReceiver(receiverInstall);
		if (receiverInstall.install) {
			message.setProcess("安装应用成功");
		} else {
			message.setProcess("安装应用失败");
		}
		recevierMsg();
		return true;
	}

	private class ReceiverInstall extends BroadcastReceiver {

		private volatile boolean install;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * android.content.BroadcastReceiver#onReceive(android.content.Context,
		 * android.content.Intent)
		 */
		@Override
		public void onReceive(Context context, Intent intent) {
			String packageName = intent.getDataString().trim();
			String temp = "package:";
			if (packageName.startsWith(temp)) {
				packageName = packageName.substring(temp.length());
				if (packageName.equalsIgnoreCase(message.getPackageName())) {
					install = true;
					synchronized (this) {
						notifyAll();
					}
				}
			}
		}

	}
}
