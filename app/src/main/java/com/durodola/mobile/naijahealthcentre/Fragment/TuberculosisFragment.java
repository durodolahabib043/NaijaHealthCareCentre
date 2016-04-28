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
public class TuberculosisFragment extends AbstractHealthFragment {
    TextView tuberTextview, whoresponse, tuberprevention, tubertreatment, tubertransmission, tuberaboutebola;
    TextView tuberebolaTextviewM, whoresponseM, tuberpreventionM, tubertreatmentM, tubertransmissionM, tuberaboutebolaM;

    public TuberculosisFragment() {
        // Required empty public constructor
    }


    public static TuberculosisFragment newInstance() {
        return new TuberculosisFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tuberculosis_layout, container, false);

        tuberaboutebola = (TextView) view.findViewById(R.id.what_you_need);
        tuberTextview = (TextView) view.findViewById(R.id.eboladef);
        whoresponse = (TextView) view.findViewById(R.id.whodef);
        tuberprevention = (TextView) view.findViewById(R.id.preventiondef);
        tubertransmission = (TextView) view.findViewById(R.id.transmissiondef);
        tubertreatment = (TextView) view.findViewById(R.id.treatmentdef);

        tubertreatmentM = (TextView) view.findViewById(R.id.treatment);
        tuberebolaTextviewM = (TextView) view.findViewById(R.id.whatsebola);
        whoresponseM = (TextView) view.findViewById(R.id.who);
        tuberpreventionM = (TextView) view.findViewById(R.id.prevention);
        tubertransmissionM = (TextView) view.findViewById(R.id.transmission);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tuberebolaTextviewM.setTextColor(Color.parseColor("#FFFFFF"));
        tubertransmissionM.setTextColor(Color.parseColor("#FFFFFF"));
        tubertreatmentM.setTextColor(Color.parseColor("#FFFFFF"));
        whoresponseM.setTextColor(Color.parseColor("#FFFFFF"));
        tuberpreventionM.setTextColor(Color.parseColor("#FFFFFF"));


        tuberTextview.setText(getContext().getResources().getString(R.string.tuber));
        //.getString(R.string.meaningebola));
        tubertransmission.setText(getContext().getResources().getString(R.string.tubertransmision));
        whoresponse.setText(getContext().getResources().getString(R.string.whodef));
        tuberprevention.setText(getContext().getResources().getString(R.string.tuberprevention));
        tubertreatment.setText(getContext().getResources().getString(R.string.tubertreatmtn));
    }
}
