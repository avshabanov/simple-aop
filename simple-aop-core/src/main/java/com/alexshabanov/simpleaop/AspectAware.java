package com.alexshabanov.simpleaop;

import com.alexshabanov.simpleaop.internal.*;

/**
 * This is a base interface that provides convenient methods for delegating calls.
 * This delegation enables assigning aspects.
 *
 * @author Alexander Shabanov
 */
public interface AspectAware<T> {

  /**
   * @return Delegate instance
   */
  default T $() {
    throw new UnsupportedOperationException();
  }

  /**
   * @return Aspect, associated with this proxy instance
   */
  default AroundAspect $aspect() {
    throw new UnsupportedOperationException();
  }

  //
  // Callers, that return results
  //

  default <R> R $(Caller0<R> caller) {
    // aspect behavior can be applied here
    @SuppressWarnings("unchecked") final R r = (R) $aspect().call(caller::call);
    return r;
  }

  default <R, P1> R $(Caller1<R, P1> caller, P1 p1) {
    // aspect behavior can be applied here
    @SuppressWarnings("unchecked") final R r = (R) $aspect().call(() -> caller.call(p1), p1);
    return r;
  }

  default <R, P1, P2> R $(Caller2<R, P1, P2> caller, P1 p1, P2 p2) {
    // aspect behavior can be applied here
    @SuppressWarnings("unchecked") final R r = (R) $aspect().call(() -> caller.call(p1, p2), p1, p2);
    return r;
  }

  default <R, P1, P2, P3> R $(Caller3<R, P1, P2, P3> caller, P1 p1, P2 p2, P3 p3) {
    // aspect behavior can be applied here
    @SuppressWarnings("unchecked") final R r = (R) $aspect().call(() -> caller.call(p1, p2, p3), p1, p2, p3);
    return r;
  }

  default <R, P1, P2, P3, P4> R $(Caller4<R, P1, P2, P3, P4> caller, P1 p1, P2 p2, P3 p3, P4 p4) {
    // aspect behavior can be applied here
    @SuppressWarnings("unchecked") final R r = (R) $aspect().call(() -> caller.call(p1, p2, p3, p4),
        p1, p2, p3, p4);
    return r;
  }

  //
  // Void callers
  //

  default void $(VoidCaller0 caller) {
    // aspect behavior can be applied here
    $aspect().call(() -> {
      caller.call();
      return null;
    });
  }

  default <P1> void $(VoidCaller1<P1> caller, P1 p1) {
    // aspect behavior can be applied here
    $aspect().call(() -> {
      caller.call(p1);
      return null;
    }, p1);
  }

  default <P1, P2> void $(VoidCaller2<P1, P2> caller, P1 p1, P2 p2) {
    // aspect behavior can be applied here
    $aspect().call(() -> {
      caller.call(p1, p2);
      return null;
    }, p1, p2);
  }

  default <P1, P2, P3> void $(VoidCaller3<P1, P2, P3> caller, P1 p1, P2 p2, P3 p3) {
    // aspect behavior can be applied here
    $aspect().call(() -> {
      caller.call(p1, p2, p3);
      return null;
    }, p1, p2, p3);
  }

  default <P1, P2, P3, P4> void $(VoidCaller4<P1, P2, P3, P4> caller, P1 p1, P2 p2, P3 p3, P4 p4) {
    // aspect behavior can be applied here
    $aspect().call(() -> {
      caller.call(p1, p2, p3, p4);
      return null;
    }, p1, p2, p3, p4);
  }
}
