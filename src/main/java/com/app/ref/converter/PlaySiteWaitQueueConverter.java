package com.app.ref.converter;

import com.app.ref.domain.dto.PlaySiteUserRequest;
import com.app.ref.domain.entity.PlaySiteWaitQueueEntity;
import com.app.ref.domain.enums.PlaySiteWaitQueueStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Slf4j
@Component
public class PlaySiteWaitQueueConverter implements Converter<PlaySiteUserRequest, PlaySiteWaitQueueEntity> {

    /**
     * Convert {@link PlaySiteUserRequest} to {@link PlaySiteWaitQueueEntity}.
     *
     * @param playSiteUserRequest - play site user request
     * @return populated {@link PlaySiteWaitQueueEntity}
     */
    @Override
    public PlaySiteWaitQueueEntity convert(PlaySiteUserRequest playSiteUserRequest) {
        PlaySiteWaitQueueEntity playSiteWaitQEntity = new PlaySiteWaitQueueEntity();
        playSiteWaitQEntity.setName(playSiteUserRequest.getName());
        playSiteWaitQEntity.setAge(playSiteUserRequest.getAge());
        playSiteWaitQEntity.setTicketNumber(playSiteUserRequest.getTicketNumber());
        playSiteWaitQEntity.setStatus(PlaySiteWaitQueueStatus.WAITING);
        playSiteWaitQEntity.setStartedAt(LocalDateTime.now());
        return playSiteWaitQEntity;
    }
}
