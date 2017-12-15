/*
 * Copyright (c) 2015 Inomera Research.
 */

package com.inomera.netmerang;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.netmera.NetmeraPushBroadcastReceiver;
import com.netmera.NetmeraPushObject;

import de.greenrobot.event.EventBus;

/**
 * @author Emmar Kardeslik
 */
public class NGPushBroadcastReceiver extends NetmeraPushBroadcastReceiver {

  @Override
  protected void onPushRegister(Context context, String gcmSenderId, String pushToken) {
    Log.v("sample", "onPushRegister");
    PushRegisterIdlingResource.registeredToPush();
    EventBus.getDefault().postSticky(new GCMRegistrationEvent(pushToken));
  }

  @Override
  protected void onPushReceive(Context context, Bundle bundle, NetmeraPushObject netmeraPushObject) {
    Log.v("sample", "onPushReceive");
    EventBus.getDefault().post(new PushReceivedEvent(netmeraPushObject));
  }

  @Override
  protected void onPushOpen(Context context, Bundle bundle, NetmeraPushObject netmeraPushObject) {
    Log.v("sample", "onPushOpen");
  }

  @Override
  protected void onPushDismiss(Context context, Bundle bundle, NetmeraPushObject netmeraPushObject) {
    Log.v("sample", "onPushDismiss");
  }

  @Override
  protected void onPushButtonClicked(Context context, Bundle bundle, NetmeraPushObject netmeraPushObject) {
    Log.v("sample", "onPushButtonClicked");
  }
}
