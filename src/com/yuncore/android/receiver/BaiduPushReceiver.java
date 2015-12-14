/**
 * BaiduPushReceiver.java Created on 2015-12-11
 */
package com.yuncore.android.receiver;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;

/**
 * The class <code>BaiduPushReceiver</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class BaiduPushReceiver extends PushMessageReceiver {

	public static final String TAG = BaiduPushReceiver.class.getSimpleName();

	public static final String ACTION_ONBIND = "com.yuncore.andremote.action.OnBind";

	public static final String ACTION_ONMESSAGE = "com.yuncore.andremote.action.OnMessage";

	/**
	 * 调用PushManager.startWork后，sdk将对push
	 * server发起绑定请求，这个过程是异步的。绑定请求的结果通过onBind返回。 如果您需要用单播推送，需要把这里获取的channel
	 * id和user id上传到应用server中，再调用server接口用channel id和user id给单个手机或者用户推送。
	 * 
	 * @param context
	 *            BroadcastReceiver的执行Context
	 * @param errorCode
	 *            绑定接口返回值，0 - 成功
	 * @param appid
	 *            应用id。errorCode非0时为null
	 * @param userId
	 *            应用user id。errorCode非0时为null
	 * @param channelId
	 *            应用channel id。errorCode非0时为null
	 * @param requestId
	 *            向服务端发起的请求id。在追查问题时有用；
	 * @return none
	 */
	@Override
	public void onBind(Context mContext, int errorCode, String appid,
			String userId, String channelId, String requestId) {
		Log.d(TAG, "onBind");
		final Bundle bundle = new Bundle();
		bundle.putInt("ErrorCode", errorCode);
		bundle.putString("AppId", appid);
		bundle.putString("UserId", userId);
		bundle.putString("ChannelId", channelId);
		bundle.putString("RequestId", requestId);

		Intent intent = new Intent(ACTION_ONBIND);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
	}

	/**
	 * 接收透传消息的函数。
	 * 
	 * @param context
	 *            上下文
	 * @param message
	 *            推送的消息
	 * @param customContentString
	 *            自定义内容,为空或者json字符串
	 */
	@Override
	public void onMessage(Context mContext, String message,
			String customContentString) {
		
		Log.d(TAG, "onMessage");
		final Bundle bundle = new Bundle();
		bundle.putString("Message", message);
		bundle.putString("CustomContentString", customContentString);

		Intent intent = new Intent(ACTION_ONMESSAGE);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);

	}

	/**
	 * 接收通知点击的函数。
	 * 
	 * @param context
	 *            上下文
	 * @param title
	 *            推送的通知的标题
	 * @param description
	 *            推送的通知的描述
	 * @param customContentString
	 *            自定义内容，为空或者json字符串
	 */
	@Override
	public void onNotificationClicked(Context context, String title,
			String description, String customContentString) {
	}

	/**
	 * 接收通知到达的函数。
	 * 
	 * @param context
	 *            上下文
	 * @param title
	 *            推送的通知的标题
	 * @param description
	 *            推送的通知的描述
	 * @param customContentString
	 *            自定义内容，为空或者json字符串
	 */

	@Override
	public void onNotificationArrived(Context context, String title,
			String description, String customContentString) {
	}

	/**
	 * setTags() 的回调函数。
	 * 
	 * @param context
	 *            上下文
	 * @param errorCode
	 *            错误码。0表示某些tag已经设置成功；非0表示所有tag的设置均失败。
	 * @param successTags
	 *            设置成功的tag
	 * @param failTags
	 *            设置失败的tag
	 * @param requestId
	 *            分配给对云推送的请求的id
	 */
	@Override
	public void onSetTags(Context context, int errorCode,
			List<String> sucessTags, List<String> failTags, String requestId) {
	}

	/**
	 * delTags() 的回调函数。
	 * 
	 * @param context
	 *            上下文
	 * @param errorCode
	 *            错误码。0表示某些tag已经删除成功；非0表示所有tag均删除失败。
	 * @param successTags
	 *            成功删除的tag
	 * @param failTags
	 *            删除失败的tag
	 * @param requestId
	 *            分配给对云推送的请求的id
	 */
	@Override
	public void onDelTags(Context context, int errorCode,
			List<String> sucessTags, List<String> failTags, String requestId) {
	}

	/**
	 * listTags() 的回调函数。
	 * 
	 * @param context
	 *            上下文
	 * @param errorCode
	 *            错误码。0表示列举tag成功；非0表示失败。
	 * @param tags
	 *            当前应用设置的所有tag。
	 * @param requestId
	 *            分配给对云推送的请求的id
	 */
	@Override
	public void onListTags(Context context, int errorCode, List<String> tags,
			String requestId) {
	}

	/**
	 * PushManager.stopWork() 的回调函数。
	 * 
	 * @param context
	 *            上下文
	 * @param errorCode
	 *            错误码。0表示从云推送解绑定成功；非0表示失败。
	 * @param requestId
	 *            分配给对云推送的请求的id
	 */
	@Override
	public void onUnbind(Context context, int errorCode, String requestId) {
	}

}
