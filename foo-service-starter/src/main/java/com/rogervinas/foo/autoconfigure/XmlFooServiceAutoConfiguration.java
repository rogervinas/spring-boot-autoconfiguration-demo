package com.rogervinas.foo.autoconfigure;

import com.rogervinas.foo.FooService;
import com.rogervinas.foo.XmlFooService;
import java.io.PrintStream;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(FooService.class)
@EnableConfigurationProperties(XmlFooProperties.class)
public class XmlFooServiceAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "foo.xml", value = "enabled", havingValue = "true", matchIfMissing = true)
  public FooService xmlFooService(
      PrintStream out,
      XmlFooProperties properties
  ) {
    return new XmlFooService(out, properties.getTagName());
  }
}
