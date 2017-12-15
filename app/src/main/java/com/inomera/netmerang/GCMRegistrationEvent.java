/*
 * Copyright (c) 2015 Inomera Research.
 */

package com.inomera.netmerang;

/**
 * @author Emmar Kardeslik
 */
public class GCMRegistrationEvent {
  private String deviceToken;

  public GCMRegistrationEvent(String deviceToken) {
    this.deviceToken = deviceToken;
  }

  public String getDeviceToken() {
    return deviceToken;
  }
}
