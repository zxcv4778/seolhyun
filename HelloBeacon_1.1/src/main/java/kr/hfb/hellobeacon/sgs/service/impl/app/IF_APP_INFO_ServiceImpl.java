package kr.hfb.hellobeacon.sgs.service.impl.app;

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

@Service("IF_APP_INFO_Service")
public class IF_APP_INFO_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;

	public IF_APP_INFO_ServiceImpl() {
		super.paramKey = new String[] {};
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		return sgsDao.execute(parameters, "appInfo.selectAppInfo", "select");
	}

	@Override
	public String execute(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
