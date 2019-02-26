package com.mashup6th.preambackend.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
public class Filter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String imgUrl;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Column
    @CreationTimestamp
    private LocalDateTime shareDate;


    @Column(nullable = false)
    private int sharedCount;
  
    @Column(columnDefinition = "int default 1")
    private int useCount;

    @Column
    private Float exposure;

    @Column
    private Float brightness;

    @Column
    private Float contrast;

    @Column
    private Float sharpen;

    @Column
    private Float saturation;

    @Column
    private Float highlight;

    @Column
    private Float shadow;

    @Column
    private Float whiteBalanceTint;

    @Column
    private Float whiteBalanceTemperature;

    @Column
    private Float vignette;

    @Column
    private Float grain;

    @Column
    private Float fade;


    private Integer colorFilterColor;

    @Column
    private Float colorFilterValue;

    //관리자가 등록한 필터값이면 true
    @Column(columnDefinition = "boolean default false")
    private Boolean adminYn;
    
    //어떤 유저가 이 filter를 생성했는지에 대한 정보
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "filter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserFilter> userFilters;
}