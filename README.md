Simple AOP Support
==================

## Overview

This is a small library without any external dependencies that enables aspect oriented programming in java 8
using only features of java language, without any steps involving byte/source code generation, JDK proxies, cglib, etc.

It has some limitations though:
* Interfaces of the beans wrapped into aspects need to be defined in a special way (explained below). However there is no
restriction for the method signatures.
* Aspect is applied uniformly to all the interface methods.
* No method or annotation-based filtering (after all this is pure Java, without any kind of reflection whatsoever).

## 1 Minute Overview

Define interface of the class, you're going to wrap into an aspect:

```java
// this is how aspect bean interface should be defined
interface BarService extends AspectAware<BarService> {
  // every method should be default and the default implementation should delegate method call

  default void foo() { $($()::foo); }

  default void bar(int a, double b) { $($()::bar, a, b); }

  default String getGreeting(String person) { return $($()::getGreeting, person); }
}
```

Note, that every method in this interface should contain default behavior that delegates the call.

Don't worry, you won't have to implement anything crazy when you'll start writing implementation of your interface:

```java
class BarServiceImpl implements BarService {
  // regular implementation goes here, you don't need to override any method except
  // for what is declared in BarService interface:

  @Override
  public void foo() {
    System.out.println("in foo method");
  }

  @Override
  public void bar(int a, double b) {
    System.out.println("in bar method (a=" + a + ", b=" + b + ")");
  }

  @Override
  public String getGreeting(String person) {
    return "Hello, " + person;
  }
}
```

Then the most important part begins. You need to define an aspect and service proxy.
Service proxy definition is always a no brainer:

```java
class BarServiceProxy extends AspectAwareSupport<BarService> implements BarService {
  public BarServiceProxy(BarService delegate, AroundAspect aspect) { super(delegate, aspect); }
}
```

Then in your DI container (or in whatever place you're creating instances of BarService) you need to
create and return an instance of BarServiceProxy:

```java
public BarService createBarService() {
  final AroundAspect aspect = /* Put aspect that you want to use here, for example: */NoopAroundAspect.INSTANCE;
  final BarService service = new BarServiceImpl(/*args*/); // create original service instance
  return new BarServiceProxy(service, aspect); // return proxy that will wrap calls to this service
}
```

## How to define an aspect

This is an example aspect definition that prints method arguments before method invocation and
then prints method invocation result:

```java
class SimpleTracingAspect implements AroundAspect {

  @Override
  public Object call(JoinPoint joinPoint, Object... args) {
    System.out.println("[BEFORE] parameters=" + Arrays.toString(args));
    final Object result = joinPoint.call();
    System.out.println("[AFTER] result=" + result + "parameters=" + Arrays.toString(args));
    return result;
  }
}
```

## How to include

Add to your pom.xml (use alternatives for ivy and similar build systems):

```xml
<dependency>
  <groupId>com.truward.simpleaop</groupId>
  <artifactId>simple-aop-core</artifactId>
  <version>1.0.1</version>
</dependency>
```
