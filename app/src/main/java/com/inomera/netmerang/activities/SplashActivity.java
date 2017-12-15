/*
 * Copyright (c) 2015 Inomera Research.
 */

package com.inomera.netmerang.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

/**
 * @author Emmar Kardeslik
 */
public class SplashActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    new CountDownTimer(2000, 500){
      @Override
      public void onTick(long millisUntilFinished) {

      }

      @Override
      public void onFinish() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
      }
    }.start();
  }

}
