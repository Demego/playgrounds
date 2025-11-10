package com.app.ref.converter;

import com.app.ref.domain.dto.PlaySiteAttractionDto;
import com.app.ref.domain.dto.PlaySiteResponse;
import com.app.ref.domain.entity.PlaySiteEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
public class PlaySiteResponseConverter implements Converter<PlaySiteEntity, PlaySiteResponse> {

    /**
     * Convert {@link PlaySiteEntity} to {@link PlaySiteResponse}.
     *
     * @param playSiteEntity - {@link PlaySiteEntity}
     * @return populated {@link PlaySiteResponse} from {@link PlaySiteEntity}
     */
    @Override
    public PlaySiteResponse convert(PlaySiteEntity playSiteEntity) {
        List<PlaySiteAttractionDto> attractions = playSiteEntity.getAttractions().stream()
                .map(attractionEntity ->
                        new PlaySiteAttractionDto(
                                attractionEntity.getCapacity(),
                                attractionEntity.getType().toString()))
                .collect(Collectors.toList());

        return new PlaySiteResponse(playSiteEntity.getId().toString(), attractions);
    }
}
