package com.truward.simpleaop.support;

import com.truward.simpleaop.AroundAspect;
import com.truward.simpleaop.JoinPoint;

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
