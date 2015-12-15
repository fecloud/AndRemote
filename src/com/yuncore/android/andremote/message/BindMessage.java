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

	private String requestId;
	
	private String ext;

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

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
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
			if (jsonObject.has("requestId")) {
				requestId = jsonObject.getString("requestId");
			}
			if (jsonObject.has("ext")) {
				ext = jsonObject.getString("ext");
			}
		} catch (JSONException e) {
		}
	}
	
	/* (non-Javadoc)
	 * @see com.yuncore.android.andremote.message.Message#toJSON(org.json.JSONObject)
	 */
	@Override
	public JSONObject toJSON(JSONObject jsonObject) {
		try {
			jsonObject.put("errorCode", errorCode);
			jsonObject.put("appid", appid);
			jsonObject.put("userId", userId);
			jsonObject.put("requestId", requestId);
			jsonObject.put("ext", ext);
		} catch (JSONException e) {
		}
		return super.toJSON(jsonObject);
	}

}
