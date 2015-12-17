/**
 * ToastMessage.java Created on 2015-12-16
 */
package com.yuncore.android.andremote.message;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The class <code>ToastMessage</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ToastMessage extends Message {

	private String toast;

	private int duration = 0;

	private String process;

	public ToastMessage(JSONObject jsonObject) {
		fromJSON(jsonObject);
	}

	public String getToast() {
		return toast;
	}

	public void setToast(String toast) {
		this.toast = toast;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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
			if (jsonObject.has("toast")) {
				toast = jsonObject.getString("toast");
			}
			if (jsonObject.has("duration")) {
				duration = jsonObject.getInt("duration");
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
			if (null != toast)
				jsonObject.put("toast", toast);
			jsonObject.put("duration", duration);
			if (null != process)
				jsonObject.put("process", process);
		} catch (JSONException e) {
		}
		return super.toJSON(jsonObject);
	}

}
