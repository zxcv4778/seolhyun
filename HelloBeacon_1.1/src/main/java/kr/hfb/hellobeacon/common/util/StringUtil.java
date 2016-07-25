/*
 * COPYRIGHT(c) A3 Security co.,Ltd 2008
 * This software is the proprietary information of A3 Security Consulting.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * KimHoon    2008. 04. 18  최초 작
 *
 */
package kr.hfb.hellobeacon.common.util;

import java.security.MessageDigest;
import java.util.Random;

/**
 * 
 * @author Robin
 *
 */
public class StringUtil {

	/**
	 * 파일명에서 확장자 가져오기
	 * 
	 * @param fileName
	 *            읽을 파일이름
	 * @return extension 파일확장자
	 */
	public static String getExtension(String fileName) {

		String extension = null;

		if (!fileName.equals("")) {
			extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		}
		return extension;
	}

	/**
	 * 랜덤 패스워드 생성
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomPassword(int length) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();

		String chars[] = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9,!,@,$,#".split(",");

		for (int i = 0; i < length; i++) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}
		return buffer.toString();
	}

	public static String getDigest(String str) {
		StringBuffer sb = new StringBuffer();

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes());
			byte byteData[] = messageDigest.digest();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return sb.toString();
	}
}