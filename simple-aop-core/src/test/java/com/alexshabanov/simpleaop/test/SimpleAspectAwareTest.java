package com.alexshabanov.simpleaop.test;

import com.alexshabanov.simpleaop.AroundAspect;
import com.alexshabanov.simpleaop.AspectAware;
import com.alexshabanov.simpleaop.JoinPoint;
import com.alexshabanov.simpleaop.support.AspectAwareSupport;
import com.alexshabanov.simpleaop.test.util.AspectTrigger;
import com.alexshabanov.simpleaop.test.util.SimpleTracingAspect;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Alexander Shabanov
 */
public final class SimpleAspectAwareTest {

  @Test
  public void shouldReturnResult() {
    // Given:
    final BarService service = new BarServiceImpl();
    final BarService proxy = new BarServiceProxy(service);
    final String message = "message";

    // When:
    final String result = proxy.getGreeting(message);

    // Then:
    assertEquals(service.getGreeting(message), result);
  }

  @Test
  public void shouldTriggerBeforeAndAfter() {
    // Given:
    final BarService service = new BarServiceImpl();
    final AspectTrigger trigger = mock(AspectTrigger.class);
    final BarService proxy = new AnotherBarServiceProxy(service, trigger);
    final String message = "message";

    // When:
    final String result = proxy.getGreeting(message);

    // Then:
    assertEquals(service.getGreeting(message), result);
    verify(trigger).before(new Object[] {message});
    verify(trigger).after(result, new Object[] {message});
  }

  //
  // Test interface
  //

  private interface BarService extends AspectAware<BarService> {
    // default implementation just delegates service calls to the delegate
    default String getGreeting(String message) { return callDelegate(getDelegate()::getGreeting, message); }
  }

  private static final class BarServiceImpl implements BarService {
    @Override
    public String getGreeting(String message) {
      return "Hello, " + message;
    }
  }

  private static final class BarServiceProxy extends AspectAwareSupport<BarService> implements BarService {
    public BarServiceProxy(BarService delegate) {
      super(delegate);
    }
  }

  private static final class AnotherBarServiceProxy extends AspectAwareSupport<BarService> implements BarService {
    public AnotherBarServiceProxy(BarService delegate, AspectTrigger aspectTrigger) {
      super(delegate, new SimpleTracingAspect(aspectTrigger));
    }
  }
}
