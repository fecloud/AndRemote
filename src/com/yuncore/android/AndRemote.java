/**
 * AndRemote.java Created on 2015-12-14
 */
package com.yuncore.android;

import android.content.Context;

/**
 * The class <code>AndRemote</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public interface AndRemote {

	/**
	 * 初始化
	 * 
	 * @param mContext
	 */
	void init(Context mContext);

	/**
	 * 销毁
	 * 
	 * @param mContext
	 */
	void destory(Context mContext);

}
