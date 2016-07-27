package kr.hfb.hellobeacon.order.dao;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.order.domain.Order;

public interface OrderDao {
	public Order selectOrder(Order req);
	public int updateOrder(Order req);
	public int insertOrder(Order req);
	public int deleteOrder( Order req );
	public JSONArray selectAll(Order req);
}