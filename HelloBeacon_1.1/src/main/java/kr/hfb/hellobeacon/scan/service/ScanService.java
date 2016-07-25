package kr.hfb.hellobeacon.scan.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import kr.hfb.hellobeacon.scan.domain.ScanData;
import kr.hfb.hellobeacon.scan.domain.Scanner;

public interface ScanService {

	public void preExecute(Map<String, Object> parameters);

	public ScanData selectScanData(HttpServletRequest request, Map<String, Object> parameters);
	public ScanData selectFilteredScanData(HttpServletRequest request, ScanData parameters);
	
	public ScanData insertScanData(HttpServletRequest request, ScanData parameters);
	
	public Scanner selectScanner(HttpServletRequest request, Scanner parameters);
	public Scanner insertScanner(HttpServletRequest request, Scanner parameters);
	public Scanner updateScanner(HttpServletRequest request, Scanner parameters);
	public Scanner deleteScanner(HttpServletRequest request, Scanner parameters);

}
