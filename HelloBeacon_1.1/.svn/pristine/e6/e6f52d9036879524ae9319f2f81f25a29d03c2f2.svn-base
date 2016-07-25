package kr.hfb.hellobeacon.sgs.service.impl.member;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

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

@Service("IF_MEMBER_AUTH_Service")
public class IF_MEMBER_AUTH_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;

	@Inject
	@Named("mybatisTxManager")
	private DataSourceTransactionManager txManager = null;

	public IF_MEMBER_AUTH_ServiceImpl() {
		super.paramKey = new String[] { "authKey" };
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		CommonBean bean = new CommonBean();

		bean = sgsDao.execute(parameters, "member.selectMemberUseAuthkey", "select");
		Integer count = (Integer) bean.getList().size();

		if (1 == count) {
			parameters.put("authYn", "Y");

			TransactionStatus transactionStatus = CommonUtils.getTransactionStatus(txManager);
			try {
				sgsDao.execute(parameters, "member.updateMemberAuthYn", "update");
				txManager.commit(transactionStatus);
			} catch (Exception e) {
				txManager.rollback(transactionStatus);
				bean.setCode("011");
				bean.setMessage(LogMessageUtil.getInfoLogMessage(false, "953", ""));
				bean.setDetailMessage(e.getMessage());
				logger.error("[errorCode : " + bean.getCode() + "] [message : " + bean.getMessage() + "] [detailMessage : " + bean.getDetailMessage() + "]");
				
			}
		} else {
			bean.setCode("011");
			bean.setMessage(LogMessageUtil.getInfoLogMessage(false, "011", ""));
			bean.setDetailMessage(LogMessageUtil.getInfoLogMessage(false, "011", ""));
			logger.error("[errorCode : " + bean.getCode() + "] [message : " + bean.getMessage() + "] [detailMessage : " + bean.getDetailMessage() + "]");
		}
		
		return bean;
	}

	@Override
	public String execute(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
