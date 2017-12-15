package com.inomera.netmerang.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inomera.netmerang.R;
import com.netmera.Netmera;
import com.netmera.NetmeraError;
import com.netmera.NetmeraInbox;
import com.netmera.NetmeraInboxFilter;
import com.netmera.NetmeraPushObject;
import com.netmera.NetmeraPushStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by emmar on 19/12/2016.
 */

public class PushInboxActivity  extends BaseActivity implements
        NetmeraInbox.NetmeraInboxFetchCallback, NetmeraInbox.NetmeraInboxStatusCallback {

    private static final int INBOX_PAGE_SIZE = 20;

    private NetmeraInbox inbox;
    private ProgressDialog progressDialog;

    @Bind(R.id.button_inbox_next_page)
    Button buttonFetchNextPage;
    @Bind(R.id.button_inbox_set_status)
    Button buttonSetStatus;
    @Bind(R.id.check_box_fetch_read)
    CheckBox checkBoxFetchRead;
    @Bind(R.id.check_box_fetch_unread)
    CheckBox checkBoxFetchUnread;
    @Bind(R.id.text_view_inbox)
    TextView textViewInbox;
    @Bind(R.id.radio_group_status)
    RadioGroup radioGroupStatus;
    @Bind(R.id.list_view_inbox)
    ListView listViewInbox;
    @Bind(R.id.spinner_page_size)
    Spinner spinnerPageSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_inbox);
        ButterKnife.bind(this);

        enableUp();

        List<Integer> pageSizeList = new ArrayList<>(INBOX_PAGE_SIZE);
        for (int i = 1; i <= INBOX_PAGE_SIZE; i++) {
            pageSizeList.add(i);
        }
        spinnerPageSize.setAdapter(new ArrayAdapter<>(this, R.layout.simple_spinner_item, pageSizeList));
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading!");
        progressDialog.setCancelable(false);
        try {
            progressDialog.show();
        } catch (Exception ignored) {
            //fail silently
        }
    }

    private void dismissProgressDialog() {
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
            } catch (Exception ignored) {
                //fail silently
            }
        }
    }

    @OnClick(R.id.button_inbox_fetch)
    void onClickInboxFetch(View v) {
        if (checkBoxFetchRead.isChecked() || checkBoxFetchUnread.isChecked()) {
            showProgressDialog();

            NetmeraInboxFilter.Builder builder = new NetmeraInboxFilter.Builder();
            builder.pageSize(spinnerPageSize.getSelectedItemPosition() + 1);
            if (checkBoxFetchRead.isChecked() && checkBoxFetchUnread.isChecked()) {
                builder.status(NetmeraPushObject.STATUS_READ | NetmeraPushObject.STATUS_UNREAD);
            } else if (checkBoxFetchRead.isChecked()) {
                builder.status(NetmeraPushObject.STATUS_READ);
            } else {
                builder.status(NetmeraPushObject.STATUS_UNREAD);
            }
            Netmera.fetchInbox(builder.build(), this);
        } else {
            Toast.makeText(this, "Select status!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.button_inbox_next_page)
    void onClickInboxNextPage(View v) {
        showProgressDialog();

        inbox.fetchNextPage(this);
    }

    @OnClick(R.id.button_inbox_set_status)
    void onClickInboxSetStatus(View v) {
        int inboxStatus;
        switch (radioGroupStatus.getCheckedRadioButtonId()) {
            case R.id.radio_fetch_read:
                inboxStatus = NetmeraPushObject.STATUS_READ;
                break;
            case R.id.radio_fetch_unread:
                inboxStatus = NetmeraPushObject.STATUS_UNREAD;
                break;
            case R.id.radio_fetch_deleted:
                inboxStatus = NetmeraPushObject.STATUS_DELETED;
                break;
            default:
                Toast.makeText(this, "Select status!", Toast.LENGTH_SHORT).show();
                return;
        }
        int checkedCount = listViewInbox.getCheckedItemCount();
        if (checkedCount == 0) {
            Toast.makeText(this, "Select message(s)!", Toast.LENGTH_SHORT).show();
            return;
        }
        showProgressDialog();

        List<NetmeraPushObject> selectedPushObjects = new ArrayList<>();
        SparseBooleanArray checkedItemPositions = listViewInbox.getCheckedItemPositions();
        int size = inbox.pushObjects().size();
        for (int i = 0; i < size; i++) {
            if (checkedItemPositions.get(i)) {
                selectedPushObjects.add(inbox.pushObjects().get(i));
            }
        }
        inbox.updateStatus(selectedPushObjects, inboxStatus, this);
    }

    @Override
    public void onFetchInbox(NetmeraInbox netmeraInbox, NetmeraError error) {
        if (error != null) {
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            inbox = netmeraInbox;
            buttonFetchNextPage.setEnabled(inbox.hasNextPage());
            setAdapter();
        }

        dismissProgressDialog();
    }

    @Override
    public void onSetStatusInbox(NetmeraError error) {
        dismissProgressDialog();

        if (error != null) {
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Status has been set successfully!", Toast.LENGTH_SHORT).show();
        }

        setAdapter();
    }

    private void setAdapter() {
        boolean notEmpty = !inbox.pushObjects().isEmpty();
        buttonSetStatus.setEnabled(notEmpty);
        for (int i = 0; i < radioGroupStatus.getChildCount(); i++) {
            radioGroupStatus.getChildAt(i).setEnabled(notEmpty);
        }
        textViewInbox.setVisibility(View.VISIBLE);
        textViewInbox.setText(getString(R.string.inbox_info, inbox.pushObjects().size(),
                inbox.countForStatus(NetmeraPushObject.STATUS_UNREAD),
                inbox.countForStatus(NetmeraPushObject.STATUS_ALL)));

        final List<NetmeraPushObject> pushObjects = inbox.pushObjects();
        int size = pushObjects.size();
        List<String> inboxAsString = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            NetmeraPushObject netmeraPushObject = pushObjects.get(i);
            NetmeraPushStyle pushStyle = netmeraPushObject.getPushStyle();
            String pushString = pushStyle.getContentText() + "\n" + new Date(netmeraPushObject.getSendDate());
            if (netmeraPushObject.getInboxStatus() == NetmeraPushObject.STATUS_READ) {
                pushString = "[READ]\n" + pushString;
            } else if (netmeraPushObject.getInboxStatus() == NetmeraPushObject.STATUS_UNREAD) {
                pushString = "[UNREAD]\n" + pushString;
            } else {
                pushString = "[WTF]\n" + pushString;
            }
            inboxAsString.add(pushString);
        }

        listViewInbox.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listViewInbox.setAdapter(new ArrayAdapter<>(this, R.layout.simple_list_item_multiple_choice, inboxAsString));
        listViewInbox.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Netmera.handlePushObject(PushInboxActivity.this, pushObjects.get(position));
                return false;
            }
        });
    }
}
