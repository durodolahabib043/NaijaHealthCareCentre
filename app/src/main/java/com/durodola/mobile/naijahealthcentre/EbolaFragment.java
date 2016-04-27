package com.durodola.mobile.naijahealthcentre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mobile on 2016-04-26.
 */
public class EbolaFragment extends AbstractHealthFragment {
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

        return view;
    }
}
