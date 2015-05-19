package com.alexshabanov.simpleaop.test;

import com.alexshabanov.simpleaop.AspectAware;
import com.alexshabanov.simpleaop.support.AspectAwareSupport;
import com.alexshabanov.simpleaop.test.util.AspectTrigger;
import com.alexshabanov.simpleaop.test.util.SimpleTracingAspect;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Alexander Shabanov
 */
public final class VoidCallerAspectTest {

  @Test
  public void shouldInvokeMethodWithPrimitiveParameters() {
    // Given:
    final FooService service = mock(FooService.class);
    final AspectTrigger trigger = mock(AspectTrigger.class);
    final FooServiceProxy proxy = new FooServiceProxy(service, trigger);
    final int a = 65;
    final char b = 'b';

    // When:
    proxy.foo(a, b);

    // Then:
    verify(service).foo(a, b);
  }

  //
  // Test interface
  //

  private interface FooService extends AspectAware<FooService> {
    default void foo(int a, char b) { callDelegate(getDelegate()::foo, a, b); }
  }

  private static final class FooServiceProxy extends AspectAwareSupport<FooService> implements FooService {
    public FooServiceProxy(FooService delegate, AspectTrigger aspectTrigger) {
      super(delegate, new SimpleTracingAspect(aspectTrigger));
    }
  }
}
