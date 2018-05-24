package com.xhwl.mwc.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.mwc.utils.ResultJson;


@ControllerAdvice
public class SystemExceptionHandler implements HandlerExceptionResolver {


	private final Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);
	@ExceptionHandler(Exception.class)
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = null;
		ResultJson resultJson = new ResultJson();

		if (ex instanceof ApplicationException) {
			resultJson.setState(false);
			resultJson.setMessage(ex.getMessage());
			resultJson.setFlag("");
			resultJson.setResult("");
		}  else if (ex instanceof SecurityException) {
			int errCode = ex.getCause() == null ? 201 : Integer.valueOf(ex.getCause().getMessage());
			resultJson.setErrorCode(errCode);
			resultJson.setState(false);
			resultJson.setMessage(ex.getMessage());
			resultJson.setFlag("");
			resultJson.setResult("");
		}  else {
			resultJson.setErrorCode(201);
			resultJson.setState(false);
			resultJson.setMessage("系统异常，操作失败!");
			resultJson.setFlag("");
			resultJson.setResult("");
		}
		try {
			out = response.getWriter();
			out.write(JSONObject.toJSONString(resultJson));
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("--------发生错误--------");
		logger.info("错误信息:"+ex.getLocalizedMessage());
		logger.info("错误编码:"+ex.getCause().getMessage());
		logger.info("--------发生错误--------");
		ex.printStackTrace();
//		for (int i=0; i<ex.getStackTrace().length; i++) {
//			logger.info(ex.getStackTrace()[i].toString());
//		}
		return null;
	}
}