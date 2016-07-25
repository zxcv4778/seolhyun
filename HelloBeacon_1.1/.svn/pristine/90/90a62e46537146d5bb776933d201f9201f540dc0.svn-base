package kr.hfb.hellobeacon.common.exception.impl;

import kr.hfb.hellobeacon.common.exception.IfCommonException;



public class CommonException extends Exception implements IfCommonException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 632837715064435910L;
	
	private String code = null;
	private String message = null;
	private String detailMessage = null;

	public CommonException() {
	}

	public CommonException(String code) {
		this.code = code;
	}

	public CommonException(String code, String detailMessage) {
		this.code = code;
		this.detailMessage = detailMessage;
	}

	public CommonException(String code, String message, String detailMessage) {
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
