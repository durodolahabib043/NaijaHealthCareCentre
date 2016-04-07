package com.durodola.mobile.naijahealthcentre;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mobile on 2016-03-16.
 */
public class HealthCareBaseAdapter extends BaseAdapter {

    Context context;
    List<String> rowItems;
    List<String> rowItems2;

    public HealthCareBaseAdapter(Context context, List<String> items,  List<String> items2) {
        this.context = context;
        this.rowItems = items;
        this.rowItems2 = items2;
    }



  /*  public HealthCareBaseAdapter(Context context, ArrayList myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }*/

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;
        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.single_layout, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }
       // String currentListData = (String) getItem(position);
        NameClass currentListData = (NameClass) getItem(position);

        mViewHolder.name.setText(currentListData.getName());
        mViewHolder.lga.setText(currentListData.getLga());
     //   mViewHolder.ivIcon.setImageResource(currentListData.getImgResId());

        return convertView;
    }




    private class MyViewHolder {
        TextView name, lga, contractor;
      //  ImageView ivIcon;

        public MyViewHolder(View item) {
            name = (TextView) item.findViewById(R.id.name);
            lga = (TextView) item.findViewById(R.id.lga);
           // ivIcon = (ImageView) item.findViewById(R.id.ivIcon);
        }
    }
}
