package test;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.hfb.hellobeacon.common.util.AppContextUtil;
import kr.hfb.hellobeacon.common.util.DateUtil;
import kr.hfb.hellobeacon.scan.dao.ScanDao;
import kr.hfb.hellobeacon.scan.domain.ScanData;
import kr.hfb.hellobeacon.scan.domain.Scanner;
import kr.hfb.hellobeacon.scan.service.ScanService;
import kr.hfb.hellobeacon.sgs.service.SgsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring/root-context.xml", "file:src/main/resources/spring/context-threadPool.xml"})
public class ScanTest {
	
	@Autowired
	private ScanService scanService = null;
	
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
	
	@Test
	public void testSelectScanData() {
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("idx",2);
		
		//scanService = (ScanService) AppContextUtil.getInstance().getBean("IF_SCAN_DATA_Service");
		
		ScanData result = scanService.selectScanData(null, parameters);
		
		System.out.println("scanData:"+result.toString());
		
	}
	
	@Test
	public void testSelectFilteredScanData() {
		//Map<String, Object> parameters = new HashMap<String,Object>();
		ScanData parameters = new ScanData();
		
		parameters.setScannerid("test01");
		parameters.setTypecode("IB");
		parameters.setUuid("uuid01");
		parameters.setMajorver(1);
		parameters.setMinorver(2);
		//scanService = (ScanService) AppContextUtil.getInstance().getBean("IF_SCAN_DATA_Service");
		
		ScanData result = scanService.selectFilteredScanData(null, parameters);
		
		System.out.println("scanData:"+result.toString());
		
	}

	@Test
	public void testInsertScanData() {
		ScanData parameters = new ScanData();
		parameters.setIdx((long) 2);
		//parameters.setAuthid("2a");
		parameters.setTypecode("type");
		parameters.setTransid("trans2a");
		parameters.setScannerid("scan2");
		parameters.setUuid("uuid2");
		parameters.setMajorver(2);
		parameters.setMinorver(2);
		parameters.setRssi(20);
		parameters.setTxpower(22);
		//parameters.setMacAddress("mac2");
		parameters.setResv1("resv21");
		parameters.setResv2("resv22");
		parameters.setResv3("resv23");
		parameters.setResv4(24);
		parameters.setResv5(DateUtil.getNow());
		
		//scanService = (ScanService) AppContextUtil.getInstance().getBean("IF_SCAN_DATA_Service");
		
		ScanData result = scanService.insertScanData(null, parameters);
		
		System.out.println("scanData:"+result.toString());
		
	}
	
		
	
	@Test
	public void testSelectScanner() {
		Scanner parameters = new Scanner();
		parameters.setIdx((long) 1);
		
		//scanService = (ScanService) AppContextUtil.getInstance().getBean("IF_SCAN_DATA_Service");
		java.util.Date date= new java.util.Date();
		Timestamp ntime = new Timestamp(date.getTime());
		String ntimestr = ntime.toString();
		
		//String str = "10062007003012";
		//SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		//java.util.Date date = sdf.parse(str);
		///java.sql.Timestamp ts = new Timestamp(date.getTime());
		
		Scanner result = scanService.selectScanner(null, parameters);
		
		System.out.println(ntime+" > "+ntimestr+"\nscanData:"+result.toString());
		
	}
	
	

	@Test
	public void testSelectScannerData1() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idx",(long) 12);
		
		
		ScanData result = scanService.selectScanData(null, parameters);
		
		System.out.println("\nscanData:"+result.toString());
		
	}
	

	@Test
	public void testInsertScanner() {
		Scanner parameters = new Scanner();
		parameters.setIdx((long) 1);
		parameters.setAuthId("A1603X00000001");
		parameters.setTypeCode("typeA");
		parameters.setScannerId("34:2C:5D");
		parameters.setUuid("uuid34");
		parameters.setMacAddress("mac34");
		parameters.setStatus("A");
		parameters.setResv1("resv21");
		parameters.setResv2("resv22");
		parameters.setResv3("resv23");
		parameters.setResv4(24);
		parameters.setResv5(DateUtil.getNow());
		
		//scanService = (ScanService) AppContextUtil.getInstance().getBean("IF_SCAN_DATA_Service");
		
		Scanner result = scanService.insertScanner(null, parameters);
		
		System.out.println("Scanner:"+result.toString());
		
	}
	

	@Test
	public void testUpdateScanner() {
		Scanner parameters = new Scanner();
		parameters.setScannerId("34:2C:5D");
		
		//scanService = (ScanService) AppContextUtil.getInstance().getBean("IF_SCAN_DATA_Service");
		
		Scanner result = scanService.updateScanner(null, parameters);
		
		System.out.println("Scanner:"+result.toString());
		
	}
	
	
	@Test
	public void testJsonParser() throws ParseException {
		
		//String params = "[{ \"scannerid\":\"1111\" },{ \"scannerid\":\"1122\" } ]";
		String params = "[{\"scannerid\":\"hf001\", \"type\":\"IB\", \"uuid\":\"3628ba2b-e721-48f6-8a0f-58eb26fe96f2”, \"majorid\":100, \"minorid\":5, \"rssi\":80, \"txpower\":60},"
+ "{\"scannerid\":\"hf002\", \"type\":\"EU\", \"namespace\":\"3628ba2b58eb26fe96f2\", \"instance\":100005, \"rssi\":90, \"txpower\":56, \"transid\":\"\"}]";

		JSONParser parser = new JSONParser();
		JSONArray json;
		
		String scannerid = "";
		
		json = (JSONArray) parser.parse(params);
		System.out.println("#MLOG /scanner/sendAlive/ json type:"+ json.size());
		
		for (int i=0; i<json.size(); i++) {
			JSONObject jo = (JSONObject) json.get(i);
			scannerid = jo.get("scannerid").toString();
			System.out.println("#MLOG /scanner/sendAlive/ by scannerid:"+ scannerid);
		}
		//scannerid = json.get("scannerid").toString();
		
		//System.out.println("#MLOG /scanner/sendAlive/ by scannerid:"+ scannerid);
	}
	
}
