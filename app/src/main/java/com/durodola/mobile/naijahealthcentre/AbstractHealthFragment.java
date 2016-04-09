package com.durodola.mobile.naijahealthcentre;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by mobile on 2016-03-16.
 */
public abstract class AbstractHealthFragment extends Fragment {
    private static String NAME = "name";
    private static String LGA = "lga";
    private static String TOWN = "town";
    private static String CONTRACTOR = "contractor";
    GPSService mGPSService;
    String address = "";
    float latitudeN;
    float longitudeN;
    String readStream;


    protected static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    protected void Switchfragment(int layout, Fragment fragment, float longitude, float latitude, String name, String contractor, float currentLattitude, float currentLongitude, String address) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(layout, fragment);
        transaction.addToBackStack(null);
        Bundle bundle = new Bundle();
        bundle.putFloat(String.valueOf(MapFragment.DATA_RECEIVE_LONG), longitude);
        // bundle.putFloat(" longitude", longitude);
        bundle.putFloat(String.valueOf(MapFragment.DATA_RECEIVE_LAT), latitude);
        //  bundle.putFloat(String.valueOf(MapFragment.DATA_RECEIVE_LAT), latitude);
        bundle.putString(String.valueOf(MapFragment.DATA_RECEIVE_NAME), name);
        bundle.putString(String.valueOf(MapFragment.DATA_RECEIVE_CONTRACTOR), contractor);
        bundle.putFloat(String.valueOf(MapFragment.DATA_CURRENT_LATTITUDE), currentLattitude);
        bundle.putFloat(String.valueOf(MapFragment.DATA_CURRENT_LONGITUDE), currentLongitude);
        bundle.putString(String.valueOf(MapFragment.DATA_RECEIVE_ADDRESS), address);

        fragment.setArguments(bundle);

        // Commit the transaction
        transaction.commit();
    }

    protected void progressbarMtd(final ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);

// Create a Handler instance on the main thread
        final Handler handler = new Handler();

// Create and start a new Thread
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                } // Just catch the InterruptedException

                // Now we use the Handler to post back to the main thread
                handler.post(new Runnable() {
                    public void run() {
                        // Set the View's visibility back on the main UI Thread
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }

}
