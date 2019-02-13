package com.mashup6th.preambackend.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Column(columnDefinition = "boolean default false")
    private Boolean sharedYn;

    @Column(nullable = false)
    private int sharedCount;

    @Column
    private Float exposure = 0.0f;

    @Column
    private Float contrast = 0.0f;

    @Column
    private Float adjust = 0.0f;

    @Column
    private Float sharpen = 0.0f;

    @Column
    private Float clarity = 0.0f;

    @Column
    private Float saturation = 0.0f;

    @Column
    private Float tone = 0.0f;

    @Column
    private Float whiteBalance = 0.0f;

    @Column
    private Float vignette = 0.0f;

    @Column
    private Float grain = 0.0f;

    @Column
    private Float fade = 0.0f;

    @Column
    private Float splitTone = 0.0f;

    @Column
    private Float colorFilter = 0.0f;

    //어떤 유저가 이 filter를 생성했는지에 대한 정보
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "filter", fetch = FetchType.LAZY)
    private List<UserFilter> userFilters;

    @OneToMany(mappedBy = "filter", fetch = FetchType.LAZY)
    private List<FilterCategory> filterCategories;
}