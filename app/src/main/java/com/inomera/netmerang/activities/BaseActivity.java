package com.inomera.netmerang.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * @author Emmar Kardeslik
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home){
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  protected void enableUp() {
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

}
