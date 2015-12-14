/**
 * PackageInfoMessage.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.message;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The class <code>PackageInfoMessage</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class PackageInfoMessage extends Message {

	private String name;
	private String pkgName;
	private String versionName;
	private String versionCode;
	private String firstInstallTime;

	public PackageInfoMessage(JSONObject jsonObject) {
		super(jsonObject);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getFirstInstallTime() {
		return firstInstallTime;
	}

	public void setFirstInstallTime(String firstInstallTime) {
		this.firstInstallTime = firstInstallTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.Message#toJSON(org.json.JSONObject)
	 */
	@Override
	public JSONObject toJSON(JSONObject jsonObject) {
		if (null != pkgName) {
			try {
				jsonObject.put("name", name);
				jsonObject.put("pkgName", pkgName);
				jsonObject.put("versionName", versionName);
				jsonObject.put("versionCode", firstInstallTime);
			} catch (JSONException e) {
			}

		}
		return super.toJSON(jsonObject);
	}

}
