package test;

import static org.junit.Assert.*;

import java.security.MessageDigest;

import org.junit.Test;

public class RobinTest {

	@Test
	public void test() {
		StringBuffer sb = new StringBuffer();

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update("Factory0133 ".getBytes());
			byte byteData[] = messageDigest.digest();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(sb.toString());
	}

}
