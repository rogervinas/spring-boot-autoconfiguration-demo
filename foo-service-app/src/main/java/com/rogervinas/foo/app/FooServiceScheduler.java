package com.rogervinas.foo.app;

import com.rogervinas.foo.FooService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class FooServiceScheduler {

  private final FooService service;

  public FooServiceScheduler(FooService service) {
    this.service = service;
  }

  @Scheduled(initialDelayString = "${foo.scheduler.initialDelay}", fixedDelayString = "${foo.scheduler.fixedDelay}")
  public void execute() {
    service.doSomething(String.valueOf(System.currentTimeMillis()));
  }
}
