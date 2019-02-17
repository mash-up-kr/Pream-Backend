package com.mashup6th.preambackend.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FilterCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "filter_id")
  private Filter filter;

  @Column(nullable = false)
  private Long categoryId;
}

