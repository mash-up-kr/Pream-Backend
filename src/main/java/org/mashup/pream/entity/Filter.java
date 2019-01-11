package org.mashup.pream.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

  //어떤 유저가 이 filter를 생성했는지에 대한 정보
  @OneToMany
  @JoinColumn(name = "user_id")
  private Set<User> users;

  @OneToMany(mappedBy = "filter")
  private Set<UserFilter> userFilters;

  @OneToMany(mappedBy = "filter")
  private Set<FilterCategory> filterCategories;
}
