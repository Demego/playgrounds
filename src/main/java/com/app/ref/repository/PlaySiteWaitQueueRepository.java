package com.app.ref.repository;

import com.app.ref.domain.entity.PlaySiteWaitQueueEntity;
import com.app.ref.domain.enums.PlaySiteWaitQueueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaySiteWaitQueueRepository extends JpaRepository<PlaySiteWaitQueueEntity, Long> {

    Optional<PlaySiteWaitQueueEntity> findFirstByStatusOrderByStartedAtDesc(PlaySiteWaitQueueStatus status);
}
