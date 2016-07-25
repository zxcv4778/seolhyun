package kr.hfb.hellobeacon.sgs.service.impl.beacon;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.util.CommonUtils;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;
import kr.hfb.hellobeacon.sgs.service.AbsctractSgsService;

@Service("IF_BEACON_PAGE_Service")
public class IF_BEACON_PAGE_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;
	
	@Inject
	@Named("mybatisTxManager")
	private DataSourceTransactionManager txManager = null;

	public IF_BEACON_PAGE_ServiceImpl() {
		super.paramKey = new String[] {};
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		CommonBean bean = sgsDao.execute(parameters, "beaconPage.selectBeaconPage", "select");
		parameters.put("fileType", "ICO");
		CommonBean iconFileBean = sgsDao.execute(parameters, "beacon.selectBeaconFileUseBeaconId", "select");
		parameters.put("fileType", "IMG");
		CommonBean imageFileBean = sgsDao.execute(parameters, "beacon.selectBeaconFileUseBeaconId", "select");
		if (iconFileBean.getList().size() != 0) {
			bean.getList().get(0).put("iconFile", iconFileBean);
		}
		if (imageFileBean.getList().size() != 0) {
			bean.getList().get(0).put("imageFile", imageFileBean);
		}
		return bean;
	}

	@Override
	public String execute(Map<String, Object> parameters) {
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

		parameters.put("beaconCode", beaconCode);
		CommonBean bean = sgsDao.execute(parameters, "beaconPage.selectBeaconPage", "select");
		
		if (bean.getList().size() != 1) {
			return null;
		}

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<HTML>");
		stringBuilder.append("<HEAD>");
		stringBuilder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		stringBuilder.append("<meta name=\"viewport\" content=\"user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width\" />");
		stringBuilder.append("<link href=\"../css/mobile.css\" type=\"text/css\" rel=\"stylesheet\" />");
		stringBuilder.append("<script type=\"text/javascript\" src=\"../js/jquery-2.1.4.min.js\" ></script>");
		stringBuilder.append("<script type=\"text/javascript\" src=\"../js/mobile_common.js\" ></script>");
		stringBuilder.append("<script type=\"text/javascript\">gfn_initialize_page('" + bean.getList().get(0).get("beaconId") + "')</script>");
		stringBuilder.append("<TITLE></TITLE>");
		stringBuilder.append("</HEAD>");
		stringBuilder.append("<BODY></BODY>");
		stringBuilder.append("</HTML>");
		
		Map<String, Object> statisticsParameters = new HashMap<String, Object>();
		statisticsParameters.put("statisticsId", UUID.randomUUID().toString());
		statisticsParameters.put("accessUrl", parameters.get("requestUrl"));
		statisticsParameters.put("ipAddress", parameters.get("ipAddress"));
		statisticsParameters.put("beaconCode", parameters.get("beaconCode"));
		
		TransactionStatus transactionStatus = CommonUtils.getTransactionStatus(txManager);
		try {
			sgsDao.execute(statisticsParameters, "statistics.insertStatisticsAccess", "insert");
			txManager.commit(transactionStatus);
		} catch (Exception e) {
			txManager.rollback(transactionStatus);
		}
		
		return stringBuilder.toString();
	}
}
