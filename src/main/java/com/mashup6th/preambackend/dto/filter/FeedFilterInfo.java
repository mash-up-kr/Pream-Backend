package com.mashup6th.preambackend.dto.filter;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FeedFilterInfo {
  private Long id;

  private String name;
  private String imgUrl;
  private String description;

  private Float exposure;
  private Float contrast;
  private Float adjust;
  private Float sharpen;
  private Float clarity;
  private Float saturation;
  private Float tone;
  private Float whiteBalance;
  private Float vignette;
  private Float grain;
  private Float fade;
  private Float splitTone;
  private Float colorFilter;

  private Boolean download;
}
