package kr.hfb.hellobeacon.scan.dao.impl;

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
import kr.hfb.hellobeacon.scan.dao.ScanDao;
import kr.hfb.hellobeacon.scan.domain.ScanData;
import kr.hfb.hellobeacon.scan.domain.Scanner;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;

@Repository("scanDao")
public class ScanDaoImpl implements ScanDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate = null;

	public ScanData execute(Map<String, Object> parameters, String queryId, String type) {
		ScanData bean = new ScanData();
		List<ScanData> list = null;
		int rtCode = 0;
		
		if ("insert".equals(type)) {
			rtCode = sqlSessionTemplate.insert(queryId, parameters);
		} else if ("update".equals(type)) {
			rtCode = sqlSessionTemplate.update(queryId, parameters);
		} else if ("delete".equals(type)) {
			rtCode = sqlSessionTemplate.delete(queryId, parameters);
		} else if ("selectList".equals(type)) {
			list = sqlSessionTemplate.selectList(queryId, parameters);
			bean.setScanList(list);
		} else if ("select".equals(type)) {
			bean = sqlSessionTemplate.selectOne(queryId, parameters);
		} else {
			throw new CommonRunTimeException("999");
		}

		bean.setRtCode(rtCode);
		return bean;

	}
	
	@Override
	public ScanData execute(ScanData parameters, String queryId, String type) {
		ScanData bean = new ScanData();
		List<ScanData> list = null;
		int rtCode = 0;
		
		if ("insert".equals(type)) {
			rtCode = sqlSessionTemplate.insert(queryId, parameters);
		} else if ("update".equals(type)) {
			rtCode = sqlSessionTemplate.update(queryId, parameters);
		} else if ("delete".equals(type)) {
			rtCode = sqlSessionTemplate.delete(queryId, parameters);
		} else if ("selectList".equals(type)) {
			list = sqlSessionTemplate.selectList(queryId, parameters);
			bean.setScanList(list);
		} else if ("select".equals(type)) {
			bean = sqlSessionTemplate.selectOne(queryId, parameters);
		} else if ("selectList".equals(type)) {
			list = sqlSessionTemplate.selectList(queryId, parameters);
			bean.setScanList(list);
		} else {
			throw new CommonRunTimeException("999");
		}

		bean.setRtCode(rtCode);
		return bean;

	}

	@Override
	public List<Map<String, Object>> execute(Map<String, Object> parameters, String queryNamespace, String queryId,
			String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scanner execute(Scanner parameters, String queryId, String type) {
		Scanner bean = new Scanner();
		List<Map<String, Object>> list = null;
		int rtCode = 0;
		
		if ("insert".equals(type)) {
			rtCode = sqlSessionTemplate.insert(queryId, parameters);
		} else if ("update".equals(type)) {
			rtCode = sqlSessionTemplate.update(queryId, parameters);
		} else if ("delete".equals(type)) {
			rtCode = sqlSessionTemplate.delete(queryId, parameters);
		} else if ("select".equals(type)) {
			bean = sqlSessionTemplate.selectOne(queryId, parameters);
		} else {
			throw new CommonRunTimeException("999");
		}

		bean.setRtCode(rtCode);
		return bean;

	}
}
