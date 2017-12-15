package com.inomera.netmerang.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.inomera.netmerang.GCMRegistrationEvent;
import com.inomera.netmerang.PropertiesUtil;
import com.inomera.netmerang.R;
import com.netmera.Netmera;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * @author Emmar Kardeslik
 */
public class MainActivity extends BaseActivity implements
    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

  private static final String PREF_NAME = "netmerangapp";
  private static final String KEY_TOKEN = "tokentoken";
  private static final int REQ_CODE_MOCK_LOCATION = 313;

  @Bind(R.id.text_view_gcm_device_token)
  TextView textViewGcmDeviceToken;

  @Bind(R.id.button_push_state)
  Button buttonPushState;

  private GoogleApiClient googleApiClient;
  private LatLng latLng;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    textViewGcmDeviceToken.setText(getToken());

    googleApiClient = new GoogleApiClient.Builder(this)
        .addApi(LocationServices.API)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .build();

    setApiInfo();

//    List<ApplicationInfo> applicationInfos = Utils.installedApps(this);
//    for (ApplicationInfo applicationInfo : applicationInfos) {
//      Log.v("sample", applicationInfo.toString());
//    }
  }

  @Override
  protected void onStart() {
    super.onStart();
    googleApiClient.connect();
    EventBus.getDefault().register(this);
    try {
      onEvent(EventBus.getDefault().getStickyEvent(GCMRegistrationEvent.class));
    } catch (Exception ignored) {
    }
    Netmera.enablePopupPresentation();
  }

  @Override
  protected void onResume() {
    super.onResume();

    buttonPushState.setText(Netmera.isPushEnabled() ? R.string.general_disable_push : R.string.general_enable_push);
  }

  @Override
  protected void onStop() {
    super.onStop();
    googleApiClient.disconnect();
    EventBus.getDefault().unregister(this);
    Netmera.disablePopupPresentation();
  }

