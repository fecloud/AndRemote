/**
 * HttpClient.java Created on 2015-12-15
 */
package com.yuncore.android.andremote.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.yuncore.android.andremote.conf.AppConf;
import com.yuncore.android.andremote.util.Log;

/**
 * The class <code>HttpClient</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class HttpClient {

	static final String TAG = "HttpClient";

	public static final int timeout = 20000;

	/**
	 * 上传数据
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public boolean get(String url) throws IOException {
		final HttpURLConnection conn = (HttpURLConnection) new URL(url)
				.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(false);
		conn.setConnectTimeout(timeout);
		conn.setReadTimeout(timeout);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent", AppConf.USER_AGENT);
		conn.setUseCaches(false);
		conn.connect();

		if (conn.getResponseCode() == 200) {
			final InputStream in = conn.getInputStream();
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			final byte[] buffer = new byte[1024];
			int len = -1;
			while (-1 != (len = in.read(buffer))) {
				out.write(buffer, 0, len);
			}
			in.close();
			conn.disconnect();
			final String string = new String(out.toByteArray(), "UTF-8");
			try {
				final JSONObject jsonObject = new JSONObject(string);
				if (jsonObject.has("code")) {
					final int code = jsonObject.getInt("code");
					if (code != 200) {
						return false;
					}
				}
			} catch (JSONException e) {
				throw new IOException(e);
			}

			Log.d(TAG, "get http result:" + string);
		} else{
			Log.d(TAG, "get http result code:" + conn.getResponseCode());
			return false;
		}
		return true;
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public boolean getfile(String url, String path) throws IOException {
		final HttpURLConnection conn = (HttpURLConnection) new URL(url)
				.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(false);
		conn.setConnectTimeout(timeout);
		conn.setReadTimeout(timeout);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent", AppConf.USER_AGENT);
		conn.setUseCaches(false);
		conn.connect();

		if (conn.getResponseCode() == 200) {
			final File file = new File(path);

			file.getParentFile().mkdirs();

			final InputStream in = conn.getInputStream();
			final FileOutputStream out = new FileOutputStream(file);
			final byte[] buffer = new byte[1024];
			int len = -1;
			while (-1 != (len = in.read(buffer))) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.flush();
			out.close();
			conn.disconnect();
			Log.d(TAG, "save file to :" + path);
			
			if (file.length() > 0) {
				return true;
			}

		} else{
			Log.d(TAG, "save file http result code:" + conn.getResponseCode());
			return false;
		}
		return true;
	}

}
