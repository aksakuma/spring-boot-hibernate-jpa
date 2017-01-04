package jp.co.maxa.com.controller;

import java.sql.SQLException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * サンプルインターセプターを表現します。
 */
@Aspect
@Component
public class ControllerIntercepter {

	/** メソッド実行前の差し込み処理を行います。 */
	@Before("execution(* *..controller.sample.*Controller.*(..))")
	public void before(JoinPoint jp){
		Signature sig = jp.getSignature();
		//call before methods
		System.out.println("Call Before Methods!!!  --- " + sig.getName());
	}

	/** メソッド実行後の差し込み処理を行います。 */
	@After("execution(* *..controller.sample.*Controller.*(..))")
	public void after(JoinPoint jp){
		Signature sig = jp.getSignature();
		//call after methods
		System.out.println("Call After Methods!!!  --- " + sig.getName());
	}

	/** メソッド実行後の差し込み処理を行います。 */
	@AfterReturning(value = "execution(* *..controller.sample.*Controller.*(..))", returning="hoge")
	public void afterReturn(JoinPoint jp, String hoge){
		//call after methods
		System.out.println("Parameter Call AfterReturn Methods!!!  --- " + hoge);
	}

	/**
	 * AOP対象メソッドの実行処理を行います。
	 * Aroundの注意点として、必ずパラメータをリターンして下さい。
	 */
	@Around("execution(* *..controller.sample.*Controller.*(..))")
	public String around(ProceedingJoinPoint pjp) throws Throwable{
		Signature sig = pjp.getSignature();
		//call before methods
		System.out.println("Call Around Before Methods!!!  --- " + sig.getName());
		String msg = (String)pjp.proceed();
		//call after methods
		System.out.println("Parameter Call Around After Methods!!!  --- " + msg);
		return msg;
	}

	/** メソッド実行後の例外処理を行います。 */
	//TODO: 本来はもっとちゃんと例外設計をして実装すべき
	@AfterThrowing(value = "execution(* *..controller.sample.*Controller.*(..))", throwing="ex")
	public void someThrowable(IllegalArgumentException ex){
		System.out.println("Throwable Exception!!!  --- ");
	}

	/** メソッド実行後の例外処理を行います。 */
	//TODO: 本来はもっとちゃんと例外設計をして実装すべき
	@AfterThrowing(value = "execution(* *..controller.sample.*Controller.*(..))", throwing="ex")
	public void anyThrowable(SQLException ex){
		System.out.println("SQL Exception!!!  --- ");
	}

	/** メソッド実行後の例外処理を行います。 */
	//TODO: 本来はもっとちゃんと例外設計をして実装すべき
	@AfterThrowing(value = "execution(* *..controller.sample.*Controller.*(..))", throwing="ex")
	public void throwable(Exception ex){
		System.out.println("SQL Exception!!!  --- ");
	}


};