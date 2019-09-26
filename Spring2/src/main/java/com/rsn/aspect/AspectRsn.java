package com.rsn.aspect;

/**
 * Is a Spring Aspect for executing logs when controller methods execute.
 * @author Gabriel Ferrer.
 */
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component("aspectRsn")
@Aspect
public class AspectRsn {
	private Logger logger = Logger.getLogger(getClass().getName());

	//// log the controllers
	//// jp.getSignature().toLongString()
	//// logs the long name of the method
	//// ex: java.util.List com.rsn.controller.PostController.getAll()

	/**
	 * Is a method that advises after the matching method is executed in the
	 * controller
	 * 
	 * @param jp is a parameter that is supplied by Spring AOP info such as the
	 *           joint point executed can be extracted from jp
	 *           
	 * jp.getSignature().toLongString() returns "java.util.List com.rsn.controller.PostController.getAll()"
	 */
	@After("execution(* com.rsn.controller..*.get*(..))")
	public void get(JoinPoint jp) {
		logger.info(jp.getSignature().toLongString());
		logger.info("get executed inside regardless of exit condition");
	}

	/**
	 * Is a method that advises after the matching method is executed in the
	 * controller
	 * 
	 * @param jp is a parameter that is supplied by Spring AOP info such as the
	 *           joint point executed can be extracted from jp
	 *           
	 * jp.getSignature().toLongString() returns "java.util.List com.rsn.controller.PostController.getAll()"
	 */
	@After("execution(* com.rsn.controller..*.log*(..))")
	public void accountAccess(JoinPoint jp) {
		logger.info(jp.getSignature().toLongString());
		logger.info("login/logout executed inside regardless of exit condition");
	}

	/**
	 * Is a method that advises after the matching method is executed in the
	 * controller
	 * 
	 * @param jp is a parameter that is supplied by Spring AOP info such as the
	 *           joint point executed can be extracted from jp
	 *           
	 * jp.getSignature().toLongString() returns "java.util.List com.rsn.controller.PostController.getAll()"
	 */
	@After("execution(* com.rsn.controller..*.updat*(..))")
	public void update(JoinPoint jp) {
		logger.info(jp.getSignature().toLongString());
		logger.info("update executed inside regardless of exit condition");
	}

	/**
	 * Is a method that advises after the matching method is executed in the
	 * controller
	 * 
	 * @param jp is a parameter that is supplied by Spring AOP info such as the
	 *           joint point executed can be extracted from jp
	 *           
	 * jp.getSignature().toLongString() returns "java.util.List com.rsn.controller.PostController.getAll()"
	 */
	@After("execution(* com.rsn.controller..*.create*(..))")
	public void create(JoinPoint jp) {
		logger.info(jp.getSignature().toLongString());
		logger.info("create executed inside regardless of exit condition");
	}
}
