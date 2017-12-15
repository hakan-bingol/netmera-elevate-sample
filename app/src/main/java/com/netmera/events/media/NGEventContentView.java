/*
 * Copyright (c) 2016 Inomera Research.
 */

package com.netmera.events.media;

import com.google.gson.annotations.SerializedName;

/**
 * @author Emmar Kardeslik
 */
public class NGEventContentView extends NetmeraEventContentView {
  private static final String EVENT_CODE = "c:vc";

  @SerializedName("other") private String other;

  public NGEventContentView(NetmeraContent content) {
    super(content);
  }

  @Override protected String eventCode() {
    return EVENT_CODE;
  }

  public void setOther(String other) {
    this.other = other;
  }
}
