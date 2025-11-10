package com.app.ref.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PlaySiteUserNotFoundException extends RuntimeException {
  private final String message;
}
