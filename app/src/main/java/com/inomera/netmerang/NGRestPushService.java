package com.inomera.netmerang;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.CacheControl;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author Ogun Gulec
 */
public class NGRestPushService {
  public static final String EXT_ID = "extId";
  public static final String TARGET = "target";
  public static final String NOTIFICATION_KEY = "notificationKey";
  private static OkHttpClient client = new OkHttpClient();
  private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  private static final String KEY_HEADER_NETMERA_REST_API_KEY = "X-netmera-api-key";

  public static void post(String url, String bodyString, Headers headers, Callback callback) {
    RequestBody body = RequestBody.create(JSON, bodyString);
    Request request = new Request.Builder()
        .url(url)
        .post(body)
        .cacheControl(CacheControl.FORCE_NETWORK)
        .headers(headers)
        .build();
    client.newCall(request).enqueue(callback);
  }

  public static void sendTransactionalPush(String notificationKey, Callback callback) {
    JSONObject body = new JSONObject();
    try {
      JSONObject target = new JSONObject();
      target.put(EXT_ID, NGApplication.userId);
      body.put(TARGET, target);
      body.put(NOTIFICATION_KEY, notificationKey);
    } catch (JSONException ignored) {
    }
    Log.d("body", body.toString());
    post(PropertiesUtil.transactionalPushRestUrl,
        body.toString(),
        Headers.of(KEY_HEADER_NETMERA_REST_API_KEY, PropertiesUtil.netmeraRestApiKey),
        callback);
  }
}
