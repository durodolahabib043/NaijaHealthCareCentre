package com.durodola.mobile.naijahealthcentre;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by mobile on 2016-03-16.
 */
public abstract class AbstractHealthFragment extends Fragment {
    private static String NAME = "name";
    private static String LGA = "lga";
    private static String TOWN = "town";
    private static String CONTRACTOR = "contractor";


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

    protected void Switchfragment(int layout, Fragment fragment, float longitude, float latitude, String name, String contractor) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(layout, fragment);
        transaction.addToBackStack(null);
        Bundle bundle = new Bundle();
        bundle.putFloat(String.valueOf(MapFragment.DATA_RECEIVE_LONG), longitude);
        // bundle.putFloat(" longitude", longitude);
        bundle.putFloat(String.valueOf(MapFragment.DATA_RECEIVE_LAT), latitude);
        bundle.putFloat(String.valueOf(MapFragment.DATA_RECEIVE_LAT), latitude);
        bundle.putString(String.valueOf(MapFragment.DATA_RECEIVE_NAME), name);
        bundle.putString(String.valueOf(MapFragment.DATA_RECEIVE_CONTRACTOR), contractor);

        fragment.setArguments(bundle);

        // Commit the transaction
        transaction.commit();
    }


}
