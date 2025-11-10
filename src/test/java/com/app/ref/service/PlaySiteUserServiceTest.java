package com.app.ref.service;

import com.app.ref.converter.PlaySiteUserRequestConverter;
import com.app.ref.converter.PlaySiteWaitQueueConverter;
import com.app.ref.domain.dto.PlaySiteUserRequest;
import com.app.ref.domain.entity.PlaySiteAttractionEntity;
import com.app.ref.domain.entity.PlaySiteEntity;
import com.app.ref.domain.entity.PlaySiteUserEntity;
import com.app.ref.domain.entity.PlaySiteWaitQueueEntity;
import com.app.ref.domain.enums.PlaySiteAttractionType;
import com.app.ref.exception.PlaySiteUserExistsException;
import com.app.ref.repository.PlaySiteRepository;
import com.app.ref.repository.PlaySiteUserRepository;
import com.app.ref.repository.PlaySiteWaitQueueRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class PlaySiteUserServiceTest {

    @Mock
    private PlaySiteUserRequestConverter playSiteUserRequestConverter;
    @Mock
    private PlaySiteWaitQueueConverter playSiteWaitQueueConverter;
    @Mock
    private PlaySiteRepository playSiteRepository;
    @Mock
    private PlaySiteUserRepository playSiteUserRepository;
    @Mock
    private PlaySiteWaitQueueRepository playSiteWaitQueueRepository;

    private PlaySiteUserService playSiteUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        playSiteUserService = new PlaySiteUserService(
                playSiteUserRequestConverter,
                playSiteWaitQueueConverter,
                playSiteRepository,
                playSiteUserRepository,
                playSiteWaitQueueRepository);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(playSiteUserRequestConverter,
                playSiteWaitQueueConverter,
                playSiteRepository,
                playSiteUserRepository,
                playSiteWaitQueueRepository);
    }


    @DisplayName("Given User is waiting and max capacity reached When addPlaySiteUser Then enqueue user to waitlist")
    @Test
    void addPlaySiteUser() {
        Long playSiteId = 1L;
        PlaySiteUserRequest playSiteUserRequest = new PlaySiteUserRequest();
        playSiteUserRequest.setTicketNumber("ticket1");
        playSiteUserRequest.setWaiting(true);

        PlaySiteEntity existingPlaySite = new PlaySiteEntity();
        PlaySiteAttractionEntity attractionEntity = new PlaySiteAttractionEntity();
        attractionEntity.setId(playSiteId);
        attractionEntity.setType(PlaySiteAttractionType.BALL_PIT);
        attractionEntity.setCapacity(3);

        PlaySiteWaitQueueEntity playSiteWaitQEntity = new PlaySiteWaitQueueEntity();

        existingPlaySite.setAttractions(List.of(attractionEntity));
        existingPlaySite.setUsers(Set.of(new PlaySiteUserEntity(), new PlaySiteUserEntity(), new PlaySiteUserEntity()));

        when(playSiteUserRepository.existsByTicketNumber(playSiteUserRequest.getTicketNumber())).thenReturn(false);
        when(playSiteRepository.findById(playSiteId)).thenReturn(Optional.of(existingPlaySite));
        when(playSiteWaitQueueConverter.convert(playSiteUserRequest)).thenReturn(playSiteWaitQEntity);

        playSiteUserService.addPlaySiteUser(playSiteId, playSiteUserRequest);

        verify(playSiteUserRepository, times(1)).existsByTicketNumber(playSiteUserRequest.getTicketNumber());
        verify(playSiteRepository, times(1)).findById(playSiteId);
        verify(playSiteWaitQueueConverter, times(1)).convert(playSiteUserRequest);
        verify(playSiteWaitQueueRepository, times(1)).save(playSiteWaitQEntity);
    }

    @DisplayName("Given PlaySiteUserRequest with existing PlaySiteUser When addPlaySiteUser Then PlaySiteUserExistsException is thrown")
    @Test
    void addPlaySiteUser_throwPlaySiteUserExistsException() {
        Long playSiteId = 1L;
        PlaySiteUserRequest playSiteUserRequest = new PlaySiteUserRequest();
        playSiteUserRequest.setTicketNumber("ticket1");

        when(playSiteUserRepository.existsByTicketNumber(playSiteUserRequest.getTicketNumber())).thenReturn(true);

        AssertionsForClassTypes.assertThatThrownBy(() -> playSiteUserService.addPlaySiteUser(playSiteId, playSiteUserRequest))
                .isInstanceOf(PlaySiteUserExistsException.class)
                .hasMessageContaining("User already exists with ticket number");

    }

}