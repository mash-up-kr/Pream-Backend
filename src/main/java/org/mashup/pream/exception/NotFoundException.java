package org.mashup.pream.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {

  public NotFoundException(String exceptionMessage) {
    super(HttpStatus.NOT_FOUND.value(), exceptionMessage);
  }
}
