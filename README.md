[![CI](https://github.com/rogervinas/spring-boot-autoconfiguration-demo/actions/workflows/gradle.yml/badge.svg?branch=master)](https://github.com/rogervinas/spring-boot-autoconfiguration-demo/actions/workflows/gradle.yml)
[![CodeQL](https://github.com/rogervinas/spring-boot-autoconfiguration-demo/actions/workflows/codeql-analysis.yml/badge.svg?branch=master)](https://github.com/rogervinas/spring-boot-autoconfiguration-demo/actions/workflows/codeql-analysis.yml)

# Spring Boot AutoConfiguration Demo

Foo Service demo using Spring AutoConfiguration.

Inspired by [hello-service-auto-configuration](https://github.com/snicoll-demos/hello-service-auto-configuration) and [It's a Kind of Magic: Under the Covers of Spring Boot - Brian Clozel, Stéphane Nicoll](https://www.youtube.com/watch?v=jDchAEHIht0).

As stated in [Testing Auto-Configurations in Spring Boot 2.0](https://spring.io/blog/2018/03/07/testing-auto-configurations-with-spring-boot-2-0) support for Auto-Configuration test is better in Spring Boot 2.0 so:
* **master** branch -> Spring Boot 2.x
* **release/1.x** branch -> Spring Boot 1.x

## Build & Test

```shell
./gradlew build
```

## Run

```shell
./gradlew bootRun
```

