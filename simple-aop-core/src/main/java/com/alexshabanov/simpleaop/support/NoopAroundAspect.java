package com.alexshabanov.simpleaop.support;

import com.alexshabanov.simpleaop.AroundAspect;
import com.alexshabanov.simpleaop.JoinPoint;

/**
 * @author Alexander Shabanov
 */
public final class NoopAroundAspect implements AroundAspect {
  private NoopAroundAspect() {}

  public static final NoopAroundAspect INSTANCE = new NoopAroundAspect();

  @Override
  public Object call(JoinPoint joinPoint, Object... args) {
    return joinPoint.call();
  }
}
