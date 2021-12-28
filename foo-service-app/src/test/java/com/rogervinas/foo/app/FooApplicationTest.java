package com.rogervinas.foo.app;

import com.rogervinas.foo.FooService;
import com.rogervinas.foo.JsonFooService;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = FooApplication.class)
@ActiveProfiles("test")
public class FooApplicationTest {

  @MockBean
  private PrintStream out;

  @SpyBean
  private FooService service;

  @Test
  public void should_execute_fooService_as_scheduled() {
    verify(service, after(2000).atLeast(7)).doSomething(anyString());
  }

  @Test
  public void should_configure_jsonFooService() {
    assertThat(service).isInstanceOf(JsonFooService.class);
  }
}
