/*package test;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.hfb.hellobeacon.common.util.AppContextUtil;
import kr.hfb.hellobeacon.common.util.DateUtil;
import kr.hfb.hellobeacon.order.dao.OrderDao;
import kr.hfb.hellobeacon.order.domain.Order;
import kr.hfb.hellobeacon.order.service.OrderService;
import kr.hfb.hellobeacon.scan.dao.ScanDao;
import kr.hfb.hellobeacon.scan.domain.ScanData;
import kr.hfb.hellobeacon.scan.domain.Scanner;
import kr.hfb.hellobeacon.scan.service.ScanService;
import kr.hfb.hellobeacon.sgs.service.SgsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring/root-context.xml"
		, "file:src/main/resources/spring/context-threadPool.xml"})
public class OrderTest {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void test() {
		Order  req = new Order();
		req.setIdx(12);
		Order result = orderService.selectOrderOne(req);
		System.out.println("### result : "+result.toString());
	}
	
	/*@Test
	public void test_insertOrderOne() {
		
		Order req = new Order();
		req.setItemName(itemName);
		int result = orderDao.insertOrder(req);
		
		System.out.println("#MLOG result : "+result);
	}*/
