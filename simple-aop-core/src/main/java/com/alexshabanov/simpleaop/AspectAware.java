package com.alexshabanov.simpleaop;

import com.alexshabanov.simpleaop.internal.*;

/**
 * @author Alexander Shabanov
 */
public interface AspectAware<T> {
  default T getDelegate() {
    throw new UnsupportedOperationException();
  }

  default AroundAspect getAspect() {
    throw new UnsupportedOperationException();
  }

  //
  // Callers, that return results
  //

  default <R> R callDelegate(Caller0<R> caller) {
    // aspect behavior can be applied here
    @SuppressWarnings("unchecked") final R r = (R) getAspect().call(caller::call);
    return r;
  }

  default <R, P1> R callDelegate(Caller1<R, P1> caller, P1 p1) {
    // aspect behavior can be applied here
    @SuppressWarnings("unchecked") final R r = (R) getAspect().call(() -> caller.call(p1), p1);
    return r;
  }

  default <R, P1, P2> R callDelegate(Caller2<R, P1, P2> caller, P1 p1, P2 p2) {
    // aspect behavior can be applied here
    @SuppressWarnings("unchecked") final R r = (R) getAspect().call(() -> caller.call(p1, p2), p1, p2);
    return r;
  }

  default <R, P1, P2, P3> R callDelegate(Caller3<R, P1, P2, P3> caller, P1 p1, P2 p2, P3 p3) {
    // aspect behavior can be applied here
    @SuppressWarnings("unchecked") final R r = (R) getAspect().call(() -> caller.call(p1, p2, p3), p1, p2, p3);
    return r;
  }

  default <R, P1, P2, P3, P4> R callDelegate(Caller4<R, P1, P2, P3, P4> caller, P1 p1, P2 p2, P3 p3, P4 p4) {
    // aspect behavior can be applied here
    @SuppressWarnings("unchecked") final R r = (R) getAspect().call(() -> caller.call(p1, p2, p3, p4),
        p1, p2, p3, p4);
    return r;
  }

  //
  // Void callers
  //

  default void callDelegate(VoidCaller0 caller) {
    // aspect behavior can be applied here
    getAspect().call(() -> { caller.call(); return null; });
  }

  default <P1> void callDelegate(VoidCaller1<P1> caller, P1 p1) {
    // aspect behavior can be applied here
    getAspect().call(() -> { caller.call(p1); return null; }, p1);
  }

  default <P1, P2> void callDelegate(VoidCaller2<P1, P2> caller, P1 p1, P2 p2) {
    // aspect behavior can be applied here
    getAspect().call(() -> { caller.call(p1, p2); return null; }, p1, p2);
  }

  default <P1, P2, P3> void callDelegate(VoidCaller3<P1, P2, P3> caller, P1 p1, P2 p2, P3 p3) {
    // aspect behavior can be applied here
    getAspect().call(() -> { caller.call(p1, p2, p3); return null; }, p1, p2, p3);
  }

  default <P1, P2, P3, P4> void callDelegate(VoidCaller4<P1, P2, P3, P4> caller, P1 p1, P2 p2, P3 p3, P4 p4) {
    // aspect behavior can be applied here
    getAspect().call(() -> { caller.call(p1, p2, p3, p4); return null; }, p1, p2, p3, p4);
  }
}
