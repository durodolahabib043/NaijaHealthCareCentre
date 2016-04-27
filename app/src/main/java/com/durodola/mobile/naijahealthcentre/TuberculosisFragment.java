package com.durodola.mobile.naijahealthcentre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mobile on 2016-04-27.
 */
public class TuberculosisFragment extends AbstractHealthFragment {
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
        return view;
    }
}