//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//    getMenuInflater().inflate(R.menu.main, menu);
//    return true;
//  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_set_api_info) {
      setApiInfo();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQ_CODE_MOCK_LOCATION) {
      if (resultCode == RESULT_OK) {
        this.latLng = data.getParcelableExtra(MapActivity.KEY_LAT_LNG);
      }
    }
  }

  @SuppressWarnings("ResourceType")
  @Override
  public void onConnected(Bundle bundle) {
    if (latLng != null) {
      Location location = new Location("mock");
      location.setLatitude(latLng.latitude);
      location.setLongitude(latLng.longitude);
      location.setAccuracy(10f);
      location.setTime(System.currentTimeMillis());
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
      }
      try {
        LocationServices.FusedLocationApi.setMockMode(googleApiClient, true);
        LocationServices.FusedLocationApi.setMockLocation(googleApiClient, location);
        Toast.makeText(this, "Mock location is set!", Toast.LENGTH_SHORT).show();
        latLng = null;
      } catch (Exception e) {
        e.printStackTrace();
        Toast.makeText(this, "Mock location cannot be set, check logs!", Toast.LENGTH_SHORT).show();
      }
    }
  }

  @Override
  public void onConnectionSuspended(int i) {
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
  }

  @OnClick(R.id.text_view_share_device_token) void onClickShareDeviceToken(View v) {
    shareDeviceToken();
  }

  @OnClick(R.id.button_enable_location) void onClickEnableLocation(View v) {
    Netmera.requestPermissionsForLocation();
  }

  @OnClick(R.id.button_mock_location) void onClickMockLocation() {
    startActivityForResult(MapActivity.newIntent(this), REQ_CODE_MOCK_LOCATION);
  }

  @OnClick(R.id.button_push_inbox) void onClickPushInboxTest(View v) {
    startActivity(new Intent(MainActivity.this, PushInboxActivity.class));
  }

  @OnClick(R.id.button_general_api_test) void onClickApiTest(View v) {
    startActivity(new Intent(MainActivity.this, UpdateUserActivity.class));
  }

  @OnClick(R.id.button_event_commerce_test) void onClickEventCommerceTest(View v) {
    startActivity(new Intent(MainActivity.this, EventCommerceActivity.class));
  }

  @OnClick(R.id.button_event_media_test) void onClickEventMediaTest(View v) {
    startActivity(new Intent(MainActivity.this, EventMediaActivity.class));
  }

  @OnClick(R.id.button_event_general_test) void onClickEventGeneralTest(View v) {
    startActivity(new Intent(MainActivity.this, EventGeneralActivity.class));
  }

  @OnClick(R.id.button_push_state) void onClickChangePushState(View v) {
    if (Netmera.isPushEnabled()) {
      Netmera.disablePush();
    } else {
      Netmera.enablePush();
    }
    buttonPushState.setText(Netmera.isPushEnabled() ? R.string.general_disable_push : R.string.general_enable_push);
  }

  @SuppressWarnings("unused")
  public void onEvent(GCMRegistrationEvent gcmRegistrationEvent) {
    if (gcmRegistrationEvent != null) {
      textViewGcmDeviceToken.setText(gcmRegistrationEvent.getDeviceToken());
      putToken(gcmRegistrationEvent.getDeviceToken());
    }
  }

  private void shareDeviceToken() {
    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
    sharingIntent.setType("text/plain");
    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "NetmeraNG Application GCM Info");
    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Device Token:\n" + textViewGcmDeviceToken.getText());
    startActivity(Intent.createChooser(sharingIntent, "Share Device Token"));

  }

  private void setApiInfo() {
    final SharedPreferences pref = getSharedPreferences("ng_prop", MODE_PRIVATE);
    String baseUrl = pref.getString("ek313", null);
    String apiKey = pref.getString("313ke", null);
    if (!TextUtils.isEmpty(baseUrl) && !TextUtils.isEmpty(apiKey)) {
      Netmera.setBaseUrl(baseUrl);
      Netmera.setApiKey(apiKey);
      return;
    }

    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
    View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog_properties, null);
    dialogBuilder.setView(dialogView);

    final EditText editTextBaseUrl = (EditText) dialogView.findViewById(R.id.edit_text_base_url);

    final EditText editTextApiKey = (EditText) dialogView.findViewById(R.id.edit_text_api_key);

    dialogBuilder.setTitle("Netmera Properties");
    dialogBuilder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton) {
        String baseUrl = editTextBaseUrl.getText().toString();
        String apiKey = editTextApiKey.getText().toString();

        Netmera.setBaseUrl(baseUrl);
        Netmera.setApiKey(apiKey);

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("ek313", baseUrl);
        editor.putString("313ke", apiKey);
        editor.commit();
      }
    });
    dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
        finish();
      }
    });
    dialogBuilder.setCancelable(false);

    final AlertDialog alertDialog = dialogBuilder.create();
    alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
      @Override public void onShow(DialogInterface dialog) {
        final Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setOnLongClickListener(new View.OnLongClickListener() {
          @Override public boolean onLongClick(View v) {
            String baseUrl = editTextBaseUrl.getText().toString();
            String apiKey = editTextApiKey.getText().toString();
            if (baseUrl.equals("a") && apiKey.equals("a")) {
              editTextApiKey.setText(PropertiesUtil.netmeraApiKey);
              editTextBaseUrl.setText(PropertiesUtil.netmeraUrl);
            }
            return true;
          }
        });
        positiveButton.setEnabled(false);
        editTextApiKey.addTextChangedListener(new TextWatcher() {
          @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

          }

          @Override public void afterTextChanged(Editable s) {
            if (s.length() > 0 && editTextBaseUrl.getText().length() > 0) {
              positiveButton.setEnabled(true);
            } else {
              positiveButton.setEnabled(false);
            }
          }
        });
        editTextBaseUrl.addTextChangedListener(new TextWatcher() {
          @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

          }

          @Override public void afterTextChanged(Editable s) {
            if (s.length() > 0 && editTextApiKey.getText().length() > 0) {
              positiveButton.setEnabled(true);
            } else {
              positiveButton.setEnabled(false);
            }
          }
        });
      }
    });
    alertDialog.show();
  }

  private void putToken(String token) {
    getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().putString(KEY_TOKEN, token).apply();
  }

  private String getToken() {
    return getSharedPreferences(PREF_NAME, MODE_PRIVATE).getString(KEY_TOKEN, "");
  }

}
