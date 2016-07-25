package kr.hfb.hellobeacon.common.pool.concurrunt.task.util;
	
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

//import kr.hfb.hellobeacon.common.exception.UserHandleableException;
import kr.hfb.hellobeacon.common.pool.concurrunt.task.util.TaskExcutionUtility;
import kr.hfb.hellobeacon.common.pool.concurrunt.task.arg.TaskArgument;

/**
 * TaskArgument로 정의한 작업을 수행한다.
 * @author Administrator
 *
 */
	public class TaskExcutionUtility {
		static	org.apache.log4j.Logger log =org.apache.log4j.Logger.getLogger(TaskExcutionUtility.class); 
	static public Object executeTask(TaskArgument taskArgument)
	throws RuntimeException
	{
		Object returnValue = null;
		System.out.println(taskArgument);
		log.debug("+++ TaskExcutionUtility start : taskArgument : "+taskArgument);
		Object targetObject = taskArgument.getTargetObject();
		Method method     = null;
		String methodName = taskArgument.getMethodName();
		Object[] parameters =  taskArgument.getParameters();
		Class <?>[] parameterTypes = taskArgument.getParameterTypes();
		//log.debug(taskArgument.toString());
		log.debug("+++ TaskExcutionUtility before Try : taskArgument : "+taskArgument);
		try{
			
		Class _class =targetObject.getClass();
		method = _class.getDeclaredMethod(methodName, parameterTypes);
		
		returnValue = method.invoke(targetObject, parameters);
		
		}catch(Exception e){
			log.debug(e.toString());
			throw new RuntimeException(e);
		}
		writeReturnValueLog (returnValue);
		return returnValue;
  
	}
	
	static private void writeReturnValueLog(Object returnValue){

		log.debug("Returnvalue ["+returnValue+"]");
	}
}