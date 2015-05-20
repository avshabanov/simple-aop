package com.truward.simpleaop.internal;

/**
 * @author Alexander Shabanov
 */
public interface Caller4<R, P1, P2, P3, P4> {
  R call(P1 p1, P2 p2, P3 p3, P4 p4);
}
