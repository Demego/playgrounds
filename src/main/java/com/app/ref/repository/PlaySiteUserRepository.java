package com.app.ref.repository;

import com.app.ref.domain.entity.PlaySiteUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PlaySiteUserRepository extends JpaRepository<PlaySiteUserEntity, Long> {

    boolean existsByTicketNumber(String ticketNumber);

    Optional<PlaySiteUserEntity> findOneByTicketNumber(String ticketNumber);

    Long countByEnteredAt(LocalDate now);
}
