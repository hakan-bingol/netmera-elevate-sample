/*
 * Copyright (c) 2015 Inomera Research.
 */

package com.inomera.netmerang.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.inomera.netmerang.R;

/**
 * @author Emmar Kardeslik
 */
public class SchemeCategoryActivity extends BaseActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_scheme);

    ((TextView) findViewById(R.id.text_view_scheme)).setText("id = " + getIntent().getData().getQueryParameter("id"));
  }

}
