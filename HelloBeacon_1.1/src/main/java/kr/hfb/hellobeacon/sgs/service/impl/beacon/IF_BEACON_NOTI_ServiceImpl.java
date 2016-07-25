package kr.hfb.hellobeacon.sgs.service.impl.beacon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.util.StringUtil;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;
import kr.hfb.hellobeacon.sgs.service.AbsctractSgsService;

@Service("IF_BEACON_NOTI_Service")
public class IF_BEACON_NOTI_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;

	@Value("#{config['server.domain']}")
	private String domain = null;

	public IF_BEACON_NOTI_ServiceImpl() {
		super.paramKey = new String[] {};
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		String beaconCode = "";

		if (null != parameters.get("uu")) {
			beaconCode += (String) parameters.get("uu");
		}

		if (null != parameters.get("mj")) {
			beaconCode += (String) parameters.get("mj");
		}

		if (null != parameters.get("mi")) {
			beaconCode += (String) parameters.get("mi");
		}
		if (null != parameters.get("n")) {
			beaconCode += (String) parameters.get("n");
		}

		if (null != parameters.get("i")) {
			beaconCode += (String) parameters.get("i");
		}

		if (null != parameters.get("m")) {
			beaconCode += (String) parameters.get("m");
		}

		if (null != parameters.get("bi")) {
			beaconCode += (String) parameters.get("bi");
		}

		if (null != parameters.get("beaconCode")) {
			beaconCode += (String) parameters.get("beaconCode");
		}

		if (beaconCode != "") {
			parameters.put("beaconCode", beaconCode);
		}
		CommonBean bean = sgsDao.execute(parameters, "beacon.selectBeaconNoti", "select");

		if (bean.getList().size() > 0) {
			bean.getList().get(0).put("url", domain + "r?beaconCode=" + beaconCode);
			if (null == bean.getList().get(0).get("icon")) {
				bean.getList().get(0).put("icon", domain + "img/HelloFactory_CI.png");
			}
			bean.getList().get(0).put("result", 0);
		} else {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", 999);
			list.add(map);
			bean.setList(list);
		}
		return bean;
	}

	@Override
	public String execute(Map<String, Object> parameters) {
		return null;
	}
}
