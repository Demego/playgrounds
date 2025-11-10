package com.app.ref.api;

import com.app.ref.domain.dto.PlaySiteUserRequest;
import com.app.ref.service.PlaySiteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/playsite/{playSiteId}/user")
public class PlaysiteUserController {

    private final PlaySiteUserService playSiteUserService;

    /**
     * Add user to play site
     *
     * @param playSiteUserRequest {@link PlaySiteUserRequest} play site user request
     */
    @PostMapping
    public void addUser(@PathVariable("playSiteId") final Long playSiteId,
                                    @RequestBody PlaySiteUserRequest playSiteUserRequest) {
        playSiteUserService.addPlaySiteUser(playSiteId, playSiteUserRequest);
    }

    /**
     * Remove user from play site
     *
     * @param ticketNumber ticket number of an existing user
     */
    @DeleteMapping("/{ticketNumber}")
    public void removeUser(@PathVariable("playSiteId") final Long playSiteId,
                           @PathVariable("ticketNumber") final String ticketNumber) {
        playSiteUserService.removePlaySiteUser(playSiteId, ticketNumber);
    }

}
