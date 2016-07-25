package kr.hfb.hellobeacon.common.pool.concurrunt.task.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.NoSuchElementException;

import kr.hfb.hellobeacon.common.pool.concurrunt.task.handler.TaskInvocationHandler;
import kr.hfb.hellobeacon.common.pool.concurrunt.task.Task;
import kr.hfb.hellobeacon.common.pool.concurrunt.task.arg.TaskArgument;

public class TaskProxyFactory {
	
	
	
	public  static Task getTask(TaskArgument argument) throws IllegalArgumentException, NoSuchElementException, IllegalStateException, Exception {
		
		Task task =(Task) argument.getTargetObject();
		
		return (Task)Proxy.newProxyInstance(
				Task.class.getClassLoader(),
				new Class<?>[] {Task.class},
				(InvocationHandler)new TaskInvocationHandler(task)
				);
	}
}
