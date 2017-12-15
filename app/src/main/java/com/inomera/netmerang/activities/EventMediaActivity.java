/*
 * Copyright (c) 2016 Inomera Research.
 */

package com.inomera.netmerang.activities;

import android.os.Bundle;
import android.view.View;

import com.inomera.netmerang.R;
import com.inomera.netmerang.Utils;
import com.netmera.Netmera;
import com.netmera.events.media.NetmeraContent;
import com.netmera.events.media.NetmeraEventContentComment;
import com.netmera.events.media.NetmeraEventContentRate;
import com.netmera.events.media.NetmeraEventContentView;

import java.util.Arrays;
import java.util.Collections;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Emmar Kardeslik
 */
public class EventMediaActivity extends BaseActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_media);
    ButterKnife.bind(this);
    enableUp();
  }

  private NetmeraContent createNetmeraContent() {
    return new NetmeraContent.Builder(Utils.randomAlphaNumericString(8), Utils.randomUpperCaseString(20), Utils.randomInt(3))
        .setProvider(Utils.randomUpperCaseString(10))
        .setCategoryIds(Collections.singletonList(Utils.randomUpperCaseString(10)))
        .setCategoryNames(Collections.singletonList(Utils.randomUpperCaseString(10)))
        .setKeywords(Arrays.asList(Utils.randomLowerCaseString(5), Utils.randomLowerCaseString(5)))
        .build();
  }

  @OnClick(R.id.button_content_comment)
  void onClickContentComment(View v) {
    Netmera.sendEvent(new NetmeraEventContentComment(createNetmeraContent()));
  }

  @OnClick(R.id.button_content_rate)
  void onClickContentRate(View v) {
    int rating = Utils.randomInt(5);
    NetmeraEventContentRate contentRate = new NetmeraEventContentRate(createNetmeraContent(), rating);
    contentRate.setRevenue((double) rating);
    Netmera.sendEvent(contentRate);
  }

  @OnClick(R.id.button_content_view)
  void onClickContentView(View v) {
    Netmera.sendEvent(new NetmeraEventContentView(createNetmeraContent()));
  }

}
