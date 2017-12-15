/*
 * Copyright (c) 2016 Inomera Research.
 */

package com.inomera.netmerang;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.runner.lifecycle.ActivityLifecycleCallback;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.view.WindowManager;

/**
 * @author Emmar Kardeslik
 */
public class TestRunner extends AndroidJUnitRunner {
  @Override
  public Application newApplication(ClassLoader cl, String className, Context context)
      throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return super.newApplication(cl, TestApplication.class.getName(), context);
  }

  @Override public void onCreate(Bundle arguments) {
    super.onCreate(arguments);

    ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(
        new ActivityLifecycleCallback() {
          @Override public void onActivityLifecycleChanged(Activity activity, Stage stage) {
            if (stage == Stage.PRE_ON_CREATE) {
              activity.getWindow().addFlags(
                  WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                      WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                      WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
          }
        }
    );
  }
}


