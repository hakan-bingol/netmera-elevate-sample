package com.inomera.endpoint.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.inomera.endpoint.model.Point;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SharedPreference {

    public static final String APP_NAME = "end-point";
    public static final String ROOT_URL = "root-url";
    public static final String END_POINT_URLS = "end-point-urls";
    public static final String IS_LOCALHOST_URL = "is-localhost-url";

    private static SharedPreference sharedPreference;

    SharedPreferences preferences;
    Editor editor;
    Context context;

    public SharedPreference(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(APP_NAME, 0);
        editor = preferences.edit();
    }

    public static SharedPreference getInstance(Context context) {
        if (sharedPreference == null)
            sharedPreference = new SharedPreference(context);
        return sharedPreference;
    }

    public void setRootUrl(Point rootUrl) {
        String json = new Gson().toJson(rootUrl);
        editor.putString(ROOT_URL, json);
        editor.commit();
    }

    public Point getRootUrl() {
        String json = preferences.getString(ROOT_URL, null);
        if (!TextUtils.isEmpty(json)) {
            return new Gson().fromJson(json, Point.class);
        }

        return null;
    }

    public void setEndPointUrls(List<Point> points) {
        String json = new Gson().toJson(points);
        editor.putString(END_POINT_URLS, json);
        editor.commit();
    }

    public void setIsLocalHostUrl(boolean b) {
        editor.putBoolean(IS_LOCALHOST_URL, b);
        editor.commit();
    }

    public boolean getIsLocalHostUrl() {
        return preferences.getBoolean(IS_LOCALHOST_URL, false);
    }

    public List<Point> getEndPointUrls() {
        String json = preferences.getString(END_POINT_URLS, null);
        List<Point> points = new ArrayList<>();
        if (!TextUtils.isEmpty(json)) {
            Type listType = new TypeToken<ArrayList<Point>>() {
            }.getType();
            points.addAll((Collection<? extends Point>) new Gson().fromJson(json, listType));
        }

        return points;
    }

}
