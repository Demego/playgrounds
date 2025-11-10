package com.app.ref.service;

import com.app.ref.domain.entity.PlaySiteAttractionEntity;
import com.app.ref.domain.entity.PlaySiteEntity;
import com.app.ref.exception.PlaySiteNotFoundException;
import com.app.ref.repository.PlaySiteRepository;
import com.app.ref.repository.PlaySiteUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PlaySiteStatisticsService {

    private final PlaySiteRepository playSiteRepository;
    private final PlaySiteUserRepository playSiteUserRepository;

    public BigDecimal getPlaySiteCapacityUsagePercent(Long playSiteId) {
        PlaySiteEntity existingPlaySite = playSiteRepository.findById(playSiteId)
                .orElseThrow(() -> new PlaySiteNotFoundException(String.format("Play site not found with id: %d", playSiteId)));
        int totalMaxCapacity = existingPlaySite.getAttractions().stream().mapToInt(PlaySiteAttractionEntity::getCapacity).sum();
        int capacityUsed = existingPlaySite.getUsers().size();
        BigDecimal totalCapacityPercentUsed = new BigDecimal(Double.toString((double) capacityUsed / totalMaxCapacity * 100));
        return totalCapacityPercentUsed.setScale(2, RoundingMode.HALF_UP);
    }

    public Long totalVisitorCountToday() {
       return playSiteUserRepository.countByEnteredAt(LocalDate.now());
    }
}
