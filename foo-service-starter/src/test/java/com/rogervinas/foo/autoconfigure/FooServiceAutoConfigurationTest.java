package com.rogervinas.foo.autoconfigure;

import com.rogervinas.foo.FooService;
import com.rogervinas.foo.JsonFooService;
import com.rogervinas.foo.XmlFooService;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FooServiceAutoConfigurationTest {

  private static PrintStream out = mock(PrintStream.class);

  @Before
  public void before() {
    out = mock(PrintStream.class);
  }

  @Test
  public void should_autoConfigure_defaultFooService_with_defaultConfiguration() {
    contextRunner(BaseConfiguration.class)
        .run(context ->
          assertThat(context)
              .getBean(FooService.class)
              .isInstanceOf(XmlFooService.class)
              .satisfies(bean -> {
                bean.doSomething("bar");
                verify(out, times(1)).println("<foo>bar</foo>");
              })
        );
  }

  @Test
  public void should_autoConfigure_defaultFooService_with_userConfiguration() {
    contextRunner(BaseConfiguration.class, "foo.xml.tagName=city")
        .run(context ->
          assertThat(context)
              .getBean(FooService.class)
              .isInstanceOf(XmlFooService.class)
              .satisfies(bean -> {
                bean.doSomething("barcelona");
                verify(out, times(1)).println("<city>barcelona</city>");
              })
        );
  }

  @Test
  public void should_autoConfigure_xmlFooService_with_userConfiguration() {
    contextRunner(BaseConfiguration.class, "foo.xml.enabled=true", "foo.xml.tagName=fruit")
        .run(context ->
            assertThat(context)
                .getBean(FooService.class)
                .isInstanceOf(XmlFooService.class)
                .satisfies(bean -> {
                  bean.doSomething("mango");
                  verify(out, times(1)).println("<fruit>mango</fruit>");
                })
        );
  }

  @Test
  public void should_autoConfigure_jsonFooService_with_defaultConfiguration() {
    contextRunner(BaseConfiguration.class, "foo.json.enabled=true")
        .run(context ->
            assertThat(context)
                .getBean(FooService.class)
                .isInstanceOf(JsonFooService.class)
                .satisfies(bean -> {
                  bean.doSomething("bar");
                  verify(out, times(1)).println("{ \"foo\" : \"bar\" }");
                })
        );
  }

  @Test
  public void should_autoConfigure_jsonFooService_with_userConfiguration() {
    contextRunner(BaseConfiguration.class, "foo.json.enabled=true", "foo.json.fieldName=fruit")
        .run(context ->
            assertThat(context)
                .getBean(FooService.class)
                .isInstanceOf(JsonFooService.class)
                .satisfies(bean -> {
                  bean.doSomething("orange");
                  verify(out, times(1)).println("{ \"fruit\" : \"orange\" }");
                })
        );
  }

  @Test
  public void should_configure_userFooService() {
    contextRunner(UserConfiguration.class)
        .run(context ->
            assertThat(context)
                .getBean(FooService.class)
                .isInstanceOf(UserFooService.class)
                .satisfies(bean -> {
                  bean.doSomething("bar");
                  verify(out, times(1)).println("bar");
                })
        );
  }

  private ApplicationContextRunner contextRunner(Class<?> config, String... environment) {
    return new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(config))
        .withPropertyValues(environment);
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
