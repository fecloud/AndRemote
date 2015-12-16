/**
 * InstallAppMessage.java Created on 2015-12-16
 */
package com.yuncore.android.andremote.message;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The class <code>InstallAppMessage</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class InstallAppMessage extends Message {

	private String dlurl;

	private String localurl;

	private String appName;

	private String packageName;

	private String version;

	private String process;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public InstallAppMessage(JSONObject jsonObject) {
		super(jsonObject);
	}

	public String getDlurl() {
		return dlurl;
	}

	public void setDlurl(String dlurl) {
		this.dlurl = dlurl;
	}

	public String getLocalurl() {
		return localurl;
	}

	public void setLocalurl(String localurl) {
		this.localurl = localurl;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.Message#fromJSON(org.json.JSONObject
	 * )
	 */
	@Override
	public void fromJSON(JSONObject jsonObject) {
		try {
			if (jsonObject.has("dlurl")) {
				dlurl = jsonObject.getString("dlurl");
			}
		} catch (JSONException e) {
		}
		super.fromJSON(jsonObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.Message#toJSON(org.json.JSONObject)
	 */
	@Override
	public JSONObject toJSON(JSONObject jsonObject) {
		try {
			if (null != dlurl)
				jsonObject.put("dlurl", dlurl);
			if (null != localurl)
				jsonObject.put("localurl", localurl);
			if (null != appName)
				jsonObject.put("appName", appName);
			if (null != packageName)
				jsonObject.put("packageName", packageName);
			if (null != version)
				jsonObject.put("version", version);
			if (null != process)
				jsonObject.put("process", process);
		} catch (JSONException e) {
		}
		return super.toJSON(jsonObject);
	}

}
