package com.app.ref.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "play_site")
public class PlaySiteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(insertable = false, updatable = false, nullable = false)
  private Long id;

  @OneToMany(mappedBy = "playSite", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PlaySiteAttractionEntity> attractions = new ArrayList<>();

  @OneToMany(mappedBy = "playSite", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<PlaySiteUserEntity> users = new HashSet<>();

}


