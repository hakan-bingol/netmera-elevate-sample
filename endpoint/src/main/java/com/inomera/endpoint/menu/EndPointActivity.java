package com.inomera.endpoint.menu;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.inomera.endpoint.R;
import com.inomera.endpoint.model.Point;
import com.inomera.endpoint.util.AppUtil;
import com.inomera.endpoint.util.SharedPreference;

import java.io.File;
import java.util.List;


public class EndPointActivity extends AppCompatActivity implements View.OnClickListener {


    EditText localHostEditText;
    ListView listView;
    Button saveButton;
    static Context mContext;

    public static Intent newIntent(Context context) {
        mContext = context;
        Intent intent = new Intent(context, EndPointActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (view instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom())) {
                AppUtil.hideKeyboard(this);
            }
        }
        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endpoint);


        localHostEditText = (EditText) findViewById(R.id.et_customUrl);
        saveButton = (Button) findViewById(R.id.btn_save);

        listView = (ListView) findViewById(R.id.lv_url);
        saveButton.setOnClickListener(this);

        showEndPointUrls();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_save) {
            clearApplicationData();
            save();
            restart(mContext, 0);
        }
    }

    protected void save() {
        String localhost = localHostEditText.getText().toString();
        if (TextUtils.isEmpty(localhost)) {
            SharedPreference.getInstance(getApplicationContext()).setIsLocalHostUrl(false);
        } else {
            SharedPreference.getInstance(getApplicationContext()).setIsLocalHostUrl(true);
            SharedPreference.getInstance(getApplicationContext()).setRootUrl(new Point("LocalHost", localhost));
        }
    }

    protected void showEndPointUrls() {
        final List<Point> endPoints = SharedPreference.getInstance(getApplicationContext()).getEndPointUrls();
        Point point = SharedPreference.getInstance(getApplicationContext()).getRootUrl();
        boolean isLocalHostEnable = SharedPreference.getInstance(getApplicationContext()).getIsLocalHostUrl();
        if (isLocalHostEnable) {
            localHostEditText.setText(point.getUrl());
        }

        final EndPointAdapter adapter = new EndPointAdapter(this, endPoints, point.getUrl());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Point rootUrl = endPoints.get(position);
                adapter.setRootUrl(rootUrl.getUrl());
                adapter.notifyDataSetChanged();

                localHostEditText.getText().clear();
                SharedPreference.getInstance(getApplicationContext()).setRootUrl(rootUrl);
            }
        });
    }

    protected void startLauncherActivity() {
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(mContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    protected void clearApplicationData() {
        File cache = mContext.getApplicationContext().getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    boolean deleted = deleteDir(new File(appDir, s));
                    Log.i(mContext.getPackageName(), s + " file deleted status " + deleted);
                }
            }
        }
    }

    protected static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

    protected void restart(Context context, int delay) {
        if (delay == 0) {
            delay = 1;
        }
        Log.i(context.getPackageName(), "restarting app");
        Intent restartIntent = context.getPackageManager()
                .getLaunchIntentForPackage(context.getPackageName());
        PendingIntent intent = PendingIntent.getActivity(context, 0, restartIntent, Intent.FLAG_ACTIVITY_CLEAR_TOP);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC, System.currentTimeMillis() + delay, intent);
        System.exit(2);
    }

}
