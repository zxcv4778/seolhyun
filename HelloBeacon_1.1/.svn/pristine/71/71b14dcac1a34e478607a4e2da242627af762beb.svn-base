package kr.hfb.hellobeacon.sgs.service.impl.member;

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
import kr.hfb.hellobeacon.common.bean.Email;
import kr.hfb.hellobeacon.common.exception.impl.CommonRunTimeException;
import kr.hfb.hellobeacon.common.util.CommonUtils;
import kr.hfb.hellobeacon.common.util.EmailSender;
import kr.hfb.hellobeacon.common.util.LogMessageUtil;
import kr.hfb.hellobeacon.common.util.StringUtil;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;
import kr.hfb.hellobeacon.sgs.service.AbsctractSgsService;

@Service("IF_MEMBER_FORGET_PASSWORD_Service")
public class IF_MEMBER_FORGET_PASSWORD_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;

	@Inject
	@Named("emailSender")
	private EmailSender emailSender;

	@Value("#{config['mail.id']}")
	private String senderEmail = null;

	@Inject
	@Named("mybatisTxManager")
	private DataSourceTransactionManager txManager = null;

	public IF_MEMBER_FORGET_PASSWORD_ServiceImpl() {
		super.paramKey = new String[] { "memberEmail" };
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		CommonBean bean = sgsDao.execute(parameters, "member.selectMemberUseMemberEmail", "select");

		if (bean.getList().size() == 1) {
			
			if("W".equals(bean.getList().get(0).get("membershipType"))){
				String memberName = (String) bean.getList().get(0).get("memberName");
				String password = StringUtil.getRandomPassword(8);
				parameters.put("memberPassword", StringUtil.getDigest(password));

				TransactionStatus transactionStatus = CommonUtils.getTransactionStatus(txManager);
				try {

					sgsDao.execute(parameters, "member.updateMemberPassword", "update");
					txManager.commit(transactionStatus);

					Email email = new Email();
					String receiverEmail = (String) parameters.get("memberEmail");
					email.setReciver(receiverEmail);
					email.setSender(senderEmail);
					email.setSubject(LogMessageUtil.getInfoLogMessage(false, "023", memberName));
					email.setContent(LogMessageUtil.getInfoLogMessage(false, "024", password));
					emailSender.SendEmail(email);
				} catch (Exception e) {
					txManager.rollback(transactionStatus);
					throw new CommonRunTimeException("953");
				}
				bean.setMessage(LogMessageUtil.getInfoLogMessage(false, "026", ""));
				return bean;
			} else {
				throw new CommonRunTimeException("027");
			}
			
		} else {
			throw new CommonRunTimeException("025");
		}
	}

	@Override
	public String execute(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
