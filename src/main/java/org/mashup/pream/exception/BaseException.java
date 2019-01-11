package org.mashup.pream.exception;

public class BaseException extends RuntimeException {
  private int statusCode;
  private String exceptionMessage;

  /* 예외 메시지 없이 예외 코드만 넘어온 경우 */
  public BaseException(int statusCode) {
    this(statusCode,null);
  }

  /* 예외 메시지와 예외 코드가 함께 넘어온 경우 */
  public BaseException(int statusCode, String exceptionMessage) {
    this.statusCode = statusCode;
    this.exceptionMessage = exceptionMessage;
  }
}
