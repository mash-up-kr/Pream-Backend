package com.mashup6th.preambackend.dto.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FilterModel {

    private Long id;

    private String name;
    private String description;
    private String imgUrl;

    private int useCount;

    private Float exposure;
    private Float brightness;
    private Float contrast;
    private Float sharpen;
    private Float saturation;
    private Float highlight;
    private Float shadow;
    private Float whiteBalanceTint;
    private Float whiteBalanceTemperature;
    private Float vignette;
    private Float grain;
    private Float fade;
    private Integer colorFilterColor;
    private Float colorFilterValue;

    private Boolean download;
}
