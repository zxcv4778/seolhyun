package kr.hfb.hellobeacon.sgs.service.impl.beacon;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.exception.impl.CommonRunTimeException;
import kr.hfb.hellobeacon.common.util.CommonUtils;
import kr.hfb.hellobeacon.common.util.DateUtil;
import kr.hfb.hellobeacon.common.util.LogMessageUtil;
import kr.hfb.hellobeacon.common.util.StringUtil;
import kr.hfb.hellobeacon.sgs.dao.SgsDao;
import kr.hfb.hellobeacon.sgs.service.AbsctractSgsService;

@Service("IF_BEACON_CREATE_Service")
public class IF_BEACON_CREATE_ServiceImpl extends AbsctractSgsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	@Named("sgsDao")
	private SgsDao sgsDao = null;

	@Value("#{config['server.domain']}")
	private String domain = null;

	@Value("#{config['server.physical.filepath']}")
	private String beaconPhysicalFilePath = null;

	@Value("#{config['server.logical.filepath']}")
	private String beaconLogicalFilePath = null;

	@Inject
	@Named("mybatisTxManager")
	private DataSourceTransactionManager txManager = null;

	public IF_BEACON_CREATE_ServiceImpl() {
		super.paramKey = new String[] { "memberEmail", "beaconCode", "typeCode", "title", "layoutId", "memberId" };
	}

	public CommonBean execute(HttpServletRequest request, Map<String, Object> parameters) {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> iconFiles = multiRequest.getFiles("iconFile");
		List<MultipartFile> imageFiles = multiRequest.getFiles("imageFile");

		String date = DateUtil.getNowDate("yyyy/MM/dd");
		String datetime = DateUtil.getNowDate("yyyyMMddHHmmss");

		File directory = new File(beaconPhysicalFilePath + date + "/" + datetime);

		if (!directory.exists()) {
			directory.mkdirs();
		}
		CommonBean bean = new CommonBean();

		parameters.put("beaconId", UUID.randomUUID().toString());

		TransactionStatus transactionStatus = CommonUtils.getTransactionStatus(txManager);
		try {
			sgsDao.execute(parameters, "beacon.insertBeacon", "insert");

			for (MultipartFile iconFile : iconFiles) {

				if (iconFile.getBytes().length > 0) {
					String fileName = DateUtil.getNowDate("yyyyMMddHHmmssSSSS");

					try {
						File fileIcon = new File(directory.getAbsoluteFile() + "/ICO_" + fileName + "." + StringUtil.getExtension(iconFile.getOriginalFilename()));
						iconFile.transferTo(fileIcon);
						Map<String, Object> iconFileParam = new HashMap<String, Object>();
						iconFileParam.put("beaconFileId", UUID.randomUUID().toString());
						iconFileParam.put("fileName", fileIcon.getName());
						iconFileParam.put("beaconId", parameters.get("beaconId"));
						iconFileParam.put("physicalPath", fileIcon.getAbsolutePath());
						iconFileParam.put("logicalPath", domain + beaconLogicalFilePath + date + "/" + datetime + "/" + fileIcon.getName());
						iconFileParam.put("fileType", "ICO");
						sgsDao.execute(iconFileParam, "beacon.insertBeaconFile", "insert");

					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
			for (MultipartFile imageFile : imageFiles) {

				if (imageFile.getBytes().length > 0) {
					String fileName = DateUtil.getNowDate("yyyyMMddHHmmssSSSS");

					try {
						File fileImage = new File(directory.getAbsoluteFile() + "/IMG_" + fileName + "." + StringUtil.getExtension(imageFile.getOriginalFilename()));
						imageFile.transferTo(fileImage);
						Map<String, Object> imageFileParam = new HashMap<String, Object>();
						imageFileParam.put("beaconFileId", UUID.randomUUID().toString());
						imageFileParam.put("fileName", fileImage.getName());
						imageFileParam.put("beaconId", parameters.get("beaconId"));
						imageFileParam.put("physicalPath", fileImage.getAbsolutePath());
						imageFileParam.put("logicalPath", domain + beaconLogicalFilePath + date + "/" + datetime + "/" + fileImage.getName());
						imageFileParam.put("fileType", "IMG");
						sgsDao.execute(imageFileParam, "beacon.insertBeaconFile", "insert");

					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
			txManager.commit(transactionStatus);
		} catch (DuplicateKeyException e) {
			txManager.rollback(transactionStatus);
			throw new CommonRunTimeException("961");
		} catch (Exception e) {
			txManager.rollback(transactionStatus);
			throw new CommonRunTimeException("952");
		}

		Map<String, Object> statisticsParameters = new HashMap<String, Object>();
		statisticsParameters.put("statisticsId", UUID.randomUUID().toString());
		statisticsParameters.put("typeCode", parameters.get("typeCode").toString());
		statisticsParameters.put("memberEmail", parameters.get("memberEmail").toString());
		statisticsParameters.put("statisticsType", "R");
		transactionStatus = CommonUtils.getTransactionStatus(txManager);
		try {
			sgsDao.execute(statisticsParameters, "statistics.insertStatisticsBeacon", "insert");
			txManager.commit(transactionStatus);
		} catch (Exception e) {
			txManager.rollback(transactionStatus);
		}
		bean.setMessage(LogMessageUtil.getInfoLogMessage(false, "000", ""));
		return bean;
	}

	@Override
	public String execute(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
