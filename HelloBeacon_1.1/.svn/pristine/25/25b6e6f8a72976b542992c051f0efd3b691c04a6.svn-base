package kr.hfb.hellobeacon.common.pool.concurrent.task.reject;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

public class MemberJoinExecutionHandeler implements RejectedExecutionHandler
{
	@Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor)
    {
        new Thread (runnable).start();
    }
}
