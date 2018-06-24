package com.rogervinas.foo;

import java.io.PrintStream;

public class JsonFooService implements FooService {

  private final PrintStream out;
  private final String fieldName;

  public JsonFooService(PrintStream out, String fieldName) {
    this.out = out;
    this.fieldName = fieldName;
  }

  public void doSomething(String value) {
    out.println(String.format("{ \"%1$s\" : \"%2$s\" }", fieldName, value));
  }
}
