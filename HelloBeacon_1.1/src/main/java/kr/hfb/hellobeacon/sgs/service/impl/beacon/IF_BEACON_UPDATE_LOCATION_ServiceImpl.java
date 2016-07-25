package kr.hfb.hellobeacon.sgs.service.impl.beacon;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.util.CommonUtils;
import kr.hfb.hellobeacon.common.util.LogMessageUtil;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;
import kr.hfb.hellobeacon.sgs.service.AbsctractSgsService;

@Service("IF_BEACON_UPDATE_LOCATION_Service")
public class IF_BEACON_UPDATE_LOCATION_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;

	@Inject
	@Named("mybatisTxManager")
	private DataSourceTransactionManager txManager = null;

	public IF_BEACON_UPDATE_LOCATION_ServiceImpl() {
		super.paramKey = new String[] { "beaconCode", "latitude", "longitude", "macAddress"};
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		CommonBean bean = new CommonBean();

		String tmplatitude = parameters.get("latitude").toString();
		String tmplongitude = parameters.get("longitude").toString();
		String tmpmacAddress = parameters.get("macAddress").toString();
		
		CommonBean beaconBean = sgsDao.execute(parameters, "beacon.selectBeacon", "select");
		String latitude = "";
		String longitude = "";
		String macAddress = "";
		
		if(null != beaconBean.getList().get(0).get("latitude")){
			latitude = beaconBean.getList().get(0).get("latitude").toString();
		}

		if(null != beaconBean.getList().get(0).get("longitude")){
			longitude = beaconBean.getList().get(0).get("longitude").toString();
		}
		
		if(null != beaconBean.getList().get(0).get("macAddress")){
			macAddress = beaconBean.getList().get(0).get("macAddress").toString();
		}
		
		if(!latitude.equals(tmplatitude) || !longitude.equals(tmplongitude) || !macAddress.equals(tmpmacAddress)){
			TransactionStatus transactionStatus = CommonUtils.getTransactionStatus(txManager);
			try {
				sgsDao.execute(parameters, "beacon.updateBeaconLocation", "update");
				txManager.commit(transactionStatus);
			} catch (Exception e) {
				txManager.rollback(transactionStatus);
			}
			bean.setMessage(LogMessageUtil.getInfoLogMessage(false, "000", ""));
		}
		
		
		return bean;
	}

	@Override
	public String execute(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
