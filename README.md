Simple AOP Support
==================

## Overview

This is a small library that enables aspect oriented programming in java 8 using only pure language features,
without code generation, without JDK proxies, without cglib.

It has some limitations though:
* Interfaces of the beans wrapped into aspects need to be defined in a special way.
* Aspect is applied uniformly to all the interface methods.
* No method or annotation-based filtering (hey, after all it is pure Java, without any kind of reflection).

## Sample

```java
// this is how aspect bean interface should be defined
private interface BarService extends AspectAware<BarService> {
  default String getGreeting(String message) { return callDelegate(getDelegate()::getGreeting, message); }
}

// define aspect wrapper
private static final class BarServiceProxy extends AspectAwareSupport<BarService> implements BarService {
  public BarServiceProxy(BarService delegate, AroundAspect aroundAspect) {
    super(delegate, aroundAspect);
  }
}

// ...

// Then in DI container:
public BarService getBarService() {
  final BarService service = new BarServiceImpl(/*args*/); // create "real" service instance
  return new BarServiceProxy(service, getAroundAspect()); // return proxy that will wrap calls to bar service
}

public AroundAspect getAroundAspect() {
  return NoopAroundAspect.INSTANCE; // simplest aspect that does nothing (just calls join point)
}
```

