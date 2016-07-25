package kr.hfb.hellobeacon.common.pool.concurrunt.task;

import kr.hfb.hellobeacon.common.pool.concurrunt.task.util.TaskExcutionUtility;

import org.springframework.stereotype.Service;

import kr.hfb.hellobeacon.common.pool.concurrunt.task.arg.TaskArgument;



/**
 * My {@link Runnable} class. Represents a task which need to be executed.
 */
public class JobTask implements Task{
	
    TaskArgument agument;
    String name = null;
    
    public JobTask(TaskArgument agument,String name)
    {
        this.agument = agument;
        this.name = name;
    }
 
    @Override
    public void run()
    {
    	if(this.agument!= null){
    		execute();
    	}else{
    		throw new NullPointerException("TaskArgument is NULL");
    	}
    	
    }
 
    @Override
    public String toString()
    {
        return "name="+this.name+ " EXECUTE ARGUMENT{"
        		+agument.toString()+"}";
    }
    public void execute()  {
    	TaskExcutionUtility.executeTask(this.agument);
    
    }
}