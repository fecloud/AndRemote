/**
 * BindMessage.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.message;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The class <code>BindMessage</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class BindMessage extends Message {

	private int errorCode;

	private String appid;

	private String userId;

	private String channelId;

	private String requestId;

	public BindMessage(JSONObject jsonObject) {
		super(jsonObject);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
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
		super.fromJSON(jsonObject);

		try {
			if (jsonObject.has("errorCode")) {
				errorCode = jsonObject.getInt("errorCode");
			}
			if (jsonObject.has("appid")) {
				appid = jsonObject.getString("appid");
			}
			if (jsonObject.has("userId")) {
				userId = jsonObject.getString("userId");
			}
			if (jsonObject.has("channelId")) {
				channelId = jsonObject.getString("channelId");
			}
			if (jsonObject.has("requestId")) {
				requestId = jsonObject.getString("requestId");
			}
		} catch (JSONException e) {
		}
	}

}
