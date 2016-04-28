package com.durodola.mobile.naijahealthcentre;

import com.durodola.mobile.naijahealthcentre.Utils.HealthClass;

import java.util.ArrayList;

/**
 * Created by mobile on 2016-04-28.
 */
public interface HealthCareInterator {
    interface OnFinishedListener {
        void onFinished(ArrayList<HealthClass> contactResultArrayList);
    }

    void findItems(OnFinishedListener listener);
}
