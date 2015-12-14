/**
 * BindMessageProcess.java Created on 2015-12-14
 */
package com.yuncore.android.andremote.message.process;

import com.yuncore.android.andremote.message.BindMessage;
import com.yuncore.android.andremote.message.Message;

/**
 * The class <code>BindMessageProcess</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class BindMessageProcess extends MessageProcess<BindMessage> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.process.MessageProcess#process()
	 */
	@Override
	public boolean process() {

		final String bind_param = mContext.getSharedPreferences(
				mContext.getPackageName(), 0).getString("bind_param", null);
		if (null != bind_param)
			System.out.println(bind_param);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yuncore.android.andremote.message.process.MessageProcess#getMessageClass
	 * ()
	 */
	@Override
	protected Class<? extends Message> getMessageClass() {
		return BindMessage.class;
	}

}
