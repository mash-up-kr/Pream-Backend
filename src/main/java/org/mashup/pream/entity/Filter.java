package org.mashup.pream.entity;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
  private Long userId;

  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime regDate;

  @Column(columnDefinition = "boolean default false")
  private Boolean visibility;

  @Column(nullable = false)
  private Long category;

  @OneToMany(mappedBy = "filter")
  private Set<UserFilter> userFilters;

  @OneToMany(mappedBy = "filter")
  private Set<FilterCategory> filterCategories;

  @OneToMany(mappedBy = "filter")
  private Set<Favorite> favorites;
}
