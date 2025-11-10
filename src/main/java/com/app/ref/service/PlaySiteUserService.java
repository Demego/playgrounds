package com.app.ref.service;

import com.app.ref.converter.PlaySiteUserRequestConverter;
import com.app.ref.converter.PlaySiteWaitQueueConverter;
import com.app.ref.domain.dto.PlaySiteUserRequest;
import com.app.ref.domain.entity.PlaySiteAttractionEntity;
import com.app.ref.domain.entity.PlaySiteEntity;
import com.app.ref.domain.entity.PlaySiteUserEntity;
import com.app.ref.domain.entity.PlaySiteWaitQueueEntity;
import com.app.ref.domain.enums.PlaySiteWaitQueueStatus;
import com.app.ref.exception.PlaySiteMaxCapacityException;
import com.app.ref.exception.PlaySiteNotFoundException;
import com.app.ref.exception.PlaySiteUserExistsException;
import com.app.ref.exception.PlaySiteUserNotFoundException;
import com.app.ref.repository.PlaySiteRepository;
import com.app.ref.repository.PlaySiteUserRepository;
import com.app.ref.repository.PlaySiteWaitQueueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PlaySiteUserService {

    private final PlaySiteUserRequestConverter playSiteUserRequestConverter;
    private final PlaySiteWaitQueueConverter playSiteWaitQueueConverter;

    private final PlaySiteRepository playSiteRepository;
    private final PlaySiteUserRepository playSiteUserRepository;
    private final PlaySiteWaitQueueRepository playSiteWaitQueueRepository;

    public void addPlaySiteUser(Long playSiteId, PlaySiteUserRequest playSiteUserRequest) {
        if (playSiteUserRepository.existsByTicketNumber(playSiteUserRequest.getTicketNumber())) {
            throw new PlaySiteUserExistsException(String.format("User already exists with ticket number: %s", playSiteUserRequest.getTicketNumber()));
        }

        PlaySiteEntity existingPlaySite = playSiteRepository.findById(playSiteId)
                .orElseThrow(() -> new PlaySiteNotFoundException(String.format("Play site not found with id: %d", playSiteId)));
        int totalMaxCapacity = existingPlaySite.getAttractions().stream().mapToInt(PlaySiteAttractionEntity::getCapacity).sum();
        if (existingPlaySite.getUsers().size() == totalMaxCapacity) {
            if (playSiteUserRequest.isWaiting()) {
                PlaySiteWaitQueueEntity newPlaySiteWaitQueueEntity = playSiteWaitQueueConverter.convert(playSiteUserRequest);
                newPlaySiteWaitQueueEntity.setPlaysiteId(playSiteId);
                playSiteWaitQueueRepository.save(newPlaySiteWaitQueueEntity);
            } else {
                throw new PlaySiteMaxCapacityException(String.format("Max capacity reached in play site id: %d", playSiteId));
            }
        } else {
            PlaySiteUserEntity newPlaySiteUserEntity = playSiteUserRequestConverter.convert(playSiteUserRequest);
            newPlaySiteUserEntity.setPlaySite(existingPlaySite);
            existingPlaySite.getUsers().add(newPlaySiteUserEntity);
            playSiteRepository.save(existingPlaySite);
        }
    }

    public void removePlaySiteUser(Long playSiteId, String ticketNumber) {
        PlaySiteUserEntity existingUser = playSiteUserRepository.findOneByTicketNumber(ticketNumber)
                .orElseThrow(() -> new PlaySiteUserNotFoundException(String.format("User does not exist with ticket number: %s", ticketNumber)));
        PlaySiteEntity existingPlaySite = playSiteRepository.findById(playSiteId)
                .orElseThrow(() -> new PlaySiteNotFoundException(String.format("Play site not found with id: %d", playSiteId)));
        existingPlaySite.getUsers().remove(existingUser);

        playSiteWaitQueueRepository.findFirstByStatusOrderByStartedAtDesc(PlaySiteWaitQueueStatus.WAITING).ifPresent(waitingUser -> {
            PlaySiteUserEntity playSiteUserEntity = new PlaySiteUserEntity();
            playSiteUserEntity.setName(waitingUser.getName());
            playSiteUserEntity.setAge(waitingUser.getAge());
            playSiteUserEntity.setTicketNumber(waitingUser.getTicketNumber());
            playSiteUserEntity.setPlaySite(existingPlaySite);

            existingPlaySite.getUsers().add(playSiteUserEntity);
            playSiteRepository.save(existingPlaySite);

            waitingUser.setStatus(PlaySiteWaitQueueStatus.REMOVED);
            playSiteWaitQueueRepository.save(waitingUser);
        });
    }
}
