package com.alexshabanov.simpleaop.test.util;

/**
 * @author Alexander Shabanov
 */
public interface AspectTrigger {
  void before(Object[] args);
  void after(Object result, Object[] args);
  void fail(Exception e);
}
