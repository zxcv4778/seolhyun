package kr.hfb.hellobeacon.common.pool.concurrent.task.reject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

/**
 * 
 */

/**
 * @author mdk
 *
 */
public class MemberJoinRecjectedExcutionFution implements Runnable {

	private HttpServletRequest resquest = null;
	
	
	

	public MemberJoinRecjectedExcutionFution() {
		super();
		// TODO Auto-generated constructor stub
	}




	public MemberJoinRecjectedExcutionFution(HttpServletRequest resquest) {
		super();
		this.resquest = resquest;
	}




	public HttpServletRequest getResquest() {
		return resquest;
	}




	public void setResquest(HttpServletRequest resquest) {
		this.resquest = resquest;
	}




	@Override
	public void run() {
		

	}
}
	
