package com.durodola.mobile.naijahealthcentre.AbstractFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.durodola.mobile.naijahealthcentre.Fragment.MapFragment;
import com.durodola.mobile.naijahealthcentre.R;

/**
 * Created by mobile on 2016-03-16.
 */
public abstract class AbstractHealthFragment extends Fragment {

    protected void Switchfragment(int layout, Fragment fragment, float longitude, float latitude, String name, String contractor, float currentLattitude, float currentLongitude, String address) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(layout, fragment);
        transaction.addToBackStack(null);
        Bundle bundle = new Bundle();
        bundle.putFloat(String.valueOf(MapFragment.DATA_RECEIVE_LONG), longitude);
        bundle.putFloat(String.valueOf(MapFragment.DATA_RECEIVE_LAT), latitude);
        bundle.putString(String.valueOf(MapFragment.DATA_RECEIVE_NAME), name);
        bundle.putString(String.valueOf(MapFragment.DATA_RECEIVE_CONTRACTOR), contractor);
        bundle.putFloat(String.valueOf(MapFragment.DATA_CURRENT_LATTITUDE), currentLattitude);
        bundle.putFloat(String.valueOf(MapFragment.DATA_CURRENT_LONGITUDE), currentLongitude);
        bundle.putString(String.valueOf(MapFragment.DATA_RECEIVE_ADDRESS), address);

        fragment.setArguments(bundle);
        transaction.commit();
    }

    protected void SwitchDisplayfragment(Fragment fragment) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.mylayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public boolean isConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public void noNetworkAlert() {
        // display dialog when no internet
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Uh Oh");
        builder.setMessage("We couldn't retrieve the data. Please check your network connection and try again.");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }

        });
        builder.show();
    }

}
