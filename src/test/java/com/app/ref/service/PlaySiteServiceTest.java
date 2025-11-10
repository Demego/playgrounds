package com.app.ref.service;

import com.app.ref.converter.PlaySiteRequestConverter;
import com.app.ref.converter.PlaySiteResponseConverter;
import com.app.ref.domain.dto.PlaySiteRequest;
import com.app.ref.domain.entity.PlaySiteEntity;
import com.app.ref.repository.PlaySiteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class PlaySiteServiceTest {

    @Mock
    private PlaySiteRequestConverter playSiteRequestConverter;
    @Mock
    private PlaySiteResponseConverter playSiteResponseConverter;
    @Mock
    private PlaySiteRepository playSiteRepository;

    private PlaySiteService playSiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        playSiteService = new PlaySiteService(playSiteRequestConverter, playSiteResponseConverter, playSiteRepository);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(playSiteRequestConverter, playSiteResponseConverter, playSiteRepository);
    }

    @Test
    void savePlaySite() {
        PlaySiteRequest playSiteRequest = new PlaySiteRequest();
        playSiteRequest.setDoubleSwingCount(1);
        playSiteRequest.setCarouselCount(2);
        playSiteRequest.setSlideCount(3);
        playSiteRequest.setBallPitCount(4);

        PlaySiteEntity playSiteEntity = new PlaySiteEntity();
        PlaySiteEntity savedPlaySiteEntity = new PlaySiteEntity();

        when(playSiteRequestConverter.convert(playSiteRequest)).thenReturn(playSiteEntity);
        when(playSiteRepository.save(playSiteEntity)).thenReturn(savedPlaySiteEntity);

        playSiteService.savePlaySite(playSiteRequest);

        verify(playSiteRequestConverter, times(1)).convert(playSiteRequest);
        verify(playSiteRepository, times(1)).save(playSiteEntity);
        verify(playSiteResponseConverter, times(1)).convert(savedPlaySiteEntity);
    }

    @Test
    void updatePlaySite() {
        PlaySiteRequest playSiteRequest = new PlaySiteRequest();
        playSiteRequest.setDoubleSwingCount(1);
        playSiteRequest.setCarouselCount(2);
        playSiteRequest.setSlideCount(3);
        playSiteRequest.setBallPitCount(4);

        Long playSiteId = 1L;
        PlaySiteEntity playSiteEntity = new PlaySiteEntity();
        PlaySiteEntity savedPlaySiteEntity = new PlaySiteEntity();

        when(playSiteRepository.findById(playSiteId)).thenReturn(Optional.of(playSiteEntity));
        when(playSiteRequestConverter.convert(playSiteRequest)).thenReturn(playSiteEntity);
        when(playSiteRepository.save(playSiteEntity)).thenReturn(savedPlaySiteEntity);

        playSiteService.updatePlaySite(playSiteId, playSiteRequest);

        verify(playSiteRepository, times(1)).findById(playSiteId);
        verify(playSiteRequestConverter, times(1)).convert(playSiteRequest);
        verify(playSiteRepository, times(1)).save(playSiteEntity);
        verify(playSiteResponseConverter, times(1)).convert(savedPlaySiteEntity);
    }

    @Test
    void getPlaySiteInfo() {
        Long playSiteId = 1L;
        PlaySiteEntity playSiteEntity = new PlaySiteEntity();

        when(playSiteRepository.findById(playSiteId)).thenReturn(Optional.of(playSiteEntity));

        playSiteService.getPlaySiteInfo(playSiteId);

        verify(playSiteRepository, times(1)).findById(playSiteId);
        verify(playSiteResponseConverter, times(1)).convert(playSiteEntity);
    }

    @Test
    void deletePlaySite() {
        Long playSiteId = 1L;
        PlaySiteEntity playSiteEntity = new PlaySiteEntity();

        when(playSiteRepository.findById(playSiteId)).thenReturn(Optional.of(playSiteEntity));

        playSiteService.deletePlaySite(playSiteId);

        verify(playSiteRepository, times(1)).findById(playSiteId);
        verify(playSiteRepository, times(1)).delete(playSiteEntity);
    }
}