/*
 * Copyright (c) 2016 Inomera Research.
 */

package com.inomera.netmerang.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.inomera.netmerang.R;
import com.netmera.Netmera;
import com.netmera.NetmeraUser;
import com.netmera.events.NetmeraEventRegister;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Emmar Kardeslik
 */
public class UpdateUserActivity extends BaseActivity {

  @Bind(R.id.edit_text_user_id)
  EditText editTextUserId;
  @Bind(R.id.edit_text_name)
  EditText editTextName;
  @Bind(R.id.edit_text_surname)
  EditText editTextSurname;
  @Bind(R.id.edit_text_date_of_birth)
  EditText editTextDob;
  @Bind(R.id.edit_text_email)
  EditText editTextEmail;
  @Bind(R.id.edit_text_msisdn)
  EditText editTextMsisdn;
  @Bind(R.id.edit_text_gender)
  EditText editTextGender;
  @Bind(R.id.edit_text_segments)
  EditText editTextSegments;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_update_user);
    ButterKnife.bind(this);

    enableUp();
  }

  @OnClick(R.id.layout_user_button)
  void onClickSendRequest(View v) {
    //try {
      String userId = editTextUserId.getText().toString();
      if (userId.length() == 0) userId = null;

      String name = editTextName.getText().toString();
      if (name.length() == 0) name = null;

      String surname = editTextSurname.getText().toString();
      if (surname.length() == 0) surname = null;

      String email = editTextEmail.getText().toString();
      if (email.length() == 0) email = null;

      String msisdn = editTextMsisdn.getText().toString();
      if (msisdn.length() == 0) msisdn = null;

      Integer gender = editTextGender.getText().length() != 0 ?
              Integer.valueOf(editTextGender.getText().toString()) : null;

      List<String> segments = editTextSegments.getText().length() != 0 ?
              Arrays.asList(editTextSegments.getText().toString().split(",")) : null;

    Date dob = null;
    try {
      dob = editTextDob.getText().length() != 0 ?
              new SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(editTextDob.getText().toString()) : null;
    } catch (ParseException e) {
      e.printStackTrace();
    }

    NetmeraUser netmeraUser = new NetmeraUser();
      netmeraUser.setUserId(userId);
      netmeraUser.setEmail(email);
      netmeraUser.setMsisdn(msisdn);
      netmeraUser.setGender(gender);
      netmeraUser.setName(name);
      netmeraUser.setSurname(surname);
      netmeraUser.setDateOfBirth(dob);
      netmeraUser.setExternalSegments(segments);
      Netmera.updateUser(netmeraUser);
    Netmera.sendEvent(new NetmeraEventRegister(userId));

      Toast.makeText(UpdateUserActivity.this, "Request has been sent!", Toast.LENGTH_SHORT).show();
//    } catch (Exception e) {
//      Toast.makeText(UpdateUserActivity.this, "Please fill form properly!", Toast.LENGTH_SHORT).show();
//    }
  }
}
