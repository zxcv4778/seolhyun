package kr.hfb.hellobeacon.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	/**
	 * 현재시간 추출
	 */
	public static String getNowDate(String format) {
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat sdfCurrent = new SimpleDateFormat(format);

		return sdfCurrent.format(today);
	}
	
	public static Date getNow() {
		Date today = Calendar.getInstance().getTime();
		return today;
	}
	

	/**
	 * String -> Date 변환
	 * @param format
	 * @param str_date
	 * @return
	 * @throws Exception
	 */
	public static Date strToDateTime(String format, String str_date) {
		Date result = null;
		try{
			if (str_date == null)
				return null;
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			result = formatter.parse(str_date);
		}catch (Exception e) {
			
		}
		return result;
	}
	
	
	/**
	 * <p>
	 * 주어진 Date type 의 날짜와 시각을 yyyy-MM-dd hh:mm:ss 형태로 변환 후 return.
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 * @see java.util.Date
	 * @see java.util.Locale <p>
	 */
	public static String getDateTime(Date inputDate) {
		Date today = inputDate;
		Locale currentLocale = new Locale("KOREAN", "KOREA");
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern,currentLocale);
		return formatter.format(today);
	}
	
	
}
