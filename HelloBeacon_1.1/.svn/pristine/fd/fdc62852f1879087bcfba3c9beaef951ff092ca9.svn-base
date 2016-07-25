package kr.hfb.hellobeacon.sgs.service.impl.member;

import java.util.HashMap;
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
import kr.hfb.hellobeacon.common.util.StringUtil;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;
import kr.hfb.hellobeacon.sgs.service.AbsctractSgsService;

@Service("IF_MEMBER_UPDATE_INFO_Service")
public class IF_MEMBER_UPDATE_INFO_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;

	@Inject
	@Named("mybatisTxManager")
	private DataSourceTransactionManager txManager = null;

	public IF_MEMBER_UPDATE_INFO_ServiceImpl() {
		super.paramKey = new String[] { "memberEmail", "memberPassword", "authKey", "memberName" };
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		Map<String, Object> param = new HashMap<String, Object>();

		CommonBean bean = new CommonBean();
		
		String memberPassword = parameters.get("memberPassword").toString();
		parameters.put("memberPassword", StringUtil.getDigest(memberPassword));
		
		TransactionStatus transactionStatus = CommonUtils.getTransactionStatus(txManager);
		try {
			sgsDao.execute(parameters, "member.updateMember", "update");
			txManager.commit(transactionStatus);
		} catch (Exception e) {
			txManager.rollback(transactionStatus);
			throw new CommonRunTimeException("954");
		}
		bean.setMessage(LogMessageUtil.getInfoLogMessage(false, "000", ""));
		return bean;
	}
	
	@Override
	public String execute(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
