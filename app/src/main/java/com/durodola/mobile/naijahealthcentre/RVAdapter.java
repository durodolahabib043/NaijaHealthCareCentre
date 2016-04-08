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
import java.util.List;

/**
 * Created by mobile on 2016-04-01.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>  {
    List<NameClass> persons;
    ArrayList<HashMap<String, String>> arraylist;
    private static MyItemClickListener mItemClickListener;


    RVAdapter(List<NameClass> persons) {
        this.persons = persons;
    }

    RVAdapter(ArrayList<HashMap<String, String>> arraylist) {
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


    public interface MyItemClickListener {
        void onItemClick(View view, int position);

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
            //  personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }

            //  Log.e("click", " click ");

        }

    }
    public void SetOnItemCLickListener(MyItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }



}