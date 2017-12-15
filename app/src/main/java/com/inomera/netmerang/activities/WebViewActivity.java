/*
 * Copyright (c) 2015 Inomera Research.
 */

package com.inomera.netmerang.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;

import com.inomera.netmerang.R;
import com.netmera.Netmera;
import com.netmera.NetmeraWebViewCallback;

/**
 * @author Emmar Kardeslik
 */
public class WebViewActivity extends BaseActivity {

  public static Intent newIntent(Context context) {
    Intent intent = new Intent(context, WebViewActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    return intent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web_view);

    WebView webView = (WebView) findViewById(R.id.sample_web_view);
    Netmera.handleWebContent(webView, new NetmeraWebViewCallback() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.v("sampleLog", url);
        return false;
      }
    });

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home){
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
