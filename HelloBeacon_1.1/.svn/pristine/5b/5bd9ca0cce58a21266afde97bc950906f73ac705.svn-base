package kr.hfb.hellobeacon.common.exception.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.exception.IfCommonException;

public class CommonExceptionResolever implements HandlerExceptionResolver {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ResourceBundleMessageSource messageSource = null;
	private String unknownErrorCode = "997"; // 서버 오류
	private String unknownErrorMessage = "Interface Server Error";
	private String dataBaseErrorCode = "950"; // 데이터 베이스 오류
	private MappingJacksonHttpMessageConverter jsonConverter = null;

	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setUnknownErrorCode(String unknownErrorCode) {
		this.unknownErrorCode = unknownErrorCode;
	}

	public void setUnknownErrorMessage(String unknownErrorMessage) {
		this.unknownErrorMessage = unknownErrorMessage;
	}

	public void setJsonConverter(MappingJacksonHttpMessageConverter jsonConverter) {
		this.jsonConverter = jsonConverter;
	}

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception) {
		CommonBean commonBean = new CommonBean();
		String message = null;
		if (exception instanceof IfCommonException) {
			IfCommonException ifCommonException = (IfCommonException) exception;
			commonBean.setCode(ifCommonException.getCode());

			if (exception.getMessage() == null) {
				message = messageSource.getMessage(ifCommonException.getCode(), null, request.getLocale());
			} else {
				message = ifCommonException.getMessage();
			}
			commonBean.setMessage(message);
			commonBean.setDetailMessage(ifCommonException.getDetailMessage());
		} else if (exception instanceof DataAccessException) {
			DataAccessException dataAccessException = (DataAccessException) exception;
			message = messageSource.getMessage(dataBaseErrorCode, null, request.getLocale());
			commonBean.setMessage(message);
			commonBean.setDetailMessage(dataAccessException.getMessage());
		} else {
			commonBean.setCode(unknownErrorCode);
			commonBean.setMessage(unknownErrorMessage);
			commonBean.setDetailMessage(exception.getMessage());
		}
		logger.error("[errorCode : " + commonBean.getCode() + "] [message : " + commonBean.getMessage() + "] [detailMessage : " + commonBean.getDetailMessage() + "]");

		HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);

		try {
			jsonConverter.write(commonBean, MediaType.APPLICATION_JSON, outputMessage);
		} catch (HttpMessageNotWritableException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView();


	}
}
