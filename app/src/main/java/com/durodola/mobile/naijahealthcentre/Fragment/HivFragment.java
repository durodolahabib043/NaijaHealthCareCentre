package com.durodola.mobile.naijahealthcentre.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.durodola.mobile.naijahealthcentre.AbstractFragment.AbstractHealthFragment;
import com.durodola.mobile.naijahealthcentre.R;

/**
 * Created by mobile on 2016-04-27.
 */
public class HivFragment extends AbstractHealthFragment {
    TextView hivTextview, whoresponse, hivprevention, hivtreatment, hivtransmission, hivaboutebola;
    TextView hivTextviewM, whoresponseM, hivpreventionM, hivtreatmentM, hivtransmissionM, hivaboutebolaM;


    public HivFragment() {
        // Required empty public constructor
    }


    public static HivFragment newInstance() {
        return new HivFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hiv_layout, container, false);

        // hivTextview = (TextView) view.findViewById(R.id.what_you_need);
        hivTextview = (TextView) view.findViewById(R.id.eboladef);
        whoresponse = (TextView) view.findViewById(R.id.whodef);
        hivprevention = (TextView) view.findViewById(R.id.preventiondef);
        hivtransmission = (TextView) view.findViewById(R.id.transmissiondef);
        hivtreatment = (TextView) view.findViewById(R.id.treatmentdef);

        ////main
        hivtreatmentM = (TextView) view.findViewById(R.id.treatment);
        hivTextviewM = (TextView) view.findViewById(R.id.whatsebola);
        whoresponseM = (TextView) view.findViewById(R.id.who);
        hivpreventionM = (TextView) view.findViewById(R.id.prevention);
        hivtransmissionM = (TextView) view.findViewById(R.id.transmission);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // hivTextview.setTextColor(Color.parseColor("#FFFFFF"));
        hivtransmissionM.setTextColor(Color.parseColor("#FFFFFF"));
        hivTextviewM.setTextColor(Color.parseColor("#FFFFFF"));
        whoresponseM.setTextColor(Color.parseColor("#FFFFFF"));
        hivpreventionM.setTextColor(Color.parseColor("#FFFFFF"));


        hivTextview.setText(getContext().getResources().getString(R.string.meaninghiv));
        //.getString(R.string.meaningebola));
        hivtransmission.setText(getContext().getResources().getString(R.string.hivtranmissiondef));
        whoresponse.setText(getContext().getResources().getString(R.string.hivresponse));
        hivprevention.setText(getContext().getResources().getString(R.string.hivprevention));
        hivtreatment.setText(getContext().getResources().getString(R.string.hivtreatment));
    }
}
