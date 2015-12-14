/**
 * Message.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.message;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The class <code>Message</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public abstract class Message {

	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Message() {
	}

	public Message(JSONObject jsonObject) {
		fromJSON(jsonObject);
	}

	public void fromJSON(JSONObject jsonObject) {
		try {
			if (jsonObject.has("type")) {
				type = jsonObject.getInt("type");
			}
		} catch (JSONException e) {
		}
	}

	public JSONObject toJSON(JSONObject jsonObject) {
		try {
			jsonObject.put("type", type);
		} catch (JSONException e) {
		}
		return jsonObject;
	}

}
