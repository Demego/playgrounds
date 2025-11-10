package com.app.ref.domain.entity;


import com.app.ref.domain.enums.PlaySiteAttractionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "play_site_attraction")
public class PlaySiteAttractionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlaySiteAttractionType type;

    @Column(nullable = false)
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "playsite_id", nullable = false)
    private PlaySiteEntity playSite;

}
