package com.app.ref.domain.dto;

import lombok.Data;


@Data
public class PlaySiteUserRequest {
    private String name;
    private int age;
    private String ticketNumber;
    private boolean isWaiting;
}
