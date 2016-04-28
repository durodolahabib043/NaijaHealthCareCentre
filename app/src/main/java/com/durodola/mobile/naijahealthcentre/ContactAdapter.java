package com.durodola.mobile.naijahealthcentre;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.durodola.mobile.naijahealthcentre.Fragment.MapFragment;
import com.durodola.mobile.naijahealthcentre.Utils.HealthClass;

import java.util.ArrayList;

/**
 * Created by mobile on 2016-04-01.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.PersonViewHolder> {

    ArrayList<HealthClass> contactResultArrayList;
    Context context;
    HealthCare contact;
    MapFragment mapFragment;
    private static MyItemClickListener mItemClickListener;

    public ContactAdapter(Context context, ArrayList<HealthClass> contactResultArrayList) {
        this.contactResultArrayList = contactResultArrayList;
        this.context = context;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        // contact = HealthCare.getInstance();
        mapFragment = new MapFragment();
        return pvh;
    }


    @Override
    public void onBindViewHolder(PersonViewHolder holder, final int position) {
        if (contactResultArrayList.get(position).getLga() == null) {
            holder.lgatxt_cardview.setText(contactResultArrayList.get(position).getTown());

        } else {
            // holder.lgatxt_cardview.setText(contactResultArrayList.get(position).getLga());
            holder.lgatxt_cardview.setText(contactResultArrayList.get(position).getLga());
        }
        if (contactResultArrayList.get(position).getName() == null) {
            holder.nametxt_cardview.setText(contactResultArrayList.get(position).getContractor());
        } else {

            holder.nametxt_cardview.setText(contactResultArrayList.get(position).getName());
        }


    }

    /*@Override
    public void onAttachedToRecyclerView(RecyclerView rec yclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }*/

    @Override
    public int getItemCount() {
        return contactResultArrayList.size();
    }

    public interface MyItemClickListener {
        public void onItemClick(int position, View v);

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
                mItemClickListener.onItemClick(getAdapterPosition(), v);

            }
        }
    }
    public void SetOnItemCLickListener(MyItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
