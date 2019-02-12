package com.mashup6th.preambackend.entity.constant;

import java.util.Random;

public class AuthNumber {
  private int authNumLength = 6;

  public String makeAuthNumber() {
    Random random = new Random(System.currentTimeMillis());

    int range = (int)Math.pow(10,authNumLength);
    int trim = (int)Math.pow(10, authNumLength-1);
    int result = random.nextInt(range)+trim;

    if(result>range){
      result = result - trim;
    }

    return String.valueOf(result);
  }
}
