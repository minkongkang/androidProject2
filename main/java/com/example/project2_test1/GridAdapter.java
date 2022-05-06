package com.example.project2_test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private List<String> list;

    private LayoutInflater inflater;
        TextView tvItemGridView;

    public GridAdapter(Context context, List<String> list) {
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

    @Override
    public int getCount() {

        return list.size();
        }

    @Override
    public String getItem(int position) {
        return list.get(position);
        }

    @Override
    public long getItemId(int position) {
        return position;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // 구글링으로 참조한 부분

        if (convertView == null) { // 만약 생성된 view가 없다면 그리드뷰 안에 넣은 날짜로 세팅

        convertView = inflater.inflate(R.layout.gridview_item, parent, false);

        }
        tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview);
        tvItemGridView.setText("" + getItem(position));

        return convertView;
        }
}

