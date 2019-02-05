package com.mashup6th.preambackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, nullable = false)
  private String name;

  @Column
  private Integer orderSequence;

  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime regDate;

  @Column
  @UpdateTimestamp
  private LocalDateTime updateDate;

  @OneToMany(mappedBy = "category")
  private List<FilterCategory> filterCategories;

  @OneToMany(mappedBy = "category")
  private List<UserFilter> userFilters;

  @OneToMany(mappedBy = "category")
  private List<UserCategory> userCategories;
}
