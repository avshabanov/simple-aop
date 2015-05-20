package com.truward.simpleaop;

/**
 * @author Alexander Shabanov
 */
public interface AroundAspect {
  Object call(JoinPoint joinPoint, Object... args);
}
