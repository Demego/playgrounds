package com.app.ref.service;

import com.app.ref.converter.PlaySiteRequestConverter;
import com.app.ref.converter.PlaySiteResponseConverter;
import com.app.ref.domain.dto.PlaySiteRequest;
import com.app.ref.domain.dto.PlaySiteResponse;
import com.app.ref.domain.entity.PlaySiteAttractionEntity;
import com.app.ref.domain.entity.PlaySiteEntity;
import com.app.ref.exception.PlaySiteNotFoundException;
import com.app.ref.repository.PlaySiteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PlaySiteService {

    private final PlaySiteRequestConverter playSiteRequestConverter;
    private final PlaySiteResponseConverter playSiteResponseConverter;
    private final PlaySiteRepository playSiteRepository;

    public PlaySiteResponse savePlaySite(PlaySiteRequest playSiteRequest) {
        PlaySiteEntity newPlaySiteEntity = playSiteRequestConverter.convert(playSiteRequest);
        PlaySiteEntity savedPlaySite = playSiteRepository.save(newPlaySiteEntity);

        return playSiteResponseConverter.convert(savedPlaySite);
    }

    public PlaySiteResponse updatePlaySite(Long playSiteId, PlaySiteRequest playSiteRequest) {
        PlaySiteEntity existingPlaySite = playSiteRepository.findById(playSiteId)
                .orElseThrow(() -> new PlaySiteNotFoundException(String.format("Play site not found with id: %d", playSiteId)));

        List<PlaySiteAttractionEntity> updatedAttractions = playSiteRequestConverter.convert(playSiteRequest).getAttractions();
        updatedAttractions.forEach(attractionEntity -> attractionEntity.setPlaySite(existingPlaySite));
        existingPlaySite.getAttractions().clear();
        existingPlaySite.getAttractions().addAll(updatedAttractions);
        PlaySiteEntity savedPlaySite = playSiteRepository.save(existingPlaySite);
        return playSiteResponseConverter.convert(savedPlaySite);
    }

    public PlaySiteResponse getPlaySiteInfo(Long playSiteId) {
        PlaySiteEntity existingPlaySite = playSiteRepository.findById(playSiteId)
                .orElseThrow(() -> new PlaySiteNotFoundException(String.format("Play site not found with id: %d", playSiteId)));

        return playSiteResponseConverter.convert(existingPlaySite);
    }

    public void deletePlaySite(Long playSiteId) {
        PlaySiteEntity existingPlaySite = playSiteRepository.findById(playSiteId)
                .orElseThrow(() -> new PlaySiteNotFoundException(String.format("Play site not found with id: %d", playSiteId)));
        playSiteRepository.delete(existingPlaySite);
    }
}
