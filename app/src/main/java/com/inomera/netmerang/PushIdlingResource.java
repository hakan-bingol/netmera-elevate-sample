package com.inomera.netmerang;

import android.support.test.espresso.IdlingResource;
import android.text.TextUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class PushIdlingResource {
  private static final String RESOURCE = "Push";
  private static SimpleCountingIdlingResource countingIdlingResource = new SimpleCountingIdlingResource(RESOURCE);

  private static String currentPushId = null;

  public static void incrementFor(String pushId) {
    if (TextUtils.equals(currentPushId, pushId)) {
      countingIdlingResource.increment();
    }
  }

  public static void decrementFor(String pushId) {
    if (TextUtils.equals(currentPushId, pushId)) {
      countingIdlingResource.decrement();
    }
  }

  public static void reset() {
    countingIdlingResource.reset();
    currentPushId = null;

  }

  public static void setCurrentPushId(String pushId) {
    PushIdlingResource.currentPushId = pushId;
  }

  public static IdlingResource getIdlingResource() {
    return countingIdlingResource;
  }

  private static final class SimpleCountingIdlingResource implements IdlingResource {

    private final String mResourceName;

    private final AtomicInteger counter = new AtomicInteger(0);

    // written from main thread, read from any thread.
    private volatile ResourceCallback resourceCallback;

    public SimpleCountingIdlingResource(String resourceName) {
      mResourceName = resourceName;
    }

    @Override
    public String getName() {
      return mResourceName;
    }

    @Override
    public boolean isIdleNow() {
      return counter.get() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
      this.resourceCallback = resourceCallback;
    }

    public void increment() {
      counter.getAndIncrement();
    }

    public void decrement() {
      int counterVal = counter.decrementAndGet();
      if (counterVal == 0) {
        // we've gone from non-zero to zero. That means we're idle now! Tell espresso.
        if (null != resourceCallback) {
          resourceCallback.onTransitionToIdle();
        }
      }
    }

    public void reset() {
      counter.getAndSet(0);
    }
  }

  public static int getCount() {
    return countingIdlingResource.counter.intValue();
  }
}