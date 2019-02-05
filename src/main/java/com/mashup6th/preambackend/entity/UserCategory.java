package com.mashup6th.preambackend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
public class UserCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "category_id")
  private Category category;

}
