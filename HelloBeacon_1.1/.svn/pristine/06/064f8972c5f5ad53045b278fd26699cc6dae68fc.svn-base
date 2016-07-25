package kr.hfb.hellobeacon.common.exception.impl;

import kr.hfb.hellobeacon.common.exception.IfCommonException;


public class CommonRunTimeException extends RuntimeException implements IfCommonException{

	private static final long serialVersionUID = 5652993812174577706L;
	private String code = null;
	private String message = null;
	private String detailMessage = null;

	public CommonRunTimeException() {
	}

	public CommonRunTimeException(String code) {
		this.code = code;
	}

	public CommonRunTimeException(String code, String detailMessage) {
		this.code = code;
		this.detailMessage = detailMessage;
	}

	public CommonRunTimeException(String code, String message, String detailMessage) {
		this.code = code;
		this.message = message;
		this.detailMessage = detailMessage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}
}
