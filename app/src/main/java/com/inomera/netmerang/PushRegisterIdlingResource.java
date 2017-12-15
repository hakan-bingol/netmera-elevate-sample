package com.inomera.netmerang;

import android.support.test.espresso.IdlingResource;


public class PushRegisterIdlingResource implements IdlingResource {

  private final String resourceName;

  private static final String RESOURCE = "PushRegister";
  private static PushRegisterIdlingResource pushRegisterIdlingResource = new PushRegisterIdlingResource(RESOURCE);

  private ResourceCallback resourceCallback;
  private boolean isIdle;

  private PushRegisterIdlingResource(String resourceName) {
    this.resourceName = resourceName;
  }

  public static void waitForPushRegister() {
    pushRegisterIdlingResource.busy();
  }

  public static void registeredToPush() {
    pushRegisterIdlingResource.idle();
  }

  public static IdlingResource getIdlingResource() {
    return pushRegisterIdlingResource;
  }

  @Override
  public String getName() {
    return resourceName;
  }

  @Override
  public boolean isIdleNow() {
    return isIdle;
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
    this.resourceCallback = resourceCallback;
  }

  public void busy() {
    isIdle = false;
  }

  public void idle() {
    isIdle = true;
    if (resourceCallback != null) {
      resourceCallback.onTransitionToIdle();
    }
  }
}