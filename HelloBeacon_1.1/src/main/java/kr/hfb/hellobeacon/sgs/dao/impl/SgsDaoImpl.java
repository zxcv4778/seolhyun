package kr.hfb.hellobeacon.sgs.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.exception.impl.CommonRunTimeException;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;

@Repository("sgsDao")
public class SgsDaoImpl implements SgsDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate = null;

	public CommonBean execute(Map<String, Object> parameters, String queryId, String type) {
		CommonBean bean = new CommonBean();
		List<Map<String, Object>> list = null;

		if ("insert".equals(type)) {
			sqlSessionTemplate.insert(queryId, parameters);
		} else if ("update".equals(type)) {
			sqlSessionTemplate.update(queryId, parameters);
		} else if ("delete".equals(type)) {
			sqlSessionTemplate.delete(queryId, parameters);
		} else if ("select".equals(type)) {
			list = sqlSessionTemplate.selectList(queryId, parameters);
			bean.setList(list);
		} else {
			throw new CommonRunTimeException("999");
		}

		return bean;

	}

	@Override
	public List<Map<String, Object>> execute(Map<String, Object> parameters, String queryNamespace, String queryId, String type) {
		List<Map<String, Object>> list = null;

		if ("insert".equals(type)) {
			sqlSessionTemplate.insert(queryId, parameters);
		} else if ("update".equals(type)) {
			sqlSessionTemplate.update(queryId, parameters);
		} else if ("delete".equals(type)) {
			sqlSessionTemplate.delete(queryId, parameters);
		} else if ("select".equals(type)) {
			list = sqlSessionTemplate.selectList(queryId, parameters);
		} else {
			throw new CommonRunTimeException("999");
		}

		return list;
	}
}
