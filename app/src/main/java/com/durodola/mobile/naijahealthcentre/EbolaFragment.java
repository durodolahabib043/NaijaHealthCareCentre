package com.durodola.mobile.naijahealthcentre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mobile on 2016-04-26.
 */
public class EbolaFragment extends AbstractHealthFragment {
    TextView ebolaTextview, whoresponse, prevention, treatment, transmission;
    EbolaFragment ebolaFragment;

    public EbolaFragment() {
        // Required empty public constructor
    }


    public static EbolaFragment newInstance() {
        return new EbolaFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ebola_layout, container, false);
        ebolaTextview = (TextView) view.findViewById(R.id.eboladef);
        whoresponse = (TextView) view.findViewById(R.id.whodef);
        prevention = (TextView) view.findViewById(R.id.preventiondef);
        transmission = (TextView) view.findViewById(R.id.transmissiondef);
        treatment = (TextView) view.findViewById(R.id.treatmentdef);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ebolaTextview.setText(getContext().getResources().getString(R.string.meaningebola));
        transmission.setText(getContext().getResources().getString(R.string.transmissiondef));
        whoresponse.setText(getContext().getResources().getString(R.string.whodef));
        prevention.setText(getContext().getResources().getString(R.string.preventiondef));
        treatment.setText(getContext().getResources().getString(R.string.treatmentdef));


    }
}
