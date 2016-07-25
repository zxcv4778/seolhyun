package kr.hfb.hellobeacon.common.util;

import kr.hfb.hellobeacon.common.exception.impl.CommonException;
import kr.hfb.hellobeacon.common.exception.impl.CommonRunTimeException;


public class LogMessageUtil {

	public static String getErrorLogMessage(boolean flag, String errorCode) {
		String retrunMessage = null;

		if (flag) {
			retrunMessage = "CODE : " + errorCode + ", MESSAGE : " + AppContextUtil.getInstance().getMeesage(errorCode);
		} else {
			retrunMessage = AppContextUtil.getInstance().getMeesage(errorCode);
		}
		return retrunMessage;
	}

	public static String getErrorLogMessage(boolean flag, String errorCode, String... keys) {
		String retrunMessage = null;

		if (flag) {
			retrunMessage = "CODE : " + errorCode + ", MESSAGE : " + AppContextUtil.getInstance().getMeesage(errorCode, keys);
		} else {
			retrunMessage = AppContextUtil.getInstance().getMeesage(errorCode, keys);
		}
		return retrunMessage;
	}

	public static String getInfoLogMessage(boolean flag, String infoCode, String... keys) {
		StringBuilder retrunMessage = new StringBuilder();
		if (true == flag) {
			retrunMessage.append("########## ");
		}

		retrunMessage.append(AppContextUtil.getInstance().getMeesage(infoCode, keys));

		if (true == flag) {
			retrunMessage.append(" ##########");
		}
		return retrunMessage.toString();
	}

	public static String exceptionMessage(Exception e) {
		String result = null;
		if (e instanceof CommonException) {
			result = ((CommonException) e).getDetailMessage();
		} else {
			result = getErrorLogMessage(false, "999");
		}
		return result;
	}
	
	public static String exceptionCode(Exception e) {
		String result = null;
		if (e instanceof CommonRunTimeException) {
			result = ((CommonRunTimeException) e).getCode();
		} else {
			result = "999";
		}
		return result;
	}
	
}
