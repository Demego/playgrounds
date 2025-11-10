package com.app.ref.repository;

import com.app.ref.domain.entity.PlaySiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaySiteRepository extends JpaRepository<PlaySiteEntity, Long> {
}
