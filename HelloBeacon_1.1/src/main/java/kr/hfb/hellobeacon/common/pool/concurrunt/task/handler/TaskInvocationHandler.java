package kr.hfb.hellobeacon.common.pool.concurrunt.task.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import kr.hfb.hellobeacon.common.pool.concurrunt.task.Task;




public class TaskInvocationHandler implements InvocationHandler {
	
	private Task taskImpl;
	public TaskInvocationHandler(Task taskImpl) {
		
		super();
		this.taskImpl = taskImpl;
		
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object returnObject = null;
		System.out.println("ARGUMENTS["+args+"]");
		returnObject = method.invoke(taskImpl, args);
		return returnObject;
	}
}
