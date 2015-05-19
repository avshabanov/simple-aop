package com.alexshabanov.simpleaop.internal;

/**
 * @author Alexander Shabanov
 */
public interface Caller1<R, P1> {
  R call(P1 p1);
}
