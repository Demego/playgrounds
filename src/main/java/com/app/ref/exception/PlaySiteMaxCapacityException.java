package com.app.ref.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PlaySiteMaxCapacityException extends RuntimeException {
  private final String message;
}
