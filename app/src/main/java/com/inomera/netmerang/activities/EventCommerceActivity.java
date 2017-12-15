/*
 * Copyright (c) 2016 Inomera Research.
 */

package com.inomera.netmerang.activities;

import android.os.Bundle;
import android.view.View;

import com.inomera.netmerang.R;
import com.inomera.netmerang.Utils;
import com.netmera.Netmera;
import com.netmera.events.commerce.NetmeraEventCartAddProduct;
import com.netmera.events.commerce.NetmeraEventCartRemoveProduct;
import com.netmera.events.commerce.NetmeraEventCartView;
import com.netmera.events.commerce.NetmeraEventOrderCancel;
import com.netmera.events.commerce.NetmeraEventProductComment;
import com.netmera.events.commerce.NetmeraEventProductRate;
import com.netmera.events.commerce.NetmeraEventProductView;
import com.netmera.events.commerce.NetmeraEventPurchase;
import com.netmera.events.commerce.NetmeraLineItem;
import com.netmera.events.commerce.NetmeraProduct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Emmar Kardeslik
 */
public class EventCommerceActivity extends BaseActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_commerce);
    ButterKnife.bind(this);
    enableUp();
  }

  private NetmeraProduct createNetmeraProduct() {
    return new NetmeraProduct.Builder(Utils.randomAlphaNumericString(8), Utils.randomUpperCaseString(20), Utils.randomDouble(100))
        .setBrandName(Utils.randomUpperCaseString(10))
        .setBrandId(Utils.randomUpperCaseString(10))
        .setCategoryIds(Collections.singletonList(Utils.randomUpperCaseString(10)))
        .setCategoryNames(Collections.singletonList(Utils.randomUpperCaseString(10)))
        .setVariant(Utils.randomUpperCaseString(10))
        .setKeywords(Arrays.asList(Utils.randomLowerCaseString(5), Utils.randomLowerCaseString(5)))
        .build();
  }

  private NetmeraLineItem createNetmeraLineItem() {
    return new NetmeraLineItem.Builder(Utils.randomAlphaNumericString(8), Utils.randomUpperCaseString(20), Utils.randomDouble(100), Utils.randomInt(8))
        .setBrandName(Utils.randomUpperCaseString(10))
        .setBrandId(Utils.randomUpperCaseString(10))
        .setCategoryIds(Collections.singletonList(Utils.randomUpperCaseString(10)))
        .setCategoryNames(Collections.singletonList(Utils.randomUpperCaseString(10)))
        .setVariant(Utils.randomUpperCaseString(10))
        .setKeywords(Arrays.asList(Utils.randomLowerCaseString(5), Utils.randomLowerCaseString(5)))
        .setCampaignId(Utils.randomUpperCaseString(10))
        .build();
  }

  @OnClick(R.id.button_cart_add_product)
  void onClickCartAddProduct(View v) {
    Netmera.sendEvent(new NetmeraEventCartAddProduct(createNetmeraProduct(), Utils.randomInt(10)));
  }

  @OnClick(R.id.button_cart_remove_product)
  void onClickCartRemoveProduct(View v) {
    Netmera.sendEvent(new NetmeraEventCartRemoveProduct(createNetmeraProduct(), Utils.randomInt(10)));
  }

  @OnClick(R.id.button_cart_view)
  void onClickCartView(View v) {
    Netmera.sendEvent(new NetmeraEventCartView(Utils.randomDouble(500), Utils.randomInt(20)));
  }

  @OnClick(R.id.button_order_cancel)
  void onClickOrderCancel(View v) {
    NetmeraEventOrderCancel event = new NetmeraEventOrderCancel(Utils.randomDouble(500), Utils.randomDouble(600), Utils.randomInt(20));
    event.setPaymentMethod(Utils.randomUpperCaseString(8));
    Netmera.sendEvent(event);
  }

  @OnClick(R.id.button_product_comment)
  void onClickProductComment(View v) {
    Netmera.sendEvent(new NetmeraEventProductComment(createNetmeraProduct()));
  }

  @OnClick(R.id.button_product_rate)
  void onClickProductRate(View v) {
    Netmera.sendEvent(new NetmeraEventProductRate(createNetmeraProduct(), Utils.randomInt(5)));
  }

  @OnClick(R.id.button_product_view)
  void onClickProductView(View v) {
    Netmera.sendEvent(new NetmeraEventProductView(createNetmeraProduct()));
  }

  @OnClick(R.id.button_purchase)
  void onClickPurchase(View v) {
    List<NetmeraLineItem> lineItems = new ArrayList<>(3);
    lineItems.add(createNetmeraLineItem());
    lineItems.add(createNetmeraLineItem());
    lineItems.add(createNetmeraLineItem());

    double subTotal = Utils.randomDouble(500);
    NetmeraEventPurchase event = new NetmeraEventPurchase(subTotal, Utils.randomDouble(600), lineItems);
    event.setPaymentMethod(Utils.randomUpperCaseString(8));
    event.setCoupon(Utils.randomUpperCaseString(12));
    event.setDiscount(Utils.randomDouble(50));
    event.setShippingCost(Utils.randomDouble(20));
    event.setRevenue(subTotal);

    Netmera.sendEvent(event);
  }

}
