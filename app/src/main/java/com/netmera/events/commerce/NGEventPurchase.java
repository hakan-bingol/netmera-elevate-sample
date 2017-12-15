/*
 * Copyright (c) 2016 Inomera Research.
 */

package com.netmera.events.commerce;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Emmar Kardeslik
 */
public class NGEventPurchase extends NetmeraEventPurchase {
  private static final String EVENT_CODE = "c:ph";

  @SerializedName("other") private String other;

  public NGEventPurchase(double subTotal, double grandTotal, List<NetmeraLineItem> lineItems) {
    super(subTotal, grandTotal, lineItems);
  }

  @Override protected String eventCode() {
    return EVENT_CODE;
  }

  public void setOther(String other) {
    this.other = other;
  }
}
