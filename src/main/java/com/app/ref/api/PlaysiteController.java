package com.app.ref.api;

import com.app.ref.domain.dto.PlaySiteRequest;
import com.app.ref.domain.dto.PlaySiteResponse;
import com.app.ref.service.PlaySiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/playsite")
public class PlaysiteController {

    private final PlaySiteService playSiteService;

    /**
     * Create initial play site
     *
     * @param playSiteRequest {@link PlaySiteRequest} play site request
     * @return {@link PlaySiteResponse}
     */
    @PostMapping
    public PlaySiteResponse createPlaySite(@RequestBody PlaySiteRequest playSiteRequest) {
        return playSiteService.savePlaySite(playSiteRequest);
    }

    /**
     * Edit existing play site
     *
     * @param playSiteId Existing play site Id
     * @param playSiteRequest {@link PlaySiteRequest} play site request
     * @return {@link PlaySiteResponse}
     */
    @PutMapping("/{playSiteId}")
    public PlaySiteResponse updatePlaySite(@PathVariable("playSiteId") final Long playSiteId,
                                           @RequestBody PlaySiteRequest playSiteRequest) {
        return playSiteService.updatePlaySite(playSiteId, playSiteRequest);
    }

    /**
     * Get play site information
     *
     * @param playSiteId Existing play site Id
     * @return {@link PlaySiteResponse}
     */
    @GetMapping("/{playSiteId}")
    public PlaySiteResponse getPlaySiteInfo(@PathVariable("playSiteId") final Long playSiteId) {
        return playSiteService.getPlaySiteInfo(playSiteId);
    }

    /**
     * Delete existing play site
     *
     * @param playSiteId Existing play site Id
     */
    @DeleteMapping("/{playSiteId}")
    public void deletePlaySite(@PathVariable("playSiteId") final Long playSiteId) {
        playSiteService.deletePlaySite(playSiteId);
    }
}
