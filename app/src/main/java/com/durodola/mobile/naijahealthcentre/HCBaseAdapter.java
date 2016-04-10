package com.durodola.mobile.naijahealthcentre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by mobile on 2016-04-04.
 */
public class HCBaseAdapter extends BaseAdapter implements Filterable {

    Context mContext;
    LayoutInflater inflater;
    private List<NameClass> worldpopulationlist = null;
    //private ArrayList<NameClass> arraylist;
    private ArrayList<HashMap<String, String>> towList;
    ArrayList<HashMap<String, String>> arraylist;

    public HCBaseAdapter(Context context, ArrayList<HashMap<String, String>> towList) {
        mContext = context;
        this.towList = towList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<HashMap<String, String>>();
        this.arraylist.addAll(towList);
    }

    @Override
    public int getCount() {
        return towList.size();
    }
    public void animateTo(ArrayList<HashMap<String, String>> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }


    private void applyAndAnimateRemovals(ArrayList<HashMap<String, String>>newModels) {
        for (int i = towList.size() - 1; i >= 0; i--) {
            final HashMap<String, String> model = towList.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(ArrayList<HashMap<String, String>>newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final HashMap<String, String> model = newModels.get(i);
            if (!towList.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(ArrayList<HashMap<String, String>> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final HashMap<String, String> model = newModels.get(toPosition);
            final int fromPosition = towList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }
    public HashMap<String, String> removeItem(int position) {
        final HashMap<String, String>  model = towList.remove(position);
       // notifyItemRemoved(position);
        notifyDataSetChanged();
        return model;
    }

    public void addItem(int position, HashMap<String, String> model) {
        towList.add(position, model);
     //   notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void moveItem(int fromPosition, int toPosition) {
        final HashMap<String, String> model = towList.remove(fromPosition);
        towList.add(toPosition, model);
       // notifyItemMoved(fromPosition, toPosition);
        notifyDataSetChanged();
    }



    @Override
    public HashMap<String, String> getItem(int position) {
        return towList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.single_layout, null);
            // Locate the TextViews in listview_item.xml
            holder.lga = (TextView) view.findViewById(R.id.lga);
            holder.name = (TextView) view.findViewById(R.id.name);
            //  holder.population = (TextView) view.findViewById(R.id.population);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(towList.get(position).get("name"));
        if (towList.get(position).get("lga").equalsIgnoreCase("null")) {
            holder.lga.setText(towList.get(position).get("town"));
        } else {
            holder.lga.setText(towList.get(position).get("lga"));
        }

        // holder.town.setText(towList.get(position).get("town"));

        return view;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class ViewHolder {
        TextView name;
        TextView lga;
        TextView town;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        towList.clear();
        if (charText.length() == 0) {
            towList.addAll(arraylist);
        } else {
            for (HashMap<String, String> wp : arraylist) {
                // check the town
                if (wp.get("lga").toLowerCase(Locale.getDefault()).contains(charText)) {
                    towList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
