package kr.hfb.hellobeacon.order.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.pool.concurrent.mgr.GenericTaskThreadPoolExecutor;
import kr.hfb.hellobeacon.common.pool.concurrunt.task.JobTask;
import kr.hfb.hellobeacon.common.pool.concurrunt.task.arg.GenericTaskArgument;
import kr.hfb.hellobeacon.common.util.AppContextUtil;
import kr.hfb.hellobeacon.common.util.DateUtil;
import kr.hfb.hellobeacon.order.domain.Order;
import kr.hfb.hellobeacon.order.service.OrderService;
import kr.hfb.hellobeacon.scan.domain.ScanData;
import kr.hfb.hellobeacon.scan.domain.Scanner;
import kr.hfb.hellobeacon.scan.service.ScanService;
import kr.hfb.hellobeacon.sgs.service.SgsService;

//@Controller("scanController")
@Controller
@RequestMapping(value = {"", "/order"})
public class OrderController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrderService orderService;
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	//@ResponseBody
	public String getTest(Model model
		, HttpServletRequest request, HttpServletResponse response) {
		logger.info("#MLOG /test");
		System.out.println("test왔다네 ");
		Order req = new Order();
		req.setIdx(1);
		Order result = orderService.selectOrderOne(req);
		model.addAttribute("orderOne", result);
		return "/order/test";			
	}
	
	@RequestMapping(value="/test2", method = RequestMethod.POST)
	public int test2(Order vo){
		logger.info("test");
		System.out.println("OrderController::test2");
		int result = orderService.insertOne(vo);
		System.out.println("result:"+result);
		return result;
	}
	
	@RequestMapping(value="/test3", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray test3(Order vo){
		logger.info("test");
		System.out.println("OrderController::test3");
		JSONArray arr = new JSONArray();
		arr=orderService.selectAll(vo);
		System.out.println("arr:"+arr);
		return arr;
		//return orderService.selectAll();
	} 
}