package com.durodola.mobile.naijahealthcentre;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by mobile on 2016-04-01.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    ArrayList<HashMap<String, String>> arraylist;
    private static MyItemClickListener mItemClickListener;


    public RVAdapter(ArrayList<HashMap<String, String>> arraylist) {
        this.arraylist = arraylist;
    }


    // private final View.OnClickListener mOnClickListener = new MyOnClickListener();

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.lgatxt_cardview.setText(arraylist.get(position).get("lga"));
        holder.nametxt_cardview.setText(arraylist.get(position).get("name"));
        //  holder.personPhoto.setImageResource(persons.get(position).photoId);


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public void animateTo(ArrayList<HashMap<String, String>> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }


    private void applyAndAnimateRemovals(ArrayList<HashMap<String, String>> newModels) {
        for (int i = arraylist.size() - 1; i >= 0; i--) {
            final HashMap<String, String> model = arraylist.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(ArrayList<HashMap<String, String>> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final HashMap<String, String> model = newModels.get(i);
            if (!arraylist.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(ArrayList<HashMap<String, String>> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final HashMap<String, String> model = newModels.get(toPosition);
            final int fromPosition = arraylist.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public HashMap<String, String> removeItem(int position) {
        final HashMap<String, String> model = arraylist.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, HashMap<String, String> model) {
        arraylist.add(position, model);
        notifyItemInserted(position);

    }

    public void moveItem(int fromPosition, int toPosition) {
        final HashMap<String, String> model = arraylist.remove(fromPosition);
        arraylist.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);

    }


    public interface MyItemClickListener {
        public void onItemClick(int position, View v);

    }

    public ArrayList<HashMap<String, String>> filter(ArrayList<HashMap<String, String>> models, ArrayList<HashMap<String, String>> completeList, String query) {
        query = query.toLowerCase(Locale.getDefault());
        final ArrayList<HashMap<String, String>> filteredModelList = new ArrayList<HashMap<String, String>>();


        if (query.length() == 0) {
            filteredModelList.clear();

            filteredModelList.addAll(completeList);

        } else {
            for (int i = 0, l = models.size(); i < l; i++) {
                final HashMap<String, String> p = models.get(i);
                if (p.toString().toLowerCase().contains(query))
                    filteredModelList.add(p);

            }
        }
        notifyDataSetChanged();

        return filteredModelList;
    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView lgatxt_cardview;
        TextView nametxt_cardview;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            lgatxt_cardview = (TextView) itemView.findViewById(R.id.name_cardview);
            nametxt_cardview = (TextView) itemView.findViewById(R.id.lga_cardview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(getAdapterPosition(), v);

            }

        }

    }

    public void SetOnItemCLickListener(MyItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


}