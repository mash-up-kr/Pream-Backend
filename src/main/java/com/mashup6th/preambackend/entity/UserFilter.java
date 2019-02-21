package com.mashup6th.preambackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserFilter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
//
//  @Column
//  private Integer ordering;
//
//  @Column(columnDefinition = "boolean default false")
//  private Boolean favorite;

  @Column(columnDefinition = "int default 1")
  private Integer useCount;

  //어떤 유저가 이 필터를 갖고 있는지에 대한 저보
  @Column(nullable = false)
  private Long userId;

  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name = "filter_id")
  private Filter filter;

}