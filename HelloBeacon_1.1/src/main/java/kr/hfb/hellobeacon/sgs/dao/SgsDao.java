package kr.hfb.hellobeacon.sgs.dao;

import java.util.List;
import java.util.Map;

import kr.hfb.hellobeacon.common.bean.CommonBean;

public interface SgsDao {
	public CommonBean execute(Map<String, Object> parameters, String queryId, String type);

	public List<Map<String, Object>> execute(Map<String, Object> parameters, String queryNamespace, String queryId, String type);
}
