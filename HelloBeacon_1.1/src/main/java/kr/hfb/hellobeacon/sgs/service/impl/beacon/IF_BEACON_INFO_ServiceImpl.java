package kr.hfb.hellobeacon.sgs.service.impl.beacon;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;
import kr.hfb.hellobeacon.sgs.service.AbsctractSgsService;

@Service("IF_BEACON_INFO_Service")
public class IF_BEACON_INFO_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;

	public IF_BEACON_INFO_ServiceImpl() {
		super.paramKey = new String[] {"memberEmail", "authKey", "beaconId"};
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		CommonBean bean = new CommonBean();
		List<Map<String, Object>> beaconList = sgsDao.execute(parameters, "beacon", "selectBeacon", "select");
		parameters.put("fileType", "ICO");
		List<Map<String, Object>> iconList = sgsDao.execute(parameters, "beacon", "selectBeaconFileUseBeaconId", "select");
		parameters.put("fileType", "IMG");
		List<Map<String, Object>> imageList = sgsDao.execute(parameters, "beacon", "selectBeaconFileUseBeaconId", "select");
		
		beaconList.get(0).put("iconList", iconList);
		beaconList.get(0).put("imageList", imageList);
		bean.setList(beaconList);
		return bean;
	}
	
	@Override
	public String execute(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
