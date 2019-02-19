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
    private LocalDateTime shareDate;

//    @Column(columnDefinition = "boolean default false")
//    private Boolean sharedYn;

    @Column(nullable = false)
    private int sharedCount;
  
    @Column
    private int useCount;

    @Column(columnDefinition = "float default 0.0")
    private Float exposure;

    @Column(columnDefinition = "float default 0.0")
    private Float contrast;

    @Column(columnDefinition = "float default 0.0")
    private Float adjust;

    @Column(columnDefinition = "float default 0.0")
    private Float sharpen;

    @Column(columnDefinition = "float default 0.0")
    private Float clarity;

    @Column(columnDefinition = "float default 0.0")
    private Float saturation;

    @Column(columnDefinition = "float default 0.0")
    private Float tone;

    @Column(columnDefinition = "float default 0.0")
    private Float whiteBalance;

    @Column(columnDefinition = "float default 0.0")
    private Float vignette;

    @Column(columnDefinition = "float default 0.0")
    private Float grain;

    @Column(columnDefinition = "float default 0.0")
    private Float fade;

    @Column(columnDefinition = "float default 0.0")
    private Float splitTone;

    @Column(columnDefinition = "float default 0.0")
    private Float colorFilter;

    //관리자가 등록한 필터값이면 true
    @Column(columnDefinition = "boolean default false")
    private Boolean adminYn;
    
    //어떤 유저가 이 filter를 생성했는지에 대한 정보
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "filter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserFilter> userFilters;
}