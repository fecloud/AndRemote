/**
 * MainActivity.java Created on 2015-12-11
 */
package com.yuncore.android;

import com.yuncore.andremote.R;

import android.app.Activity;
import android.os.Bundle;

/**
 * The class <code>MainActivity</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class MainActivity extends Activity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);
	}

}
