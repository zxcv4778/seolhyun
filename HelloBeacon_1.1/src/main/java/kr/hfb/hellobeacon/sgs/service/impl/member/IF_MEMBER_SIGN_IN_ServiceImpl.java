package kr.hfb.hellobeacon.sgs.service.impl.member;

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
import kr.hfb.hellobeacon.common.exception.impl.CommonRunTimeException;
import kr.hfb.hellobeacon.common.util.CommonUtils;
import kr.hfb.hellobeacon.common.util.StringUtil;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;
import kr.hfb.hellobeacon.sgs.service.AbsctractSgsService;

@Service("IF_MEMBER_SIGN_IN_Service")
public class IF_MEMBER_SIGN_IN_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;

	@Inject
	@Named("mybatisTxManager")
	private DataSourceTransactionManager txManager = null;

	public IF_MEMBER_SIGN_IN_ServiceImpl() {
		super.paramKey = new String[] { "memberEmail", "memberPassword" };
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		String authKey = null;

		String memberEmail = parameters.get("memberEmail").toString();
		parameters.put("memberEmail", memberEmail);
		String memberPassword = parameters.get("memberPassword").toString();
		parameters.put("memberPassword", StringUtil.getDigest(memberPassword));
		
		logger.info("## sign_in Parameters :: memberEmail : "+memberEmail+ " && memberPassword "+memberPassword);
		
		CommonBean bean = sgsDao.execute(parameters, "member.selectMember", "select");

		if (bean.getList().size() > 0) {
			if ("Y".equals(bean.getList().get(0).get("authYn"))) {
				authKey = UUID.randomUUID().toString();
				parameters.put("authKey", authKey);

				TransactionStatus transactionStatus = CommonUtils.getTransactionStatus(txManager);
				try {
					sgsDao.execute(parameters, "member.updateMemberAuthkey", "update");
					txManager.commit(transactionStatus);
				} catch (Exception e) {
					txManager.rollback(transactionStatus);
					throw new CommonRunTimeException("953");
				}
			} else {
				throw new CommonRunTimeException("021");
			}
		} else {
			throw new CommonRunTimeException("020");
		}
		
		bean = sgsDao.execute(parameters, "member.selectMember", "select");
		bean.setMessage(null);
		return bean;
	}

	@Override
	public String execute(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
