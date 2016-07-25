package kr.hfb.hellobeacon.scan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.hfb.hellobeacon.scan.dao.ScanDao;
import kr.hfb.hellobeacon.scan.domain.ScanData;
import kr.hfb.hellobeacon.scan.domain.Scanner;
import kr.hfb.hellobeacon.scan.service.AbsctractScanService;
import kr.hfb.hellobeacon.sgs.service.AbsctractSgsService;

@Service("scanner")
public class ScanServiceImpl extends AbsctractScanService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("scanDao")
	private ScanDao scanDao = null;

	public ScanServiceImpl() {
		super.paramKey = new String[] {};
	}

	public ScanData selectScanData(HttpServletRequest request, Map<String, Object> parameters) {
		return scanDao.execute(parameters, "scanData.selectScanData", "select");
	}
	public ScanData selectFilteredScanData(HttpServletRequest request, ScanData parameters) {
		return scanDao.execute(parameters, "scanData.selectFilteredScanData", "selectList");
	}
	public ScanData insertScanData(HttpServletRequest request, ScanData parameters) {
		return scanDao.execute(parameters, "scanData.insertScanData", "insert");
	}

	
	public Scanner selectScanner(HttpServletRequest request, Scanner parameters) {
		return scanDao.execute(parameters, "scanData.selectScanner", "select");
	}
	public Scanner countScanner(HttpServletRequest request, Scanner parameters) {
		return scanDao.execute(parameters, "scanData.countScanner", "select");
	}


	public Scanner insertScanner(HttpServletRequest request, Scanner parameters) {
		return scanDao.execute(parameters, "scanData.insertScanner", "insert");
	}
	public Scanner updateScanner(HttpServletRequest request, Scanner parameters) {
		return scanDao.execute(parameters, "scanData.updateScanner", "update");
	}
	public Scanner deleteScanner(HttpServletRequest request, Scanner parameters) {
		return scanDao.execute(parameters, "scanData.deleteScanner", "delete");
	}
	

	public int insertScanDataList(ScanData[] scanList) throws Exception {
		int rtCode = 0;
		try {
			for(ScanData sd : scanList) {
				
				// 이미 등록된 데이터가 있는 경우 제외
				ScanData dataExist = this.selectFilteredScanData(null, sd);
				if (dataExist != null && dataExist.getScanList() != null && dataExist.getScanList().size() > 0) {
					rtCode = 0;
				} else {
					
					ScanData rt = this.insertScanData(null, sd);
					logger.info("#MLOG /scanner/sendSignal/ by scanner info :"+ sd.getUuid() + "&&"+sd.getUuid2() + " result :"+rt.getRtCode());
					System.out.println("#MLOG /scanner/sendSignal/ by scanner info :"+ sd.getUuid() + "&&"+sd.getUuid2() + " result :"+rt.getRtCode());
			
					rtCode = 1;
				}
			}
		} catch (Exception e) {
			throw new Exception("insert ScanData error!");
		}
		return rtCode;
	}
}
