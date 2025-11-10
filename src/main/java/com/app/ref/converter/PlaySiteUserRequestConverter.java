package com.app.ref.converter;

import com.app.ref.domain.dto.PlaySiteUserRequest;
import com.app.ref.domain.entity.PlaySiteUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class PlaySiteUserRequestConverter implements Converter<PlaySiteUserRequest, PlaySiteUserEntity> {

    /**
     * Convert {@link PlaySiteUserRequest} to {@link PlaySiteUserEntity}.
     *
     * @param playSiteUserRequest - play site user request
     * @return populated {@link PlaySiteUserEntity}
     */
    @Override
    public PlaySiteUserEntity convert(PlaySiteUserRequest playSiteUserRequest) {
        PlaySiteUserEntity playSiteUserEntity = new PlaySiteUserEntity();
        playSiteUserEntity.setName(playSiteUserRequest.getName());
        playSiteUserEntity.setAge(playSiteUserRequest.getAge());
        playSiteUserEntity.setTicketNumber(playSiteUserRequest.getTicketNumber());

        return playSiteUserEntity;
    }
}
