package com.alexshabanov.simpleaop.internal;

/**
 * @author Alexander Shabanov
 */
public interface Caller2<R, P1, P2> {
  R call(P1 p1, P2 p2);
}
