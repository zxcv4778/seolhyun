package kr.hfb.hellobeacon.common.util;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import kr.hfb.hellobeacon.common.bean.Email;

@Component("emailSender")
public class EmailSender {

	@Inject
	@Named("javaMailSender")
	private JavaMailSender javaMailSender;

	public void SendEmail(Email email) throws Exception {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		messageHelper.setTo(email.getReciver());
		messageHelper.setText(email.getContent());
		messageHelper.setFrom(email.getSender());
		messageHelper.setSubject(email.getSubject());
		javaMailSender.send(message);
	}

}
