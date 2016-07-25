package kr.hfb.hellobeacon.sgs.service.impl.beacon;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.exception.impl.CommonRunTimeException;
import kr.hfb.hellobeacon.common.util.CommonUtils;
import kr.hfb.hellobeacon.common.util.LogMessageUtil;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;
import kr.hfb.hellobeacon.sgs.service.AbsctractSgsService;

@Service("IF_BEACON_DELETE_Service")
public class IF_BEACON_DELETE_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;

	@Inject
	@Named("mybatisTxManager")
	private DataSourceTransactionManager txManager = null;

	public IF_BEACON_DELETE_ServiceImpl() {
		super.paramKey = new String[] { "memberEmail", "beaconId" };
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		CommonBean bean = new CommonBean();

		CommonBean beaconBean = sgsDao.execute(parameters, "beacon.selectBeacon", "select");

		bean = sgsDao.execute(parameters, "beacon.selectBeaconFileUseBeaconId", "select");

		TransactionStatus transactionStatus = CommonUtils.getTransactionStatus(txManager);
		try {
			sgsDao.execute(parameters, "beacon.deleteBeaconFile", "delete");
			sgsDao.execute(parameters, "beacon.deleteBeacon", "delete");
			txManager.commit(transactionStatus);
		} catch (Exception e) {
			txManager.rollback(transactionStatus);
			throw new CommonRunTimeException("954");
		}

		try {
			File parentFile = null;

			for (Map<String, Object> map : bean.getList()) {
				File file = new File(map.get("physicalPath").toString());
				parentFile = file.getParentFile();
				if (file.exists()) {
					file.delete();
				}
			}

			if(null != parentFile){
				FileUtils.deleteDirectory(parentFile);
			}

		} catch (Exception e) {
			throw new CommonRunTimeException("954");
		}

		Map<String, Object> statisticsParameters = new HashMap<String, Object>();
		statisticsParameters.put("statisticsId", UUID.randomUUID().toString());
		statisticsParameters.put("typeCode", beaconBean.getList().get(0).get("typeCode"));
		statisticsParameters.put("memberEmail", parameters.get("memberEmail").toString());
		statisticsParameters.put("statisticsType", "D");
		transactionStatus = CommonUtils.getTransactionStatus(txManager);
		try {
			sgsDao.execute(statisticsParameters, "statistics.insertStatisticsBeacon", "insert");
			txManager.commit(transactionStatus);
		} catch (Exception e) {
			txManager.rollback(transactionStatus);
		}
		bean.setMessage(LogMessageUtil.getInfoLogMessage(false, "032", ""));
		return bean;
	}

	@Override
	public String execute(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
