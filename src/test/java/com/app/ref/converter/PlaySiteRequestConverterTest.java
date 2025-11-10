package com.app.ref.converter;

import com.app.ref.domain.dto.PlaySiteRequest;
import com.app.ref.domain.entity.PlaySiteEntity;
import com.app.ref.domain.enums.PlaySiteAttractionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlaySiteRequestConverterTest {

    private final PlaySiteRequestConverter playSiteRequestConverter = new PlaySiteRequestConverter();

    @DisplayName("Given PlaySiteRequest When convert Then correct number of Attractions is mapped")
    @Test
    void convert() {
        PlaySiteRequest playSiteRequest = new PlaySiteRequest();
        playSiteRequest.setBallPitCount(2);
        playSiteRequest.setCarouselCount(3);
        playSiteRequest.setSlideCount(4);
        playSiteRequest.setDoubleSwingCount(5);

        PlaySiteEntity playSiteEntity = playSiteRequestConverter.convert(playSiteRequest);

        assertThat(playSiteEntity).isNotNull();
        assertThat(playSiteEntity.getAttractions()).filteredOn(a -> a.getType() == PlaySiteAttractionType.BALL_PIT).hasSize(2);
        assertThat(playSiteEntity.getAttractions()).filteredOn(a -> a.getType() == PlaySiteAttractionType.CAROUSEL).hasSize(3);
        assertThat(playSiteEntity.getAttractions()).filteredOn(a -> a.getType() == PlaySiteAttractionType.SLIDE).hasSize(4);
        assertThat(playSiteEntity.getAttractions()).filteredOn(a -> a.getType() == PlaySiteAttractionType.DOUBLE_SWING).hasSize(5);
        assertThat(playSiteEntity.getAttractions()).hasSize(14);
    }
}