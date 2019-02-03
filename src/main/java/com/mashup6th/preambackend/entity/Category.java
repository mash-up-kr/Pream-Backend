package com.mashup6th.preambackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category {
  @TableGenerator(name = "Category_Gen", initialValue = 100, allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "Category_Gen")
  private Long id;

  @Column(length = 50, nullable = false)
  private String name;

  @GeneratedValue(strategy = GenerationType.TABLE, generator = "Category_Gen")
  @Column
  private Integer orderSequence;

  @OneToMany(mappedBy = "category")
  private List<FilterCategory> filterCategories;

  @OneToMany(mappedBy = "category")
  private List<UserFilter> userFilters;
}
