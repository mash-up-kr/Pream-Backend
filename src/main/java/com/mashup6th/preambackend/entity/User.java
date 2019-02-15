package com.mashup6th.preambackend.entity;


import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String nickname;

  @Column
  private String gender;

  @Column
  private Integer age;

  @Column
  @CreationTimestamp
  private LocalDateTime regDate;

  @Column
  @UpdateTimestamp
  private LocalDateTime updateDate;

  @OneToMany(mappedBy = "user")
  private List<Filter> filters;
}
