package com.xhwl.mwc.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ip地址拦截.
 * @author Kellan.
 * @date:2018-2-19 下午11:33:17
 * @version :
 */
public class IpInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(IpInterceptor.class);
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse res,
			Object arg2, ModelAndView arg3) throws Exception {
		logger.info("-----------------退出ip---拦截器---"+request.getRequestURI());
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse res,
			Object handler) throws Exception {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("-----------------进入ip---拦截器");
		// 获取参数
		logger.info("-----------------访问地址  | 访问者ip | 访问时间");
		logger.info("-----------------"+request.getRequestURI() +" | "+request.getRemoteAddr() +" | "+ sim.format(new Date()));
		Set<String> keySet = request.getParameterMap().keySet();
		if(!keySet.isEmpty()) {
			for(String key : keySet) {
				logger.info("-----------------参数:"+key+":"+request.getParameter(key));
			}
		}
//		logger.info("----------------json参数："+getJson(request_tem));
		return true;
	}
	
//	private String getJson(HttpServletRequest request) {
//		BufferedReader br;  
//	    StringBuilder sb = null;  
//	    String reqBody = null;  
//	    try {  
//	        br = new BufferedReader(new InputStreamReader(  
//	                request.getInputStream()));  
//	        String line = null;  
//	        sb = new StringBuilder();  
//	        while ((line = br.readLine()) != null) {  
//	            sb.append(line);  
//	        }  
//	        reqBody = URLDecoder.decode(sb.toString(), "UTF-8");  
//	        reqBody = reqBody.substring(reqBody.indexOf("{"));  
//	        request.setAttribute("inputParam", reqBody);  
//	        System.out.println("JsonReq reqBody>>>>>" + reqBody);  
//	        return reqBody;  
//	    } catch (IOException e) {  
//	        e.printStackTrace();  
//	        return "jsonerror";  
//	    }  
//	}
//	
//	/**       
//     * 描述:获取 post 请求的 byte[] 数组 
//     * <pre> 
//     * 举例： 
//     * </pre> 
//     * @param request 
//     * @return 
//     * @throws IOException       
//     */  
//    private byte[] getRequestPostBytes(HttpServletRequest request)  
//            throws IOException {  
//        int contentLength = request.getContentLength();  
//        if(contentLength<0){  
//            return null;  
//        }  
//        byte buffer[] = new byte[contentLength];  
//        for (int i = 0; i < contentLength;) {  
//  
//            int readlen = request.getInputStream().read(buffer, i,  
//                    contentLength - i);  
//            if (readlen == -1) {  
//                break;  
//            }  
//            i += readlen;  
//        }  
//        return buffer;  
//    }  
//  
//    /**       
//     * 描述:获取 post 请求内容 
//     * <pre> 
//     * 举例： 
//     * </pre> 
//     * @param request 
//     * @return 
//     * @throws IOException       
//     */  
//    private String getRequestPostStr(HttpServletRequest request)  throws IOException {  
//        byte buffer[] = getRequestPostBytes(request);  
//        String charEncoding = request.getCharacterEncoding();  
//        if (charEncoding == null) {  
//            charEncoding = "UTF-8";  
//        }  
//        return new String(buffer, charEncoding);  
//    }  
}
