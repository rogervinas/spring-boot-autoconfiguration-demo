package com.rogervinas.foo.app;

import java.io.PrintStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FooPrintStream {

  @Bean
  public PrintStream out() {
    return System.out;
  }
}
