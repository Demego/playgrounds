package com.app.ref.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PlaySiteNotFoundException extends RuntimeException {
  private final String message;
}
