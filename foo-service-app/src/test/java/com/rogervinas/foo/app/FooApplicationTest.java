package com.rogervinas.foo.app;

import com.rogervinas.foo.FooService;
import com.rogervinas.foo.JsonFooService;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = FooApplication.class)
@ActiveProfiles("test")
public class FooApplicationTest {

  @MockitoBean
  private PrintStream out;

  @MockitoSpyBean
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
