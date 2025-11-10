package com.app.ref.converter;

import com.app.ref.domain.dto.PlaySiteRequest;
import com.app.ref.domain.entity.PlaySiteAttractionEntity;
import com.app.ref.domain.entity.PlaySiteEntity;
import com.app.ref.domain.enums.PlaySiteAttractionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Slf4j
@Component
public class PlaySiteRequestConverter implements Converter<PlaySiteRequest, PlaySiteEntity> {

    /**
     * Convert {@link PlaySiteRequest} to {@link PlaySiteEntity}.
     *
     * @param playSiteRequest - play site request
     * @return populated {@link PlaySiteEntity}
     */
    @Override
    public PlaySiteEntity convert(PlaySiteRequest playSiteRequest) {

        PlaySiteEntity playSiteEntity = new PlaySiteEntity();
        List<PlaySiteAttractionEntity> ballPitAttractions = createAttractionEntities(
                playSiteRequest.getBallPitCount(),
                PlaySiteAttractionType.BALL_PIT,
                3,
                playSiteEntity);

        List<PlaySiteAttractionEntity> slideAttractions = createAttractionEntities(
                playSiteRequest.getSlideCount(),
                PlaySiteAttractionType.SLIDE,
                1,
                playSiteEntity);

        List<PlaySiteAttractionEntity> carouselAttractions = createAttractionEntities(
                playSiteRequest.getCarouselCount(),
                PlaySiteAttractionType.CAROUSEL,
                4,
                playSiteEntity);

        List<PlaySiteAttractionEntity> doubleSwingAttractions = createAttractionEntities(
                playSiteRequest.getDoubleSwingCount(),
                PlaySiteAttractionType.DOUBLE_SWING,
                2,
                playSiteEntity);

        List<PlaySiteAttractionEntity> allAttractions = Stream.of(ballPitAttractions, slideAttractions, carouselAttractions, doubleSwingAttractions)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        playSiteEntity.setAttractions(allAttractions);

        return playSiteEntity;
    }

    private List<PlaySiteAttractionEntity> createAttractionEntities(int count,
                                                                    PlaySiteAttractionType attractionType,
                                                                    int maxCapacity,
                                                                    PlaySiteEntity parentPlaySiteEntity) {
        return IntStream.range(0, count).mapToObj(i -> {
            PlaySiteAttractionEntity attraction = new PlaySiteAttractionEntity();
            attraction.setPlaySite(parentPlaySiteEntity);
            attraction.setCapacity(maxCapacity);
            attraction.setType(attractionType);
            return attraction;
        }).collect(Collectors.toList());
    }
}
