package com.app.ref.api;

import com.app.ref.service.PlaySiteStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/statistics")
public class PlaysiteStatisticsController {

    private final PlaySiteStatisticsService playSiteStatisticsService;

    /**
     * Get play site max capacity usage percentage
     *
     * @param playSiteId Existing play site Id
     * @return Max capacity of play site currently taken in percent
     */
    @GetMapping("/playsite/{playSiteId}/capacity-used")
    public BigDecimal getPlaySiteInfo(@PathVariable("playSiteId") final Long playSiteId) {
        return playSiteStatisticsService.getPlaySiteCapacityUsagePercent(playSiteId);
    }

    /**
     * Get total daily visitor count on all play sites
     *
     * @return Visitor count on all play grounds during current day
     */
    @GetMapping("/daily-visitors")
    public Long getPlaySiteInfo() {
        return playSiteStatisticsService.totalVisitorCountToday();
    }

}
