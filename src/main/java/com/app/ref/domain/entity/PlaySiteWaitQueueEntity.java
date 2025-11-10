package com.app.ref.domain.entity;

import com.app.ref.domain.enums.PlaySiteWaitQueueStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "play_site_wait_queue")
public class PlaySiteWaitQueueEntity {

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

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PlaySiteWaitQueueStatus status;

  @Column(name = "started_at")
  private LocalDateTime startedAt;

  @Column(name = "playsite_id")
  private Long playsiteId;

}


