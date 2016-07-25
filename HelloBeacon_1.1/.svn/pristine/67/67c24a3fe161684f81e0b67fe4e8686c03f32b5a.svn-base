package kr.hfb.hellobeacon.sgs.service.impl.member;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.bean.Email;
import kr.hfb.hellobeacon.common.exception.impl.CommonRunTimeException;
import kr.hfb.hellobeacon.common.util.CommonUtils;
import kr.hfb.hellobeacon.common.util.EmailSender;
import kr.hfb.hellobeacon.common.util.LogMessageUtil;
import kr.hfb.hellobeacon.common.util.StringUtil;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;
import kr.hfb.hellobeacon.sgs.service.AbsctractSgsService;

@Service("IF_MEMBER_SIGN_UP_Service")
public class IF_MEMBER_SIGN_UP_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;

	@Inject
	@Named("emailSender")
	private EmailSender emailSender;

	@Inject
	@Named("mybatisTxManager")
	private DataSourceTransactionManager txManager = null;

	@Value("#{config['server.domain']}")
	private String domain = null;

	@Value("#{config['mail.id']}")
	private String senderEmail = null;

	public IF_MEMBER_SIGN_UP_ServiceImpl() {
		super.paramKey = new String[] { "memberEmail", "memberPassword", "memberName", "membershipType" };
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		CommonBean bean = sgsDao.execute(parameters, "member.selectMemberUseMemberEmail", "select");

		//이메일 중복 발생으로 인한 대한 방어코드 추가
		if(bean.getList().size() > 0){
			//throw new CommonRunTimeException("005");
		}
		
		parameters.put("authKey", UUID.randomUUID().toString());
		parameters.put("memberType", 'C');

		if ("W".equals(parameters.get("membershipType"))) {
			parameters.put("authYn", "N");
		} else {
			parameters.put("authYn", "Y");
		}
		String memberEmail = parameters.get("memberEmail").toString();
		String memberName = parameters.get("memberName").toString();
		String memberPassword = parameters.get("memberPassword").toString();
		parameters.put("memberPassword", StringUtil.getDigest(memberPassword));

		TransactionStatus transactionStatus = CommonUtils.getTransactionStatus(txManager);
		try {
			sgsDao.execute(parameters, "member.insertMember", "insert");

			if ("W".equals(parameters.get("membershipType"))) {
				Email email = new Email();
				email.setReciver(memberEmail);
				email.setSender(senderEmail);
				email.setSubject(LogMessageUtil.getInfoLogMessage(false, "001", memberName));
				email.setContent(LogMessageUtil.getInfoLogMessage(false, "002", domain + "member/auth.json?authKey=" + parameters.get("authKey")));
				emailSender.SendEmail(email);
			}
			txManager.commit(transactionStatus);
		} catch (DuplicateKeyException e) {
			//txManager.rollback(transactionStatus);
			//throw new CommonRunTimeException("005");
		} catch (MailSendException e) {
			txManager.rollback(transactionStatus);
			throw new CommonRunTimeException("004");
		} catch (Exception e) {
			txManager.rollback(transactionStatus);
			throw new CommonRunTimeException("952");
		}

		Map<String, Object> statisticsParameters = new HashMap<String, Object>();
		statisticsParameters.put("statisticsId", UUID.randomUUID().toString());
		statisticsParameters.put("memberEmail", memberEmail);
		statisticsParameters.put("statisticsType", "R");
		transactionStatus = CommonUtils.getTransactionStatus(txManager);
		try {
			sgsDao.execute(statisticsParameters, "statistics.insertStatisticsMember", "insert");
			txManager.commit(transactionStatus);
		} catch (Exception e) {
			txManager.rollback(transactionStatus);
		}
		bean = new CommonBean();
		bean.setMessage(LogMessageUtil.getInfoLogMessage(false, "003", ""));
		return bean;
	}

	@Override
	public String execute(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
