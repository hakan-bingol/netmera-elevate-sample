/*
 * Copyright (c) 2015 Inomera Research.
 */

package com.inomera.netmerang.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.inomera.netmerang.NGRestPushService;
import com.inomera.netmerang.PushIdlingResource;
import com.inomera.netmerang.PushReceivedEvent;
import com.inomera.netmerang.R;
import com.netmera.NetmeraPushObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.inomera.netmerang.R.id.send_big_image_push;
import static com.inomera.netmerang.R.id.send_big_text_push;
import static com.inomera.netmerang.R.id.send_interactive_big_image_push;
import static com.inomera.netmerang.R.id.send_interactive_deeplink_push;
import static com.inomera.netmerang.R.id.send_interactive_push;
import static com.inomera.netmerang.R.id.send_long_push;
import static com.inomera.netmerang.R.id.send_medium_push;
import static com.inomera.netmerang.R.id.send_short_push;

/**
 * @author Ogun Gulec
 */
public class TransactionalPushActivity extends BaseActivity {
  public static String NOTIFICATION_KEY_SHORT_PUSH = "28";
  public static String NOTIFICATION_KEY_MEDIUM_PUSH = "29";
  public static String NOTIFICATION_KEY_LONG_PUSH = "31";
  public static String NOTIFICATION_KEY_INTERACTIVE_PUSH = "42";
  public static String NOTIFICATION_KEY_INTERACTIVE_DEEPLINK_PUSH = "43";
  public static String NOTIFICATION_KEY_BIG_IMAGE_PUSH = "44";
  public static String NOTIFICATION_KEY_INTERACTIVE_BIG_IMAGE_PUSH = "45";
  public static String NOTIFICATION_KEY_BIG_TEXT_PUSH = "46";

  private NetmeraPushObject netmeraPushObject;

  private static final String TAG = TransactionalPushActivity.class.getSimpleName();

  @Bind(R.id.text_view_received_push_id)
  TextView textViewReceivedPushId;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_push);
    ButterKnife.bind(this);
    enableUp();
  }


  @Override
  protected void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override
  protected void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @OnClick({
      R.id.send_short_push,
      R.id.send_medium_push,
      R.id.send_long_push,
      R.id.send_interactive_push,
      R.id.send_interactive_deeplink_push,
      R.id.send_big_image_push,
      R.id.send_interactive_big_image_push,
      R.id.send_big_text_push,
  })
  public void onClick(View view) {
    String notificationKey;
    switch (view.getId()) {
      case send_short_push:
        notificationKey = NOTIFICATION_KEY_SHORT_PUSH;
        break;
      case send_medium_push:
        notificationKey = NOTIFICATION_KEY_MEDIUM_PUSH;
        break;
      case send_long_push:
        notificationKey = NOTIFICATION_KEY_LONG_PUSH;
        break;
      case send_interactive_push:
        notificationKey = NOTIFICATION_KEY_INTERACTIVE_PUSH;
        break;
      case send_interactive_deeplink_push:
        notificationKey = NOTIFICATION_KEY_INTERACTIVE_DEEPLINK_PUSH;
        break;
      case send_big_image_push:
        notificationKey = NOTIFICATION_KEY_BIG_IMAGE_PUSH;
        break;
      case send_interactive_big_image_push:
        notificationKey = NOTIFICATION_KEY_INTERACTIVE_BIG_IMAGE_PUSH;
        break;
      case send_big_text_push:
        notificationKey = NOTIFICATION_KEY_BIG_TEXT_PUSH;
        break;
      default:
        notificationKey = null;
    }
    if (notificationKey != null) {
      sendPushWithId(notificationKey);
    }
  }

  private void sendPushWithId(final String pushId) {
    PushIdlingResource.setCurrentPushId(pushId);
    netmeraPushObject = null;
    //incrementFor for push receiving
    PushIdlingResource.incrementFor(pushId);

    PushIdlingResource.incrementFor(pushId);
    NGRestPushService.sendTransactionalPush(pushId, new Callback() {
      @Override public void onFailure(Call call, IOException e) {
        PushIdlingResource.decrementFor(pushId);
      }

      @Override public void onResponse(Call call, Response response) throws IOException {
        PushIdlingResource.decrementFor(pushId);
      }
    });

  }

  public void onEvent(PushReceivedEvent event) {
    netmeraPushObject = event.getPush();
    textViewReceivedPushId.setText(netmeraPushObject.getPushId());
    //decrementFor for push receiving
    PushIdlingResource.decrementFor(netmeraPushObject.getPushId());
  }

  public NetmeraPushObject getNetmeraPushObject() {
    return netmeraPushObject;
  }
}
