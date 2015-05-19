package com.alexshabanov.simpleaop.internal;

/**
 * @author Alexander Shabanov
 */
public interface Caller3<R, P1, P2, P3> {
  R call(P1 p1, P2 p2, P3 p3);
}
