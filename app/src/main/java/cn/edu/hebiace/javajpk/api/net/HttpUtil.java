package cn.edu.hebiace.javajpk.api.net;

import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Http引擎处理类
 *
 */
public class HttpUtil {
	private final static String TAG = "HttpUtil";
	private final static String BASE_URL="http://www.lpwxs.cn:8080/MovieServer/";
	private final static String REQUEST_MOTHOD = "POST";
	private final static String ENCODE_TYPE = "UTF-8";
	private final static int TIME_OUT = 20000;
	private String serverUrl="";

	private static HttpUtil instance = null;

	private HttpUtil() {

	}

	public static HttpUtil getInstance() {
		if (instance == null) {
			instance = new HttpUtil();
		}

		return instance;
	}

	/**
	 * 请求网络数据i
	 * @param paramsMap must have "method" parameter and not ""
	 * @return
	 * @throws IOException
     */
	public  <T> T postHandle(Map<String, String> paramsMap, Type typeOfT) throws IOException {
		System.err.println("joinParams before:::::::::::::::::::::::::");
		String data = joinParams(paramsMap);
		System.err.println("joinParams after;;;;;;;;;;;;;;;;;;;;;;;;;;");
		System.out.println("parameter length="+String.valueOf(data.getBytes().length));
		// 打印出请求
		HttpURLConnection connection = getConnection();
		connection.setRequestProperty("Content-Length", String.valueOf(data.getBytes().length));
		connection.connect();
		OutputStream os = connection.getOutputStream();
		os.write(data.getBytes());
		os.flush();
		if (connection.getResponseCode() == 200) {
			// 获取响应的输入流对象
			InputStream is = connection.getInputStream();
			// 创建字节输出流对象
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 定义读取的长度
			int len = 0;
			// 定义缓冲区
			byte buffer[] = new byte[1024];
			// 按照缓冲区的大小，循环读取
			while ((len = is.read(buffer)) != -1) {
				// 根据读取的长度写入到os对象中
				baos.write(buffer, 0, len);
			}
			// 释放资源
			is.close();
			baos.close();
			connection.disconnect();
			// 返回字符串
			final String result = new String(baos.toByteArray());
			Log.e(TAG,result);
			System.out.println("result^&^"+result);
			// 打印出结果
			 Gson gson = new Gson();
			 return gson.fromJson(result, typeOfT);
//			return result;
		} else {
			connection.disconnect();
			return null;
		}
	}

	// 获取connection
	private HttpURLConnection getConnection() {
		HttpURLConnection connection = null;
		// 初始化connection
		try {
			// 根据地址创建URL对象
			URL url = new URL(serverUrl);
			// 根据URL对象打开链接
			connection = (HttpURLConnection) url.openConnection();
			// 设置请求的方式
			connection.setRequestMethod(REQUEST_MOTHOD);
			// 发送POST请求必须设置允许输入，默认为true
			connection.setDoInput(true);
			// 发送POST请求必须设置允许输出
			connection.setDoOutput(true);
			// 设置不使用缓存
			connection.setUseCaches(false);
			// 设置请求的超时时间
			connection.setReadTimeout(TIME_OUT);
			connection.setConnectTimeout(TIME_OUT);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Connection", "keep-alive");
			 connection.setRequestProperty("Response-Type", "json");
			connection.setChunkedStreamingMode(0);
		} catch (IOException e) {
			System.out.println("连接失败");
		}
		return connection;
	}

	// 拼接参数列表
	private String joinParams(Map<String, String> paramsMap) {
		serverUrl = "";
		if(paramsMap==null||paramsMap.isEmpty()){
			System.out.println("serverUrl="+serverUrl);
			return "";
		}
		// 不需要参数列表，所以返回 ""
		if(paramsMap.size()==1){
			//拼接url
			serverUrl = BASE_URL+paramsMap.get("method");
			System.out.println("serverUrl="+serverUrl);
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : paramsMap.keySet()) {
			if(key.equals("method")){
				serverUrl = BASE_URL+paramsMap.get("method");
				continue;
			}

			stringBuilder.append(key);
			stringBuilder.append("=");
			try {
				stringBuilder.append(URLEncoder.encode(String.valueOf(paramsMap.get(key)), ENCODE_TYPE));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			stringBuilder.append("&");
		}
		System.out.println("serverUrl="+serverUrl);
		System.out.println(stringBuilder.toString());

		return stringBuilder.substring(0, stringBuilder.length() - 1);
	}
}
