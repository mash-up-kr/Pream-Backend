package org.mashup.pream.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AlreadyExistsException extends BaseException {

  public AlreadyExistsException(String exceptionMessage) {
    super(HttpStatus.CONFLICT.value(), exceptionMessage);
  }
}
