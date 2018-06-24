package com.rogervinas.foo.autoconfigure;

import com.rogervinas.foo.FooService;
import com.rogervinas.foo.JsonFooService;
import java.io.PrintStream;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(FooService.class)
@EnableConfigurationProperties(JsonFooProperties.class)
public class JsonFooServiceAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "foo.json", value = "enabled", havingValue = "true", matchIfMissing = false)
  public FooService jsonFooService(
      PrintStream out,
      JsonFooProperties properties
  ) {
    return new JsonFooService(out, properties.getFieldName());
  }
}
