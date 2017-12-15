/*
 * Copyright (c) 2016 Inomera Research.
 */

package com.inomera.netmerang.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.inomera.netmerang.R;

/**
 * @author Emmar Kardeslik
 */
public class MapActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {
  public static final String KEY_LAT_LNG = "key.lat.lng";

  private GoogleMap map;
  private LatLng latLng;

  public static Intent newIntent(Context context) {
    return new Intent(context, MapActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map);
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.map, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_done) {
      if (latLng != null) {
        Intent intent = new Intent();
        intent.putExtra(KEY_LAT_LNG, latLng);
        setResult(RESULT_OK, intent);
        finish();
      } else {
        Toast.makeText(this, "Please choose location by long pressing on map.", Toast.LENGTH_LONG).show();
      }
      return true;
    } else if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onMapReady(GoogleMap map) {
    this.map = map;
    map.getUiSettings().setZoomControlsEnabled(true);
    LatLng inomera = new LatLng(41.08730664706428, 29.042712450027462);
    map.moveCamera(CameraUpdateFactory.newLatLngZoom(inomera, 16));
    map.setOnMapLongClickListener(this);
  }

  @Override
  public void onMapLongClick(LatLng latLng) {
    map.clear();
    this.latLng = latLng;
    map.addMarker(new MarkerOptions().position(latLng).title("Mock Location!"));
  }

}
