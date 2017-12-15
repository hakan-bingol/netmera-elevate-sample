/*
 * Copyright (c) 2015 Inomera Research.
 */

package com.inomera.netmerang;

import android.content.Context;
import android.os.Bundle;

import com.inomera.netmerang.activities.WebViewActivity;
import com.netmera.NetmeraWebContentBroadcastReceiver;

/**
 * @author Emmar Kardeslik
 */
public class NGWebContentBroadcastReceiver extends NetmeraWebContentBroadcastReceiver {
  @Override
  public void onShow(Context context, Bundle bundle) {
    context.startActivity(WebViewActivity.newIntent(context));
  }
}
