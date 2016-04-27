package com.durodola.mobile.naijahealthcentre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by mobile on 2016-04-26.
 */
public class DisplayContentFragment extends AbstractHealthFragment {
    ImageView hivhealthtips, hospital, ebolaHiV, tuberculosis;
    HealthCareFragment healthCareFragment;
    EbolaFragment ebolaFragment;
    HivFragment hivFragment;
    TuberculosisFragment tuberculosisFragment;

    public DisplayContentFragment() {
        // Required empty public constructor
    }


    public static DisplayContentFragment newInstance() {
        return new DisplayContentFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.displaycontent, container, false);
        hivhealthtips = (ImageView) view.findViewById(R.id.HivImage);
        hospital = (ImageView) view.findViewById(R.id.HospiraImage);
        ebolaHiV = (ImageView) view.findViewById(R.id.ebbolaHiv);
        tuberculosis = (ImageView) view.findViewById(R.id.tuberculosis);
        healthCareFragment = HealthCareFragment.newInstance();
        ebolaFragment = EbolaFragment.newInstance();
        hivFragment = HivFragment.newInstance();
        tuberculosisFragment = TuberculosisFragment.newInstance();
       /* hospital.setOnClickListener(this);
        hivhealthtips.setOnClickListener(this);*/
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("hosi", " hosiptal");
                SwitchDisplayfragment(healthCareFragment);
            }
        });
        hivhealthtips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ebiola", " ebola");
                SwitchDisplayfragment(ebolaFragment);
            }
        });
        ebolaHiV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ebiola", " ebola");
                SwitchDisplayfragment(hivFragment);
            }
        });
        tuberculosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchDisplayfragment(tuberculosisFragment);
            }
        });
    }

/*    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.HospiraImage:
                SwitchDisplayfragment(healthCareFragment);
                break;
            *//*case R.id.play:
                //code..
                break;
            case R.id.pause:
                //code..
                break;*//*
            default:
                SwitchDisplayfragment(ebolaFragment);
                break;
        }
       *//* if (v.getId() == R.id.HospiraImage) {
            SwitchDisplayfragment(healthCareFragment);
        } else {
            SwitchDisplayfragment(ebolaFragment);
        }*//*
    }*/
}
