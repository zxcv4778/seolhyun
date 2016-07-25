package kr.hfb.hellobeacon.common.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CommonBean {

	// 리턴 메시지용 변수
	private String code = "000";
	private String message = "성공";
	private String detailMessage = "정상적으로 처리 되었습니다.";
	
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommonBean [code=");
		builder.append(code);
		builder.append(", message=");
		builder.append(message);
		builder.append(", detailMessage=");
		builder.append(detailMessage);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}
}
