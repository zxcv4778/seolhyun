package kr.hfb.hellobeacon.sgs.service.impl.beacon;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.exception.impl.CommonRunTimeException;
import kr.hfb.hellobeacon.common.util.CommonUtils;
import kr.hfb.hellobeacon.common.util.LogMessageUtil;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;
import kr.hfb.hellobeacon.sgs.service.AbsctractSgsService;

@Service("IF_BEACON_GOOD_Service")
public class IF_BEACON_GOOD_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;

	@Value("#{config['server.domain']}")
	private String domain = null;

	@Value("#{config['server.physical.filepath']}")
	private String beaconPhysicalFilePath = null;

	@Value("#{config['server.logical.filepath']}")
	private String beaconLogicalFilePath = null;

	@Inject
	@Named("mybatisTxManager")
	private DataSourceTransactionManager txManager = null;

	public IF_BEACON_GOOD_ServiceImpl() {
		super.paramKey = new String[] {"beaconId"};
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		CommonBean bean = new CommonBean();
		
		TransactionStatus transactionStatus = CommonUtils.getTransactionStatus(txManager);
		try {
			sgsDao.execute(parameters, "beacon.updateBeaconRecommendCount", "update");
			txManager.commit(transactionStatus);
		}catch(Exception e){
			txManager.rollback(transactionStatus);
			throw new CommonRunTimeException("952");
		}
		
		List<Map<String, Object>> beaconList = sgsDao.execute(parameters, "beacon", "selectBeacon", "select");
		bean.setMessage(LogMessageUtil.getInfoLogMessage(false, "000", ""));
		bean.setList(beaconList);
		
		return bean;
	}

	@Override
	public String execute(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
