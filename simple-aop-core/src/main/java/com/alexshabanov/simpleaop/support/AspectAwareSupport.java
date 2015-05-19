package com.alexshabanov.simpleaop.support;

import com.alexshabanov.simpleaop.AroundAspect;
import com.alexshabanov.simpleaop.AspectAware;

import java.util.Objects;

/**
 * @author Alexander Shabanov
 */
public abstract class AspectAwareSupport<T> implements AspectAware<T> {
  private final T delegate;
  private final AroundAspect aspect;

  public AspectAwareSupport(T delegate, AroundAspect aspect) {
    this.delegate = Objects.requireNonNull(delegate, "delegate can't be null");
    this.aspect = Objects.requireNonNull(aspect, "aspect can't be null");
  }

  public AspectAwareSupport(T delegate) {
    this(delegate, NoopAroundAspect.INSTANCE);
  }

  @Override
  public final T getDelegate() {
    return delegate;
  }

  @Override
  public final AroundAspect getAspect() {
    return aspect;
  }
}
