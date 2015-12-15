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
	
	private String identify;
	
	private String channel_id;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getChannelId() {
		return channel_id;
	}

	public void setChannelId(String channel_id) {
		this.channel_id = channel_id;
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
			if (jsonObject.has("identify")) {
				identify = jsonObject.getString("identify");
			}
			if (jsonObject.has("channel_id")) {
				channel_id = jsonObject.getString("channel_id");
			}
		} catch (JSONException e) {
		}
	}

	public JSONObject toJSON(JSONObject jsonObject) {
		try {
			jsonObject.put("type", type);
			jsonObject.put("identify", identify);
			jsonObject.put("channel_id", channel_id);
		} catch (JSONException e) {
		}
		return jsonObject;
	}

}
