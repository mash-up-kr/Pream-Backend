package com.mashup6th.preambackend.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long userId;

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "category_id")
  private Category category;

}
