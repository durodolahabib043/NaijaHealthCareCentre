package com.durodola.mobile.naijahealthcentre.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.durodola.mobile.naijahealthcentre.AbstractFragment.AbstractHealthFragment;
import com.durodola.mobile.naijahealthcentre.HealthCareFragment;
import com.durodola.mobile.naijahealthcentre.Present.DisPresenter;
import com.durodola.mobile.naijahealthcentre.R;

/**
 * Created by mobile on 2016-04-26.
 */
public class DisplayContentFragment extends AbstractHealthFragment implements DisplayView, View.OnClickListener {
    Button hivhealthtips, hospital, ebolaHiV, tuberculosis;
    HealthCareFragment healthCareFragment;
    EbolaFragment ebolaFragment;
    HivFragment hivFragment;
    TuberculosisFragment tuberculosisFragment;
    private DisPresenter presenter;

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
        hivhealthtips = (Button) view.findViewById(R.id.HivImage);
        hospital = (Button) view.findViewById(R.id.HospiraImage);
        ebolaHiV = (Button) view.findViewById(R.id.ebbolaHiv);
        tuberculosis = (Button) view.findViewById(R.id.tuberculosis);
        healthCareFragment = HealthCareFragment.newInstance();
        ebolaFragment = EbolaFragment.newInstance();
        hivFragment = HivFragment.newInstance();
        tuberculosisFragment = TuberculosisFragment.newInstance();
        hivhealthtips.setOnClickListener(this);
        ebolaHiV.setOnClickListener(this);
        tuberculosis.setOnClickListener(this);
        hospital.setOnClickListener(this);
        presenter = new DisplayPresenterImpl(this);
        return view;

    }

    @Override
    public void switchFragment() {
        SwitchDisplayfragment(healthCareFragment);

    }

    @Override
    public void switchFragmentEbola() {
        SwitchDisplayfragment(ebolaFragment);
    }

    @Override
    public void switchFragmentHiv() {
        SwitchDisplayfragment(hivFragment);
    }

    @Override
    public void switchFragmentTuberculosis() {
        SwitchDisplayfragment(tuberculosisFragment);
    }

    @Override
    public Void getUsererror() {
        return null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.HivImage) {

            presenter.switchFrag();
        } else if (v.getId() == R.id.ebbolaHiv) {
            presenter.switchFragHiv();
        } else if (v.getId() == R.id.tuberculosis) {
            presenter.switchFragTuber();
        } else {
            presenter.switchFragHospital();
        }


    }


}
