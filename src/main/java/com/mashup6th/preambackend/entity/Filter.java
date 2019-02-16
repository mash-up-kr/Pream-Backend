package com.mashup6th.preambackend.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
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

    @Column(columnDefinition = "boolean default false")
    private Boolean sharedYn;

    @Column(nullable = false)
    private int sharedCount;

    @Column
    private int useCount;

    @Column
    private Float exposure;

    @Column
    private Float contrast;

    @Column
    private Float adjust;

    @Column
    private Float sharpen;

    @Column
    private Float clarity;

    @Column
    private Float saturation;

    @Column
    private Float tone;

    @Column
    private Float whiteBalance;

    @Column
    private Float vignette;

    @Column
    private Float grain;

    @Column
    private Float fade;

    @Column
    private Float splitTone;

    @Column
    private Float colorFilter;

    //관리자가 등록한 필터값이면 true
    @Column(columnDefinition = "boolean default false")
    private Boolean adminYn;

    //어떤 유저가 이 filter를 생성했는지에 대한 정보
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "filter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserFilter> userFilters;

    @OneToMany(mappedBy = "filter", fetch = FetchType.LAZY)
    private List<FilterCategory> filterCategories;
}