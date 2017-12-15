/*
 * Copyright (c) 2016 Inomera Research.
 */

package com.netmera.events;

import com.google.gson.annotations.SerializedName;

/**
 * @author Emmar Kardeslik
 */
public class NGEventLogin extends NetmeraEventLogin {
  private static final String EVENT_CODE = "c:ph";

  @SerializedName("other") private String other;

  public NGEventLogin(String userId) {
    super(userId);
  }

  @Override protected String eventCode() {
    return EVENT_CODE;
  }

  public void setOther(String other) {
    this.other = other;
  }
}
