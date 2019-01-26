package com.mashup6th.preambackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthCheckController {
  @GetMapping(value = "/v1/health-check")
  public String healthCheck() {
    log.info("190105 logTest1");

    return "pream server is running now.";
  }
}
