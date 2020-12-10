package com.elkattanman.reddit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
  public void baseException(Exception e) {
    e.printStackTrace();
    log.error(e.getMessage());
  }
}
