/*
 * Copyright (c) 2015 Inomera Research.
 */

package com.inomera.netmerang;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;

import com.crashlytics.android.Crashlytics;
import com.inomera.endpoint.EndPoint;
import com.inomera.endpoint.model.Point;
import com.netmera.Netmera;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

/**
 * @author Emmar Kardeslik
 */
public class NGApplication extends Application {

  public static String userId;
  public static EndPoint endPoint;

  @Override public void onCreate() {
    super.onCreate();
    initLegacyData();
    Fabric.with(this, new Crashlytics());
    configureEndPoint();
    initProperties();
    userId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
  }

  @SuppressLint("ApplySharedPref")
  private void initLegacyData() {
    getSharedPreferences("com.google.android.gcm", Context.MODE_PRIVATE).edit().putString("regId", "legacyRegistrationId").commit();
    getSharedPreferences("generalSettings", Context.MODE_PRIVATE).edit().putString("appIIDStr", "legacyInstallationId").commit();
  }

  protected void initProperties() {
    PropertiesUtil.init(this, false);

    Netmera.init(this, PropertiesUtil.gcmSenderId);
    Netmera.logging(true);

    setNetmeraApiKeyIfHasBeenProvidedBefore();
  }

  private void setNetmeraApiKeyIfHasBeenProvidedBefore() {
    final SharedPreferences pref = getSharedPreferences("ng_prop", MODE_PRIVATE);
    String baseUrl = pref.getString("ek313", null);
    String apiKey = pref.getString("313ke", null);
    if (!TextUtils.isEmpty(baseUrl) && !TextUtils.isEmpty(apiKey)) {
      Netmera.setBaseUrl(baseUrl);
      Netmera.setApiKey(apiKey);
    }
  }

  private void configureEndPoint(){
    List<Point> points = new ArrayList<>();
    points.add(new Point("Elevate", "property/elevate.properties"));
    points.add(new Point("Nova", "property/nova.properties"));
    endPoint = new EndPoint.Builder(this)
            .setEndPoints(points)
            .setDefaultEndPoint(new Point("Default", BuildConfig.ENVIRONMENT_TYPE))
            .create();

  }
}
