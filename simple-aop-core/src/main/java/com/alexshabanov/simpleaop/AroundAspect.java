package com.alexshabanov.simpleaop;

/**
 * @author Alexander Shabanov
 */
public interface AroundAspect {
  Object call(JoinPoint joinPoint, Object... args);
}
