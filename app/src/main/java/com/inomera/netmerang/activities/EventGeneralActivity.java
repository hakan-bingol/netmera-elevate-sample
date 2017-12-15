/*
 * Copyright (c) 2016 Inomera Research.
 */

package com.inomera.netmerang.activities;

import android.os.Bundle;
import android.view.View;

import com.inomera.netmerang.R;
import com.inomera.netmerang.Utils;
import com.netmera.Netmera;
import com.netmera.events.NetmeraEventBannerOpen;
import com.netmera.events.NetmeraEventCategoryView;
import com.netmera.events.NetmeraEventInAppPurchase;
import com.netmera.events.NetmeraEventLogin;
import com.netmera.events.NetmeraEventRegister;
import com.netmera.events.NetmeraEventSearch;
import com.netmera.events.NetmeraEventShare;

import java.util.Arrays;
import java.util.Collections;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Emmar Kardeslik
 */
public class EventGeneralActivity extends BaseActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_general);
    ButterKnife.bind(this);
    enableUp();
  }

  @OnClick(R.id.button_banner_open)
  void onClickBannerOpen(View v) {
    NetmeraEventBannerOpen event = new NetmeraEventBannerOpen(Utils.randomAlphaNumericString(10));
    event.setKeywords(Arrays.asList(Utils.randomLowerCaseString(5), Utils.randomLowerCaseString(5)));

    Netmera.sendEvent(event);
  }

  @OnClick(R.id.button_category_view)
  void onClickCategoryView(View v) {
    NetmeraEventCategoryView event = new NetmeraEventCategoryView(
        Utils.randomAlphaNumericString(10), Utils.randomAlphaNumericString(10));

    Netmera.sendEvent(event);
  }

  @OnClick(R.id.button_in_app_purchase)
  void onClickInAppPurchase(View v) {
    NetmeraEventInAppPurchase event = new NetmeraEventInAppPurchase(Utils.randomAlphaNumericString(10), Utils.randomUpperCaseString(16), Utils.randomDouble(50));
    event.setKeywords(Arrays.asList(Utils.randomLowerCaseString(5), Utils.randomLowerCaseString(5)));
    event.setCategoryIds(Collections.singletonList(Utils.randomUpperCaseString(10)));
    event.setCategoryNames(Collections.singletonList(Utils.randomUpperCaseString(10)));
    event.setCount(Utils.randomInt(100));

    Netmera.sendEvent(event);
  }

  @OnClick(R.id.button_login)
  void onClickLogin(View v) {
    Netmera.sendEvent(new NetmeraEventLogin(Utils.randomAlphaNumericString(16)));
  }

  @OnClick(R.id.button_register)
  void onClickRegister(View v) {
    Netmera.sendEvent(new NetmeraEventRegister(Utils.randomAlphaNumericString(16)));
  }

  @OnClick(R.id.button_search)
  void onClickSearch(View v) {
    Netmera.sendEvent(new NetmeraEventSearch(Utils.randomAlphaNumericString(20), Utils.randomInt(100)));
  }

  @OnClick(R.id.button_share)
  void onClickShare(View v) {
    Netmera.sendEvent(new NetmeraEventShare(Utils.randomAlphaNumericString(8), Utils.randomAlphaNumericString(8)));
  }

}
