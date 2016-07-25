package kr.hfb.hellobeacon.scan.dao;

import java.util.List;
import java.util.Map;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.scan.domain.ScanData;
import kr.hfb.hellobeacon.scan.domain.Scanner;

public interface ScanDao {
	public ScanData execute(Map<String, Object> parameters, String queryId, String type);
	public ScanData execute(ScanData parameters, String queryId, String type);
	public Scanner execute(Scanner parameters, String queryId, String type);
	
	public List<Map<String, Object>> execute(Map<String, Object> parameters, String queryNamespace, String queryId, String type);

}
