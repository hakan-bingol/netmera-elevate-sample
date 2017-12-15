/*
 * Copyright (c) 2015 Inomera Research.
 */

package com.inomera.netmerang;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.List;
import java.util.Random;

/**
 * @author Emmar Kardeslik
 */
public final class Utils {
  private Utils() {
    //no instance
  }

  public static boolean installed(Context context, String packageName) {
    boolean installed = true;
    try {
      context.getPackageManager().getApplicationInfo(packageName, 0);
    } catch (PackageManager.NameNotFoundException ignored) {
      installed = false;
    }
    return installed;
  }

  public static List<ApplicationInfo> installedApps(Context context) {
    return context.getPackageManager().getInstalledApplications(0);
  }

  private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
  private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String NUMERIC = "0123456789";
  private static final String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static Random rnd = new Random();

  public static String randomLowerCaseString(int len) {
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(LOWERCASE.charAt(rnd.nextInt(LOWERCASE.length())));
    }
    return sb.toString();
  }

  public static String randomUpperCaseString(int len) {
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(UPPERCASE.charAt(rnd.nextInt(UPPERCASE.length())));
    }
    return sb.toString();
  }

  public static String randomNumericString(int len) {
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(NUMERIC.charAt(rnd.nextInt(NUMERIC.length())));
    }
    return sb.toString();
  }

  public static String randomAlphaNumericString(int len) {
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(ALPHANUMERIC.charAt(rnd.nextInt(ALPHANUMERIC.length())));
    }
    return sb.toString();
  }

  public static double randomDouble(int range) {
    return rnd.nextDouble() * range;
  }

  public static int randomInt(int range) {
    return rnd.nextInt(range) + 1;
  }
}
