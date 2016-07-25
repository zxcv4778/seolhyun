package kr.hfb.hellobeacon.common.util;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class CommonUtils {
	
	public static final int DEFAULT_BUFFER_SIZE = 8192;

	/**
	 * mybatis transactionStatus추출(디폴트 트렌젝션)
	 * 
	 * @param txManager
	 * @return TransactionStatus
	 */
	public static TransactionStatus getTransactionStatus(DataSourceTransactionManager txManager) {

		return getTransactionStatus(txManager, TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_DEFAULT);
	}

	/**
	 * mybatis transactionStatus추출
	 * 
	 * @param txManager
	 * @param transactionDefinition
	 * @param isolationLevel
	 * @return TransactionStatus
	 */
	public static TransactionStatus getTransactionStatus(DataSourceTransactionManager txManager, int transactionDefinition, int isolationLevel) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setIsolationLevel(isolationLevel);
		def.setPropagationBehavior(transactionDefinition);
		return txManager.getTransaction(def);
	}

}
