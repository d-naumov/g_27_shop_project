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
public class JpaProductServiceLoggingAspect {
  private Logger logger = LoggerFactory.getLogger(JpaProductServiceLoggingAspect.class);

  @Pointcut("execution(* de.aittr.g_27_shop_project.services.jpa.JpaProductService.*(..))")
  public void allServiceMethods() {
  }

  @Before("allServiceMethods()")
  public void beforeServiceMethod(JoinPoint joinPoint) {
    logger.info(String.format("Метод %s вызван с аргументами: %s", joinPoint.getSignature().getName(),
        Arrays.toString(joinPoint.getArgs())));
  }

  @After("allServiceMethods()")
  public void afterServiceMethod(JoinPoint joinPoint) {
    logger.info(String.format("Метод %s завершил работу", joinPoint.getSignature().getName()));
  }

  @AfterReturning(pointcut = "allServiceMethods()", returning = "result")
  public void afterReturningServiceMethod(JoinPoint joinPoint, Object result) {
    logger.info(String.format("Метод %s успешно вернул результат: %s", joinPoint.getSignature().getName(), result));
  }

  @AfterThrowing(pointcut = "allServiceMethods()", throwing = "e")
  public void afterThrowingServiceMethod(JoinPoint joinPoint, Exception e) {
    logger.error(String.format("Метод %s выбросил исключение: %s", joinPoint.getSignature().getName(), e.getMessage()));
  }
}
