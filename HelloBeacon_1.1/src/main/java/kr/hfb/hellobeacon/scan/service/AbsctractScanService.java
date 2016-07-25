package kr.hfb.hellobeacon.scan.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

import kr.hfb.hellobeacon.common.exception.impl.CommonRunTimeException;
import kr.hfb.hellobeacon.common.util.CommonUtils;
import kr.hfb.hellobeacon.common.util.LogMessageUtil;
import kr.hfb.hellobeacon.common.util.StringUtil;
import kr.hfb.hellobeacon.scan.dao.ScanDao;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;

public abstract class AbsctractScanService implements ScanService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("scanDao")
	private ScanDao scanDao = null;

	@Inject
	@Named("mybatisTxManager")
	private DataSourceTransactionManager txManager = null;

	protected String[] paramKey = null;
	protected String packageName = null;

	public String[] getRequestedParams() {
		return paramKey;
	}

	public void preExecute(Map<String, Object> parameters) {
		if (getRequestedParams() != null) {
			for (String param : getRequestedParams()) {
				if (parameters.get(param) == null) {
					throw new CommonRunTimeException("900", "파라미터 명 : " + param);
				}
			}
		}
	}

	// protected void insertLog(String message) {
	// Map<String, Object> logMap = new HashMap<String, Object>();
	// logMap.put("pkgName", this.packageName);
	// logMap.put("message", message);
	// logMap.put("accessDatetime", new Date());
	// logMap.put("logId", StringUtil.getRandomGUID());
	//
	// TransactionStatus transactionStatus =
	// CommonUtils.getTransactionStatus(txManager);
	//
	// try {
	// sgsDao.execute(logMap, "log.insertLog", true);
	// txManager.commit(transactionStatus);
	// } catch (Exception e) {
	// logger.info("LOG INSERT ERROR");
	// txManager.rollback(transactionStatus);
	// }
	// }

}
