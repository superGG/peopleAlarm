package com.xhwl.mwc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;


/**
 * 用于模拟HTTP请求中GET/POST方式 
 * @author landa
 *
 */
public class HttpUtils {  
    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @return
     */
    public static JSONObject httpPost(String url,JSONObject jsonParam){
        //post请求返回结果
        //DefaultHttpClient httpClient = new DefaultHttpClient();
    	System.out.println("访问URL"+url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 201) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                    System.out.println("post请求提交失败");
                }
            }
        } catch (IOException e) {
        	System.out.println("post请求提交失败");
        }
        return jsonResult;
    }
    
    /**
     * hppts （必须使用域名）
     * @author Kellan_Song
     * @param url
     * @param jsonParam
     * @return
     */
    public static JSONObject httpsPost(String url,JSONObject jsonParam){
        //post请求返回结果
    	System.out.println("访问URL"+url);
        CloseableHttpClient httpClient = null;
		try {
			httpClient = new SSLClient().build();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 201) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                    System.out.println("post请求提交失败");
                }
            }
        } catch (IOException e) {
        	System.out.println("post请求提交失败");
        }
        return jsonResult;
    }
    
    /**
     * https的post请求 (必须使用域名)
     * @author Kellan_Song
     * @param url 访问路径
     * @param map 参数键值对
     * @return
     */
	public static String httpsPost(String url, Map<String,String> map){
    	String charset = "utf-8";
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient().build();
            httpPost = new HttpPost(url);  
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }  
    
	/** 
	 * 发送GET请求 
	 *  
	 * @param url 
	 *            目的地址 
	 * @param parameters 
	 *            请求参数，Map类型。 
	 * @return 远程响应结果 
	 */  
	public static String sendGet(String url, Map<String, String> parameters) { 
		String result="";
		BufferedReader in = null;// 读取响应输入流  
		StringBuffer sb = new StringBuffer();// 存储参数  
		String params = "";// 编码之后的参数
		try {
			// 编码请求参数  
			if(parameters.size()==1){
				for(String name:parameters.keySet()){
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(parameters.get(name),  
									"UTF-8"));
				}
				params=sb.toString();
			}else{
				for (String name : parameters.keySet()) {  
					sb.append(name).append("=").append(  
							java.net.URLEncoder.encode(parameters.get(name),  
									"UTF-8")).append("&");  
				}  
				String temp_params = sb.toString();  
				params = temp_params.substring(0, temp_params.length() - 1);  
			}
			String full_url = url + "?" + params; 
			System.out.println(full_url); 
			// 创建URL对象  
			java.net.URL connURL = new java.net.URL(full_url);  
			// 打开URL连接  
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL  
					.openConnection();  
			// 设置通用属性  
			httpConn.setRequestProperty("Accept", "*/*");  
			httpConn.setRequestProperty("Connection", "Keep-Alive");  
			httpConn.setRequestProperty("User-Agent",  
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");  
			// 建立实际的连接  
			httpConn.connect();  
			// 响应头部获取  
			Map<String, List<String>> headers = httpConn.getHeaderFields();  
			// 遍历所有的响应头字段  
			for (String key : headers.keySet()) {  
				System.out.println(key + "\t：\t" + headers.get(key));  
			}  
			// 定义BufferedReader输入流来读取URL的响应,并设置编码方式  
			in = new BufferedReader(new InputStreamReader(httpConn  
					.getInputStream(), "UTF-8"));  
			String line;  
			// 读取返回的内容  
			while ((line = in.readLine()) != null) {  
				result += line;  
			}  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {  
				if (in != null) {  
					in.close();  
				}  
			} catch (IOException ex) {  
				ex.printStackTrace();  
			}  
		}
		return result ;
	}  
	

	/** 
	 * 发送POST请求 
	 *  
	 * @param url 
	 *            目的地址 
	 * @param parameters 
	 *            请求参数，Map类型。 
	 * @return 远程响应结果 
	 */  
	public static String sendPost(String url, Map<String, String> parameters) {  
		String result = "";// 返回的结果  
		BufferedReader in = null;// 读取响应输入流  
		PrintWriter out = null;  
		StringBuffer sb = new StringBuffer();// 处理请求参数  
		String params = "";// 编码之后的参数  
		try {  
			// 编码请求参数  
			if (parameters.size() == 1) {  
				for (String name : parameters.keySet()) {  
					sb.append(name).append("=").append(  
							java.net.URLEncoder.encode(parameters.get(name),  
									"UTF-8"));  
				}  
				params = sb.toString();  
			} else {  
				for (String name : parameters.keySet()) {  
					sb.append(name).append("=").append(  
							java.net.URLEncoder.encode(parameters.get(name),  
									"UTF-8")).append("&");  
				}  
				String temp_params = sb.toString();  
				params = temp_params.substring(0, temp_params.length() - 1);  
			}  
			// 创建URL对象  
			java.net.URL connURL = new java.net.URL(url);  
			// 打开URL连接  
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL  
					.openConnection();  
			// 设置通用属性  
			httpConn.setRequestProperty("Accept", "*/*");  
			httpConn.setRequestProperty("Connection", "Keep-Alive");  
			httpConn.setRequestProperty("User-Agent",  
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");  
			//httpConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			// 设置POST方式  
			httpConn.setDoInput(true);  
			httpConn.setDoOutput(true);  
			// 获取HttpURLConnection对象对应的输出流  
			out = new PrintWriter(httpConn.getOutputStream());  
			// 发送请求参数  
			out.write(params);  
			// flush输出流的缓冲  
			out.flush();  
			// 定义BufferedReader输入流来读取URL的响应，设置编码方式  
			in = new BufferedReader(new InputStreamReader(httpConn  
					.getInputStream(), "UTF-8"));  
			String line;  
			// 读取返回的内容  
			while ((line = in.readLine()) != null) {  
				result += line;  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		} finally {  
			try {  
				if (out != null) {  
					out.close();  
				}  
				if (in != null) {  
					in.close();  
				}  
			} catch (IOException ex) {  
				ex.printStackTrace();  
			}  
		}  
		return result;  
	} 
	

	/**
	 * 
	 * @Title: sendGetRest
	 * @Description: 绕过ssl认证，直接调用第三方api的restful接口
	 * @param: @param urlStr
	 * @param: @param requestMethod
	 * @param: @return
	 * @param: @throws IOException
	 * @param: @throws KeyManagementException
	 * @param: @throws NoSuchAlgorithmException     
	 * @return: String     
	 * @author:  xieyuanqiu
	 * @date: 2017年9月5日 上午10:35:32
	 * @throws
	 */
	public static String sendGetRest(String urlStr, String requestMethod) throws IOException, KeyManagementException, NoSuchAlgorithmException{
		String result = "";
		HttpsURLConnection.setDefaultHostnameVerifier(new HttpUtils().new NullHostNameVerifier());
		SSLContext sc = SSLContext.getInstance("TLS");
		sc.init(null, trustAllCerts, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		URL url = new URL(urlStr);
		// 打开restful链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(requestMethod);// POST GET PUT DELETE
		// 设置访问提交模式，表单提交
		conn.setRequestProperty("User-Agent",  
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)"); 
		conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		conn.setConnectTimeout(130000);// 连接超时 单位毫秒
		conn.setReadTimeout(130000);// 读取超时 单位毫秒
		// 读取请求返回值
		int httpResponseCode = conn.getResponseCode();
		if(conn.getResponseCode()==200){
			BufferedReader in = null;// 读取响应输入流  
			in = new BufferedReader(new InputStreamReader(conn  
					.getInputStream(), "UTF-8"));  
			String line;  
			// 读取返回的内容  
			while ((line = in.readLine()) != null) {  
				result += line;  
			} 
		}else{
			JSONObject obj = new JSONObject();
			obj.put("httpResponseCode", httpResponseCode);
			result = obj.toJSONString();
		}
		return result;
	}
	static TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {

		}
		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}
	} };

	
	public class NullHostNameVerifier implements HostnameVerifier {
		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
	}
	/********************以上为远程调用第三方api的restful接口相关代码，绕过了ssl认证***********************/



}