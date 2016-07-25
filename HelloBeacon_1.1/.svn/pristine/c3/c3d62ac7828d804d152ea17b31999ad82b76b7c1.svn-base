package kr.hfb.hellobeacon.scan.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.pool.concurrent.mgr.GenericTaskThreadPoolExecutor;
import kr.hfb.hellobeacon.common.pool.concurrunt.task.JobTask;
import kr.hfb.hellobeacon.common.pool.concurrunt.task.arg.GenericTaskArgument;
import kr.hfb.hellobeacon.common.util.AppContextUtil;
import kr.hfb.hellobeacon.common.util.DateUtil;
import kr.hfb.hellobeacon.scan.domain.ScanData;
import kr.hfb.hellobeacon.scan.domain.Scanner;
import kr.hfb.hellobeacon.scan.service.ScanService;
import kr.hfb.hellobeacon.sgs.service.SgsService;

//@Controller("scanController")
@Controller
@RequestMapping(value = {"", "/HelloBeacon"})
public class ScanController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

//	@Value("#{config['server.domain']}")
//	private String domain = null;
	@Autowired
	private ScanService scanService;
	
	@Autowired
	private GenericTaskThreadPoolExecutor threadPool;
	
	// @스캐너 조회
	@RequestMapping(value = "/scanner/getInfo/{idx}", method = RequestMethod.GET)
	@ResponseBody
	public Scanner getScannerInfoByIdx(Model model
		, @PathVariable int idx
		, HttpServletRequest request, HttpServletResponse response) {
				
		logger.info("#MLOG /scanner/getInfo/"+ idx );
		Scanner req = new Scanner();
		req.setIdx((long) idx);

		Scanner result = null;
		try {
			result = scanService.selectScanner(request, req);
		} catch (Exception e) { 
			logger.info("#MLOG /scanner/getInfo/"+ idx +" ERROR! caused by "+e.getCause());
		}
		
		return result;			
	}
	
	// @스캐너 Alive signal 수신
	@RequestMapping(value = "/scanner/sendAliveHttp", method = RequestMethod.POST)
	@ResponseBody
	public String sendAliveByScannerHttp(Model model
		, @RequestParam String scannerid
		, @RequestParam String mac
		, HttpServletRequest request, HttpServletResponse response) {
				
		logger.info("#PMLOG /scanner/sendAlive/ by scannerId:"+ scannerid + " | mac:"+mac );
		Scanner req = new Scanner();
		req.setScannerId(scannerid);
		req.setMacAddress(mac);

		Scanner result = null;
		String errMsg = "";
		try {
			result = scanService.updateScanner(request, req);
		} catch (Exception e) { 
			logger.info("#MLOG /scanner/sendAlive/ by scannerId:"+ scannerid + " | mac:"+mac +" ERROR! caused by "+e.getCause());
			errMsg = e.getCause().toString();
		}
		

		JSONObject json = new JSONObject();						
		if (result != null && result.getRtCode() > 0) {
			json.put("rtCode", "success");		
			json.put("rtmessage", "success");
		} else {
			json.put("rtCode", "error");		
			json.put("rtmessage", errMsg);
		}
		
		return json.toString();			
	}
	
	

	// @스캐너 Alive signal 수신
	@RequestMapping(value = "/scanner/sendAlive", method = RequestMethod.POST)
	@ResponseBody
	public String sendAliveByScanner(
		@RequestBody String params
		, HttpServletRequest request, HttpServletResponse response) {
				
		
		if (params != null && !"".equals(params)) {
			try {
				params = URLDecoder.decode(params, "UTF-8");
			} catch (Exception e) {
				logger.info("#MLOG /scanner/sendSignal/ by params "+params+ " DECODE error! caused by :"+ e.getCause());
				e.printStackTrace();
			}
		}
		
		logger.info("#PMLOG /scanner/sendAlive/ ALIVE-DATA by params "+params);
		
		//logger.info("#MLOG /scanner/sendAlive/ by params:"+ params);
		//System.out.println("#MLOG /scanner/sendAlive/ by params:"+ params);
				
		List<Scanner> scanner = null;
		Scanner result = null;
		int rtCode = 0;
		
		String errMsg = "";
		
		if (params != null && !"".equals(params)) {
			scanner = new ArrayList<Scanner>();
			
			JSONParser parser = new JSONParser();
			/* JSONObject -> JSONArray 로 개편
			 * 2016.05.02 added by jaeyeon.hwang
			 */
			//JSONObject json;
			JSONArray json;
			String scannerid = "";
			try {
				//json = (JSONObject) parser.parse(params);
				json = (JSONArray) parser.parse(params);
				
				Scanner sc = null;
				for (int i=0; i<json.size(); i++) {
					sc = new Scanner();
					JSONObject jo = (JSONObject) json.get(i);
					scannerid = "";
					scannerid = jo.get("scannerid").toString();
					sc.setScannerId(scannerid);
					scanner.add(sc);
				}
				
				System.out.println("#MLOG /scanner/sendAlive/ by scannerid list Array:"+ scanner.toString());
				rtCode = 1;
			} catch (Exception e1) {
				rtCode = -3;
				logger.info("#MLOG /scanner/sendAlive/ by params:"+ params + " ERROR! caused by "+e1.getCause());
				errMsg = e1.getCause().toString();
			}
			if (rtCode > -1) {	
				try {
					for(Scanner sc : scanner) {
						Scanner rt = scanService.updateScanner(request, sc);
						logger.info("#MLOG /scanner/sendAlive/ updateScanner scannerid:"+sc.getScannerId()+ " | result:"+ rt.getRtCode());
						System.out.println("#MLOG /scanner/sendAlive/ updateScanner scannerid:"+sc.getScannerId()+ " | result:"+ rt.getRtCode());
					}
					rtCode = 1;
					
				} catch (Exception e) { 
					logger.info("#MLOG /scanner/sendAlive/ by scannerid list:"+ params + " ERROR! caused by "+e.getCause());
					errMsg = e.getCause().toString();
				}
			}
		} else {
			rtCode = -2;
			errMsg = "Request parameter params empty!";
		}

		JSONObject json = new JSONObject();						
		if (rtCode > 0) {
			json.put("rtCode", "success");		
			json.put("rtmessage", "success");
		} else {
			json.put("rtCode", rtCode);		
			json.put("rtmessage", errMsg);
		}
		
		return json.toString();			
	}
	
	
	// @스캐너 Alive signal 수신
		@RequestMapping(value = "/scanner/sendAliveStr", method = RequestMethod.POST)
		@ResponseBody
		public int sendAliveByScannerStr(
			@RequestBody String params
			, HttpServletRequest request, HttpServletResponse response) {
					
			
			if (params != null && !"".equals(params)) {
				try {
					params = URLDecoder.decode(params, "UTF-8");
				} catch (Exception e) {
					logger.info("#MLOG /scanner/sendSignal/ by params "+params+ " DECODE error! caused by :"+ e.getCause());
					e.printStackTrace();
				}
			}
			
			logger.info("#PMLOG /scanner/sendAlive/ ALIVE-DATA by params "+params);
			
			//logger.info("#MLOG /scanner/sendAlive/ by params:"+ params);
			//System.out.println("#MLOG /scanner/sendAlive/ by params:"+ params);
					
			List<Scanner> scanner = null;
			Scanner result = null;
			int rtCode = 0;
			
			String errMsg = "";
			
			if (params != null && !"".equals(params)) {
				scanner = new ArrayList<Scanner>();
				
				String[] paramsIds = null;
				try {
					params = params.replaceAll("\"", "\\\"");
					
					if (params.contains("/")) {
						paramsIds = params.split("/");
					} else {
						paramsIds = new String[1]; 
						paramsIds[0] = params;
					}
					
					Scanner sc = null;
					for (String scannerid : paramsIds) {
						sc = new Scanner();
						sc.setScannerId(scannerid);
						scanner.add(sc);
					}
					
					System.out.println("#MLOG /scanner/sendAlive/ by scannerid list Array:"+ scanner.toString());
					rtCode = 1;
				} catch (Exception e1) {
					rtCode = -3;
					logger.info("#MLOG /scanner/sendAlive/ by params:"+ params + " ERROR! caused by "+e1.getCause());
					errMsg = e1.getCause().toString();
				}
				if (rtCode > -1) {	
					try {
						for(Scanner sc : scanner) {
							Scanner rt = scanService.updateScanner(request, sc);
							logger.info("#MLOG /scanner/sendAlive/ updateScanner scannerid:"+sc.getScannerId()+ " | result:"+ rt.getRtCode());
							System.out.println("#MLOG /scanner/sendAlive/ updateScanner scannerid:"+sc.getScannerId()+ " | result:"+ rt.getRtCode());
						}
						rtCode = 1;
						
					} catch (Exception e) { 
						logger.info("#MLOG /scanner/sendAlive/ by scannerid list:"+ params + " ERROR! caused by "+e.getCause());
						errMsg = e.getCause().toString();
					}
				}
			} else {
				rtCode = -2;
				errMsg = "Request parameter params empty!";
			}

			return rtCode;		
		}
	

	// @스캐너 Beacon Sensing 데이터 수신
	@RequestMapping(value = "/scanner/sendSignalHttp", method = RequestMethod.POST)
	@ResponseBody
	public String sendSignalByBeaconHttp(Model model
		//, @RequestParam(required=true) String authid
		, @RequestParam(required=true) String scannerid
		, @RequestParam(required=false, defaultValue="")String transid
		, @RequestParam(required=true) String uuid
		, @RequestParam(required=true) int majorver
		, @RequestParam(required=true) int minorver
		, @RequestParam(required=true) int rssi
		, @RequestParam(required=true) int txpower
		, @RequestParam(required=false) String resv1
		, @RequestParam(required=false) String resv2
		, @RequestParam(required=false) String resv3
		, @RequestParam(required=false) int resv4
		, @RequestParam(required=false) String resv5
		, HttpServletRequest request, HttpServletResponse response) {
				
		logger.info("#MLOG /scanner/sendSignal/ by scannerId:"+ scannerid + " | uuid:"+uuid );
		ScanData req = new ScanData();
		//req.setAuthid(authid);
		req.setScannerid(scannerid);
		req.setTransid(transid);
		req.setUuid(uuid);
		req.setMajorver(majorver);
		req.setMinorver(minorver);
		req.setRssi(rssi);
		req.setTxpower(txpower);
		req.setResv1(resv1);
		req.setResv2(resv2);
		req.setResv3(resv3);
		req.setResv4(resv4);
		try {
			Date resv5_date = DateUtil.strToDateTime("yyyy-MM-dd hh:mm:ss", resv5);
			req.setResv5(resv5_date);
		} catch(Exception e) { e.printStackTrace(); } 

		ScanData result = null;
		String errMsg = "";
		try {
			result = scanService.insertScanData(request, req);
		} catch (Exception e) { 
			logger.info("#MLOG /scanner/sendSignal/ by scannerid:"+ scannerid + " | uuid:"+uuid +" ERROR! caused by "+e.getCause());
			errMsg = e.getCause().toString();
		}
		

		JSONObject json = new JSONObject();						
		if (result != null && result.getRtCode() > 0) {
			json.put("rtCode", "success");		
			json.put("rtmessage", "success");
		} else {
			json.put("rtCode", "error");		
			json.put("rtmessage", errMsg);
		}
		
		return json.toString();			
	}
	
	
	// @스캐너 Beacon Sensing 데이터 수신
	@RequestMapping(value = "/scanner/sendSignal", method = RequestMethod.POST)
	@ResponseBody
	public String sendSignalByBeacon(
		@RequestBody String params
		, HttpServletRequest request, HttpServletResponse response) {
		
		//System.out.println("#MLOG /scanner/sendSignal/ by params:"+ params);
		
		if (params != null && !"".equals(params)) {
			try {
				params = URLDecoder.decode(params, "UTF-8");
			} catch (Exception e) {
				logger.info("#MLOG /scanner/sendSignal/ by params "+params+ " DECODE error! caused by :"+ e.getCause());
				e.printStackTrace();
			}
		}
		
		logger.info("#PMLOG /scanner/sendSignal/ SCAN-DATA by params:"+ params);
		
		List<ScanData> scanList = null; 
		ScanData result = null;
		int rtCode = 0;
		
		String errMsg = "";
		
		if (params != null && !"".equals(params)) {
			scanList = new ArrayList<ScanData>();
			
			JSONParser parser = new JSONParser();
			JSONArray jsonArr;
			
			try {
				logger.info("#MLOG inputParams :"+params);
				System.out.println("#MLOG inputParams :"+params);
				try {
					params = params.replaceAll("\"", "\\\"");
					jsonArr = (JSONArray) parser.parse(params);
				} catch (Exception ep) {
					ep.printStackTrace();
					throw new Exception ("#ERROR input params parsing ERROR! "+ep.getCause()); 
				}
				
				for(int i=0; i<jsonArr.size(); i++) {
					ScanData req = new ScanData();
					JSONObject json = (JSONObject) jsonArr.get(i);					
		 			
					// client time stamp 처리
					// yyyy-MM-dd HH:mm:ss 문자열로 수신
					String clienttime = "";
					try {
						if(json.get("clienttime") != null) {
							clienttime = json.get("clienttime").toString();
							Date clTime = DateUtil.strToDateTime("yyyy-MM-dd HH:mm:ss", clienttime);
							
							req.setClienttime(clTime);
							System.out.println("#MLOG getCleintTIme:"+req.getClienttime());
						}
					
					} catch (Exception e1) { logger.error("#MLOG clienttime convert error! "+e1.getCause()); }
							
					String type = "";
					if(json.get("type") != null &&
							( json.get("type").equals("IB")
							|| json.get("type").equals("EU")
							|| json.get("type").equals("ET")
							)) 	{
						type = json.get("type").toString();						
						
						switch (type) {
							// iBeacon
							case "IB" :
								if(json.get("uuid") != null) 		{req.setUuid(json.get("uuid").toString()); } else { throw new Exception("check UUID param is not valid!"); }								
								if(json.get("majorid") != null) 	{req.setMajorver(Float.valueOf(json.get("majorid").toString())); } else { throw new Exception("check MAJORVER param is not valid!"); }
								if(json.get("minorid") != null) 	{req.setMinorver(Float.valueOf(json.get("minorid").toString())); } else { throw new Exception("check MINORVER param is not valid!"); }
								if(json.get("rssi") != null) 		{req.setRssi(Integer.valueOf(json.get("rssi").toString())); } else { throw new Exception("check RSSI param is not valid!"); }
								if(json.get("txpower") != null) 	{req.setTxpower(Integer.valueOf(json.get("txpower").toString())); } else { throw new Exception("check TXPOWER param is not valid!"); }
							break;
							// Eddystone UID
							case "EU" :
								if(json.get("namespace") != null) 		{req.setUuid(json.get("namespace").toString()); } else { throw new Exception("check NAMESPACE param is not valid!"); }
								if(json.get("instance") != null) 		{req.setUuid2(json.get("instance").toString()); } else { throw new Exception("check INSTANCE param is not valid!"); }
								if(json.get("rssi") != null) 			{req.setRssi(Integer.valueOf(json.get("rssi").toString())); } else { throw new Exception("check RSSI param is not valid!"); }
								if(json.get("txpower") != null) 		{req.setTxpower(Integer.valueOf(json.get("txpower").toString())); } else { throw new Exception("check TXPOWER param is not valid!"); }
							break;
							// Eddystone TLM
							case "ET" :
								//req.setUuid("ET");
								if(json.get("mac") != null) 			{req.setUuid(json.get("mac").toString()); } else { throw new Exception("check MAC param is not valid!"); }
								if(json.get("vbatt") != null) 			{req.setMajorver(Float.valueOf(json.get("vbatt").toString())); } else { throw new Exception("check VBATT param is not valid!"); }
								if(json.get("temp") != null) 			{req.setMinorver(Float.valueOf(json.get("temp").toString())); } else { throw new Exception("check TEMP param is not valid!"); }
								if(json.get("adv_cnt") != null) 		{req.setRssi(Integer.valueOf(json.get("adv_cnt").toString())); } else { throw new Exception("check ADV_CNT param is not valid!"); }
								if(json.get("sec_cnt") != null) 		{req.setTxpower(Integer.valueOf(json.get("sec_cnt").toString())); } else { throw new Exception("check SEC_CNT param is not valid!"); }
							break;							
						}
					} else {
						throw new Exception("#ERROR check TYPE param is not valid!"); 
					}
					
					if(json.get("type") != null) 		req.setTypecode(json.get("type").toString());
					if(json.get("scannerid") != null) 	req.setScannerid(json.get("scannerid").toString());						 		
					if(json.get("transid") != null) 	req.setTransid(json.get("transid").toString());							 
					
					if(json.get("resv1") != null) 		req.setResv1(json.get("resv1").toString());								
					if(json.get("resv2") != null) 		req.setResv2(json.get("resv2").toString());								
					if(json.get("resv3") != null) 		req.setResv3(json.get("resv3").toString());								
					if(json.get("resv4") != null) 		req.setResv4(Integer.valueOf(json.get("resv4").toString()));			
					
					if(json.get("resv5") != null) { 
						try {
							Date resv5_date = DateUtil.strToDateTime("yyyy-MM-dd hh:mm:ss", json.get("resv5").toString());
							req.setResv5(resv5_date);
						} catch(Exception e2) { e2.printStackTrace(); }				
					}
					
					scanList.add(req);
				}
			} catch (Exception e1) {
				rtCode = -3;
				e1.printStackTrace();
				logger.info("#MLOG /scanner/sendSignal/ by params:"+ params + " ERROR! caused by "+e1.getCause());
				System.out.println("#MLOG /scanner/sendSignal/ by params:"+ params + " ERROR! caused by "+e1.getCause()+" // "+e1.getMessage());
				//if (e1.getCause() != null) errMsg = errMsg + e1.getCause().toString();
				//if (e1.getMessage() != null) errMsg = errMsg + " / " + e1.getMessage().toString();
			}
			
			if (rtCode > -1) {
				try {
//					for(ScanData sd : scanList) {
//						ScanData rt = scanService.insertScanData(request, sd);
//						logger.info("#MLOG /scanner/sendSignal/ by scanner info :"+ sd.getUuid() + "&&"+sd.getUuid2() + " result :"+rt.getRtCode());
//						System.out.println("#MLOG /scanner/sendSignal/ by scanner info :"+ sd.getUuid() + "&&"+sd.getUuid2() + " result :"+rt.getRtCode());
//
//						rtCode = 1;
//					}
					
					// DB인서트 는 비동기로
					GenericTaskArgument taskargument =new GenericTaskArgument();
					taskargument.setTargetObject(scanService);
					taskargument.setMethodName("insertScanDataList");
					
					
					Class<?>[] parmeterTypes={ScanData[].class};
					taskargument.setParameterTypes(parmeterTypes);
					// 형 변환
					ScanData[] scanDataList = scanList.toArray( new ScanData[scanList.size()]);
					Object[] parameters={scanDataList};
					taskargument.setParameters(parameters);
					
					JobTask JobTask = new JobTask(taskargument,Thread.currentThread().getName()+"_JobTask");
					
					if(threadPool.isAvailAbleExecutionResource()){
						try {
							threadPool.execute(JobTask);
							logger.info("#MLOG insertScanDataList ThreadPool Execute - get status : "+threadPool);
							System.out.println("#MLOG insertScanDataList ThreadPool Execute - get status : "+threadPool);
							rtCode = 1;
						} catch (Exception e) {
							logger.info("#MLOG insertScanDataList ThreadPool Execute ERROR! caused by "+e.getCause());
							System.out.println("#MLOG insertScanDataList ThreadPool Execute ERROR! caused by "+e.getCause());
							rtCode = -4;
						} 
					}else{
						// 쓰레드 런이 정상적이지 않은 경우 오류코드 리턴
						logger.info("#MLOG insertScanDataList cannot execute! no more threads..");
						System.out.println("#MLOG insertScanDataList cannot execute! no more threads..");
						rtCode = -4;
					}
					
				} catch (Exception e) { 
					logger.info("#MLOG /scanner/sendSignal/ by params:"+ params + " ERROR! caused by "+e.getCause());
					errMsg = e.getCause().toString();
				}
			}
		} else {
			rtCode = -2;
			errMsg = "Request parameter params empty!";
		}

		JSONObject json1 = new JSONObject();						
		if (rtCode > 0) {
			json1.put("rtCode", "success");		
			json1.put("rtmessage", "success");
		} else {
			json1.put("rtCode", rtCode);		
			json1.put("rtmessage", errMsg);
		}
		
		return json1.toString();			
	}
	
	
	// @스캐너 Beacon Sensing 데이터 수신
		@RequestMapping(value = "/scanner/sendSignalStr", method = RequestMethod.POST)
		@ResponseBody
		public int sendSignalByBeaconStr(
			@RequestBody String params
			, HttpServletRequest request, HttpServletResponse response) {
			
			//System.out.println("#MLOG /scanner/sendSignal/ by params:"+ params);
			
			if (params != null && !"".equals(params)) {
				try {
					params = URLDecoder.decode(params, "UTF-8");
				} catch (Exception e) {
					logger.info("#MLOG /scanner/sendSignal/ by params "+params+ " DECODE error! caused by :"+ e.getCause());
					e.printStackTrace();
				}
			}
			
			logger.info("#PMLOG /scanner/sendSignal/ SCAN-DATA by params:"+ params);
			
			List<ScanData> scanList = null; 
			ScanData result = null;
			int rtCode = 0;
			
			String errMsg = "";
			
			if (params != null && !"".equals(params)) {
				scanList = new ArrayList<ScanData>();
				
				String paramsArr = null;
				
				try {
					logger.info("#MLOG inputParams :"+params);
					System.out.println("#MLOG inputParams :"+params);
					
					String [] paramsIds = null;
					try {
						params = params.replaceAll("\"", "\\\"");

						if (params.contains("/")) {
							paramsIds = params.split("/");
						} else {
							paramsIds = new String[1]; 
							paramsIds[0] = params;
						}
						
					} catch (Exception ep) {
						ep.printStackTrace();
						rtCode = -1;
						throw new Exception ("#ERROR input params parsing ERROR! "+ep.getCause()); 
					}
					
					for(String param : paramsIds) {
						ScanData req = new ScanData();
						
						String[] paramSet = param.split("|");
						if(paramSet[0] != null) { 	req.setScannerid(paramSet[0]);		}
						else { throw new Exception("check 1'st scannerid param is not valid!");  }
						if(paramSet[1] != null) {	req.setMajorver(Float.parseFloat(paramSet[1]));		}
						else { throw new Exception("check 2'nd major_id param is not valid!"); }
						if(paramSet[2] != null) {	req.setMinorver(Float.parseFloat(paramSet[2]));		}
						else { throw new Exception("check 3'nd minor_id param is not valid!"); }
						if(paramSet[3] != null) { 	req.setRssi(0 - Math.abs(Integer.parseInt(paramSet[3]))); 	}
						else { throw new Exception("check 4'nd rssi param is not valid!"); }
						
						
						scanList.add(req);
					}
				} catch (Exception e1) {
					rtCode = -1;
					e1.printStackTrace();
					logger.info("#MLOG /scanner/sendSignal/ by params:"+ params + " ERROR! caused by "+e1.getCause());
					System.out.println("#MLOG /scanner/sendSignal/ by params:"+ params + " ERROR! caused by "+e1.getCause()+" // "+e1.getMessage());
				}
				
				if (rtCode > -1) {
					try {
						// DB인서트 는 비동기로
						GenericTaskArgument taskargument =new GenericTaskArgument();
						taskargument.setTargetObject(scanService);
						taskargument.setMethodName("insertScanDataList");
						
						
						Class<?>[] parmeterTypes={ScanData[].class};
						taskargument.setParameterTypes(parmeterTypes);
						// 형 변환
						ScanData[] scanDataList = scanList.toArray( new ScanData[scanList.size()]);
						Object[] parameters={scanDataList};
						taskargument.setParameters(parameters);
						
						JobTask JobTask = new JobTask(taskargument,Thread.currentThread().getName()+"_JobTask");
						
						if(threadPool.isAvailAbleExecutionResource()){
							try {
								threadPool.execute(JobTask);
								logger.info("#MLOG insertScanDataList ThreadPool Execute - get status : "+threadPool);
								System.out.println("#MLOG insertScanDataList ThreadPool Execute - get status : "+threadPool);
								rtCode = 1;
							} catch (Exception e) {
								logger.info("#MLOG insertScanDataList ThreadPool Execute ERROR! caused by "+e.getCause());
								System.out.println("#MLOG insertScanDataList ThreadPool Execute ERROR! caused by "+e.getCause());
								rtCode = -4;
							} 
						}else{
							// 쓰레드 런이 정상적이지 않은 경우 오류코드 리턴
							logger.info("#MLOG insertScanDataList cannot execute! no more threads..");
							System.out.println("#MLOG insertScanDataList cannot execute! no more threads..");
							rtCode = -4;
						}
						
					} catch (Exception e) { 
						logger.info("#MLOG /scanner/sendSignal/ by params:"+ params + " ERROR! caused by "+e.getCause());
						errMsg = e.getCause().toString();
					}
				}
			} else {
				rtCode = -2;
				errMsg = "Request parameter params empty!";
			}

			return rtCode;
		}
}
