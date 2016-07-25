package kr.hfb.hellobeacon.order.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import kr.hfb.hellobeacon.common.exception.impl.CommonRunTimeException;
import kr.hfb.hellobeacon.common.util.CommonUtils;
import kr.hfb.hellobeacon.common.util.LogMessageUtil;
import kr.hfb.hellobeacon.common.util.StringUtil;
import kr.hfb.hellobeacon.order.dao.OrderDao;
import kr.hfb.hellobeacon.order.domain.Order;
import kr.hfb.hellobeacon.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	private OrderDao orderDao;

	@Inject
	@Named("mybatisTxManager")
	private DataSourceTransactionManager txManager = null;
	
	public void OrderServiceImpl() {}
	
	public Order selectOrderOne(Order req) {
		return orderDao.selectOrder(req);
	}
	
	public int updateOne(Order req){
		return 0;
	}
	public int insertOne(Order req){
		System.out.println("Serivce::updateOrder");
		return orderDao.insertOrder(req);
	}
	public JSONArray selectAll(Order req){
		System.out.println("Service::selectAll");
		return orderDao.selectAll(req);
	}
}
