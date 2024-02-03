package de.aittr.g_27_shop_project.logging;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServicesLoggingAspect {

  private Logger logger = LoggerFactory.getLogger(ServicesLoggingAspect.class);

  @Pointcut("execution(* de.aittr.g_27_shop_project.services..*.*(..))")
  public void allServiceMethods() {
  }

  @Before("allServiceMethods()")
  public void beforeServiceMethod(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    String args = Arrays.toString(joinPoint.getArgs());
    logger.info(String.format("Метод %s класса %s вызван с аргументами: %s", methodName, className, args));
  }

  @After("allServiceMethods()")
  public void afterServiceMethod(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    logger.info(String.format("Метод %s класса %s завершил работу", methodName, className));

  }

  @AfterReturning(pointcut = "allServiceMethods()", returning = "result")
  public void afterReturningServiceMethod(JoinPoint joinPoint, Object result) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    logger.info(String.format("Метод %s класса %s успешно вернул результат: %s", methodName, className, result));

  }

  @AfterThrowing(pointcut = "allServiceMethods()", throwing = "e")
  public void afterThrowingServiceMethod(JoinPoint joinPoint, Exception e) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    logger.error(String.format("Метод %s класса %s выбросил исключение: %s", methodName, className, e.getMessage()));

  }
}

