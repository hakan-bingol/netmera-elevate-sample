/*
 * Copyright (c) 2016 Inomera Research.
 */

package com.netmera;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.netmera.internal.Optional;

/**
 * @author Emmar Kardeslik
 */
public class NGUser extends NetmeraUser {
  @SerializedName("other") private Optional<String> other;

  public void setOther(@Nullable String other) {
    this.other = Optional.fromNullable(other);
  }
}
