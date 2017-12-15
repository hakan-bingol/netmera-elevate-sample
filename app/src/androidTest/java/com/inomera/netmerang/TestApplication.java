/*
 * Copyright (c) 2016 Inomera Research.
 */

package com.inomera.netmerang;

import com.netmera.Netmera;

/**
 * @author Emmar Kardeslik
 */
public class TestApplication extends NGApplication {

  @Override protected void initProperties() {
    PropertiesUtil.init(this, true);

    PushRegisterIdlingResource.waitForPushRegister();
    Netmera.init(this, PropertiesUtil.gcmSenderId, PropertiesUtil.netmeraApiKey);
    Netmera.setBaseUrl(PropertiesUtil.netmeraUrl);
  }
}
