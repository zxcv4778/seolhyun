package kr.hfb.hellobeacon.order.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;







import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.exception.impl.CommonRunTimeException;
import kr.hfb.hellobeacon.order.dao.OrderDao;
import kr.hfb.hellobeacon.order.domain.Order;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate = null;

	public Order selectOrder(Order req) {
		return sqlSessionTemplate.selectOne("selectOrder", req);
	}
	
	public int updateOrder(Order req){
		
		return 0;
	}
	
	public int insertOrder(Order req){
		int res;
		System.out.println("DAO:::updateOrder");
		System.out.println("req.content :"+req.getTitle());
		System.out.println("req.content :"+req.getContent());
		System.out.println("req.author :"+req.getAuthor());
		res = sqlSessionTemplate.insert("insertOrder",req);
		System.out.println("res : "+res);
		return res;
	}
	
	public JSONArray selectAll(Order req){
		//JSONArray arr = new JSONArray();
		//System.out.println("arr : "+arr);
		System.out.println("DAO:::selectAll");
		return JSONArray.fromObject(sqlSessionTemplate.selectList("selectAllList"));
	}
}