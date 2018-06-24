package com.rogervinas.foo.autoconfigure;

import com.rogervinas.foo.FooService;
import com.rogervinas.foo.JsonFooService;
import com.rogervinas.foo.XmlFooService;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FooServiceAutoConfigurationTest {

  private AnnotationConfigApplicationContext context;

  private static PrintStream out = mock(PrintStream.class);

  @Before
  public void before() {
    context = new AnnotationConfigApplicationContext();
    out = mock(PrintStream.class);
  }

  @After
  public void after() {
    if (context != null) {
      context.close();
    }
    reset(out);
  }

  @Test
  public void should_autoConfigure_defaultFooService_with_defaultConfiguration() {
    load(BaseConfiguration.class);

    FooService bean = context.getBean(FooService.class);
    bean.doSomething("bar");

    assertThat(bean).isInstanceOf(XmlFooService.class);
    verify(out, times(1)).println("<foo>bar</foo>");
  }

  @Test
  public void should_autoConfigure_defaultFooService_with_userConfiguration() {
    load(BaseConfiguration.class, "foo.xml.tagName=city");

    FooService bean = context.getBean(FooService.class);
    bean.doSomething("barcelona");

    assertThat(bean).isInstanceOf(XmlFooService.class);
    verify(out, times(1)).println("<city>barcelona</city>");
  }

  @Test
  public void should_autoConfigure_xmlFooService_with_userConfiguration() {
    load(BaseConfiguration.class, "foo.xml.enabled=true", "foo.xml.tagName=fruit");

    FooService bean = context.getBean(FooService.class);
    bean.doSomething("mango");

    assertThat(bean).isInstanceOf(XmlFooService.class);
    verify(out, times(1)).println("<fruit>mango</fruit>");
  }

  @Test
  public void should_autoConfigure_jsonFooService_with_defaultConfiguration() {
    load(BaseConfiguration.class, "foo.json.enabled=true");

    FooService bean = context.getBean(FooService.class);
    bean.doSomething("bar");

    assertThat(bean).isInstanceOf(JsonFooService.class);
    verify(out, times(1)).println("{ \"foo\" : \"bar\" }");
  }

  @Test
  public void should_autoConfigure_jsonFooService_with_userConfiguration() {
    load(BaseConfiguration.class, "foo.json.enabled=true", "foo.json.fieldName=fruit");

    FooService bean = context.getBean(FooService.class);
    bean.doSomething("orange");

    assertThat(bean).isInstanceOf(JsonFooService.class);
    verify(out, times(1)).println("{ \"fruit\" : \"orange\" }");
  }

  @Test
  public void should_configure_userFooService() {
    load(UserConfiguration.class);

    FooService bean = context.getBean(FooService.class);
    bean.doSomething("bar");

    assertThat(bean).isInstanceOf(UserFooService.class);
    verify(out, times(1)).println("bar");
  }

  private void load(Class<?> config, String... environment) {
    context.register(config);
    EnvironmentTestUtils.addEnvironment(context, environment);
    context.refresh();
  }

  @Configuration
  @EnableAutoConfiguration
  static class BaseConfiguration {
    @Bean
    public PrintStream out() {
      return out;
    }
  }

  @Configuration
  @Import(BaseConfiguration.class)
  static class UserConfiguration {
    @Bean
    public FooService userFooService(PrintStream out) {
      return new UserFooService(out);
    }
  }

  static class UserFooService implements FooService {
    private final PrintStream out;
    public UserFooService(PrintStream out) {
      this.out = out;
    }
    @Override
    public void doSomething(String value) {
      out.println(value);
    }
  }
}
