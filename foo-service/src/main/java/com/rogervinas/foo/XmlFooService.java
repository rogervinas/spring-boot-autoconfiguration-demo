package com.rogervinas.foo;

import java.io.PrintStream;

public class XmlFooService implements FooService {

  private final PrintStream out;
  private final String tagName;

  public XmlFooService(PrintStream out, String tagName) {
    this.out = out;
    this.tagName = tagName;
  }

  public void doSomething(String value) {
    out.println(String.format("<%1$s>%2$s</%1$s>", tagName, value));
  }
}
