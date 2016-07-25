package kr.hfb.hellobeacon.order.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.order.domain.Order;

public interface OrderService {
	public Order selectOrderOne(Order req);
	public int updateOne(Order req);
	public int insertOne(Order req);
	public JSONArray selectAll(Order req);
}