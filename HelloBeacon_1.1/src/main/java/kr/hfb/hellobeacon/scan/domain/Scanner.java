package kr.hfb.hellobeacon.scan.domain;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Scanner {
	@Override
    public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	private long idx;
	private String auth_id;
	private String type_code;
	private String scanner_id;
    private String uuid;
    private Date reg_date;
    private Date upt_date;
    private String mac_address;
    private String status;
    
    private String resv1;
    private String resv2;
    private String resv3;
    private long resv4;
    private Date resv5;
    
    private int rtCode;
    
    private String params;

    
    
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public String getAuthId() {
		return auth_id;
	}

	public void setAuthId(String auth_id) {
		this.auth_id = auth_id;
	}

	public String getTypeCode() {
		return type_code;
	}

	public void setTypeCode(String type_code) {
		this.type_code = type_code;
	}

	public String getScannerId() {
		return scanner_id;
	}

	public void setScannerId(String scanner_id) {
		this.scanner_id = scanner_id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getRegDate() {
		return reg_date;
	}

	public void setRegDate(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getUptDate() {
		return upt_date;
	}

	public void setUptDate(Date upt_date) {
		this.upt_date = upt_date;
	}

	public String getMacAddress() {
		return mac_address;
	}

	public void setMacAddress(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getResv1() {
		return resv1;
	}

	public void setResv1(String resv1) {
		this.resv1 = resv1;
	}

	public String getResv2() {
		return resv2;
	}

	public void setResv2(String resv2) {
		this.resv2 = resv2;
	}

	public String getResv3() {
		return resv3;
	}

	public void setResv3(String resv3) {
		this.resv3 = resv3;
	}

	public long getResv4() {
		return resv4;
	}

	public void setResv4(long resv4) {
		this.resv4 = resv4;
	}

	public Date getResv5() {
		return resv5;
	}

	public void setResv5(Date resv5) {
		this.resv5 = resv5;
	}

	public int getRtCode() {
		return rtCode;
	}

	public void setRtCode(int rtCode) {
		this.rtCode = rtCode;
	}    
    
    
}
