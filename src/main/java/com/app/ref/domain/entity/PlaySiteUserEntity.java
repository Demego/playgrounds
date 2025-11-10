package com.app.ref.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "play_site_user")
public class PlaySiteUserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(insertable = false, updatable = false, nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private int age;

  @Column(name = "ticket_number")
  private String ticketNumber;

  @CreationTimestamp
  @Column(name = "entered_at", nullable = false, updatable = false)
  private LocalDate enteredAt;

  @ManyToOne
  @JoinColumn(name = "playsite_id", nullable = false)
  private PlaySiteEntity playSite;

}


