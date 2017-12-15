package com.inomera.netmerang;

import com.netmera.NetmeraPushObject;

/**
 * @author Ogun Gulec
 */
public class PushReceivedEvent {
  private NetmeraPushObject push;

  public PushReceivedEvent(NetmeraPushObject push) {
    this.push = push;
  }

  public NetmeraPushObject getPush() {
    return push;
  }
}
