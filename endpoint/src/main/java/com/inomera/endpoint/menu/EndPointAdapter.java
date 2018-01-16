package com.inomera.endpoint.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.inomera.endpoint.model.Point;
import com.inomera.endpoint.R;

import java.util.List;


public class EndPointAdapter extends BaseAdapter {

    private Context context;
    private List<Point> points;
    private String rootUrl;

    public EndPointAdapter(Context context, List<Point> developmentUrls, String rootUrl) {
        this.context = context;
        this.points = developmentUrls;
        this.rootUrl = rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    @Override
    public int getCount() {
        return points.size();
    }

    @Override
    public Object getItem(int position) {
        return points.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.endpoint_url_item, parent, false);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.tv_name);
            holder.urlTextView = (TextView) convertView.findViewById(R.id.tv_url);
            holder.itemCheckBox = (CheckBox) convertView.findViewById(R.id.cb_urlItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTextView.setText(points.get(position).getName());
        holder.urlTextView.setText(points.get(position).getUrl());
        if (rootUrl.equals(points.get(position).getUrl())) {
            holder.itemCheckBox.setChecked(true);
        } else {
            holder.itemCheckBox.setChecked(false);
        }

        return convertView;
    }

    public static class ViewHolder {
        public TextView nameTextView;
        public TextView urlTextView;
        public CheckBox itemCheckBox;
    }
}
