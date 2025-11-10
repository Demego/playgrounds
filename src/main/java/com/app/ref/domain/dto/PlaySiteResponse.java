package com.app.ref.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaySiteResponse {
    private String playSiteId;
    private List<PlaySiteAttractionDto> attractions;
}
