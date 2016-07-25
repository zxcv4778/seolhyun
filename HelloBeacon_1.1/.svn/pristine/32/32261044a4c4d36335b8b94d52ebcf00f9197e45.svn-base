package kr.hfb.hellobeacon.common.pool.concurrent.mgr;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class GenericTaskThreadPoolExecutor extends ThreadPoolExecutor {
	public GenericTaskThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, BlockingQueue<Runnable> workQueue,RejectedExecutionHandler rejectedExecutionHandler) 
	{

		super(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue,rejectedExecutionHandler );
		System.out.println("=================================================================");
	}
	
	public GenericTaskThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,RejectedExecutionHandler rejectedExecutionHandler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,rejectedExecutionHandler );
	}
	
public GenericTaskThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
			RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
		
	}

	public GenericTaskThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory);
		
	}

	public GenericTaskThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		
	}
/**
 * 가용할만한 리소스가 있는지 조회.
 * @return
 */
public boolean isAvailAbleExecutionResource(){
 return getMaximumPoolSize() - getActiveCount() >0 ? true: false;	
}
}
