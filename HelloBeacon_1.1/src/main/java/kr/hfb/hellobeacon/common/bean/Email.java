package kr.hfb.hellobeacon.common.bean;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Email {
	private String subject;
	private String content;
	private String regdate;
	private String reciver;
	private String sender;

	public String getReciver() {
		return reciver;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Email [subject=");
		builder.append(subject);
		builder.append(", content=");
		builder.append(content);
		builder.append(", regdate=");
		builder.append(regdate);
		builder.append(", reciver=");
		builder.append(reciver);
		builder.append(", sender=");
		builder.append(sender);
		builder.append("]");
		return builder.toString();
	}

}
