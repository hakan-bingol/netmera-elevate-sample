package com.inomera.endpoint;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.inomera.endpoint.menu.EndPointActivity;
import com.inomera.endpoint.model.Point;
import com.inomera.endpoint.util.SharedPreference;

import java.util.List;

/**
 * Created by Hakan Bingöl on 13/01/17.
 */

public class EndPoint {

    private static final int MENU_ITEM_ID = 1;
    private EndPoint.Builder builder;

    private EndPoint(EndPoint.Builder builder) {
        this.builder = builder;
        setEndPoints(builder);
        setDefaultEndPoint(builder);
    }

    private void setEndPoints(EndPoint.Builder builder) {
        SharedPreference.getInstance(builder.context).setEndPointUrls(builder.points);
    }

    public List<Point> getEndPoints() {
        return SharedPreference.getInstance(builder.context).getEndPointUrls();
    }

    private void setDefaultEndPoint(EndPoint.Builder builder) {
        if (SharedPreference.getInstance(builder.context).getRootUrl() == null) {
            SharedPreference.getInstance(builder.context).setRootUrl(builder.point);
        }
    }

    public String getRootPoint() {
        return SharedPreference.getInstance(builder.context).getRootUrl().getUrl();
    }

    public void add(Menu menu) {
        addMenuItem(menu);
    }

    private void addMenuItem(Menu menu) {
        if (menu.findItem(MENU_ITEM_ID) == null) {
            MenuItem item = menu.add(Menu.NONE, MENU_ITEM_ID, Menu.NONE, "EndPoint");

            item.setIcon(R.drawable.menu_item);
            item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    builder.context.startActivity(EndPointActivity.newIntent(builder.context));
                    return true;
                }
            });
        }
    }

    public static class Builder {

        private Context context;
        private List<Point> points;
        private Point point;

        public Builder(@NonNull Context context) {
            this.context = context;
        }

        public Builder setEndPoints(@NonNull List<Point> points) {
            this.points = points;
            return this;
        }

        public Builder setDefaultEndPoint(@NonNull Point point) {
            this.point = point;
            return this;
        }

        public EndPoint create() {
            return new EndPoint(this);
        }
    }
}
