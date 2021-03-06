package com.truward.simpleaop.test.util;

import com.truward.simpleaop.AroundAspect;
import com.truward.simpleaop.JoinPoint;

/**
 * @author Alexander Shabanov
 */
public final class SimpleTracingAspect implements AroundAspect {
  final AspectTrigger trigger;

  public SimpleTracingAspect(AspectTrigger trigger) {
    this.trigger = trigger;
  }

  @Override
  public Object call(JoinPoint joinPoint, Object... args) {
    try {
      trigger.before(args);
      final Object result = joinPoint.call();
      trigger.after(result, args);
      return result;
    } catch (RuntimeException e) {
      trigger.fail(e);
      throw e;
    }
  }
}
