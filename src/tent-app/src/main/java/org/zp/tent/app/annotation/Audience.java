package org.zp.tent.app.annotation;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Pointcut("execution(* notes.javaee.spring.annotation.Performer.perform(..))")
	public void performance(){
		log.debug("performance()");
	}
	
	@Before("performance()")
	public void takeSeats(){
		log.debug("takeSeats()");
	}
	
	@Before("performance()")
	public void turnOffCellPhones(){
		log.debug("turnOffCellPhones()");
	}
	
	@AfterReturning("performance()")
	public void applaud(){
		log.debug("applaud()");
	}
	
	@AfterThrowing("performance()")
	public void demandRefund(){
		log.debug("demandRefund()");
	}
	
	public static void main(String args) {

	}
}
