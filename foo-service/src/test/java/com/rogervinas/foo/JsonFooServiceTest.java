package com.rogervinas.foo;

import java.io.PrintStream;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JsonFooServiceTest {

  @Test
  public void should_do_something() {
    PrintStream out = mock(PrintStream.class);
    String tagName = "fruit";
    JsonFooService service = new JsonFooService(out, tagName);

    service.doSomething("orange");

    verify(out, times(1)).println("{ \"fruit\" : \"orange\" }");
  }
}
