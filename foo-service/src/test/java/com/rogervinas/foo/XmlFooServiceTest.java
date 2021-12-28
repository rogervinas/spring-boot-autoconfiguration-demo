package com.rogervinas.foo;

import java.io.PrintStream;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class XmlFooServiceTest {

  @Test
  public void should_do_something() {
    PrintStream out = mock(PrintStream.class);
    String tagName = "fruit";
    XmlFooService service = new XmlFooService(out, tagName);

    service.doSomething("apple");

    verify(out, times(1)).println("<fruit>apple</fruit>");
  }
}
