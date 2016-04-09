package com.durodola.mobile.naijahealthcentre;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by mobile on 2016-04-05.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    RelativeLayout rv;
    private GoogleMap mMap;
    private SupportMapFragment fragment;
    public static float DATA_RECEIVE_LONG = (float) 3342.34;

    public static float DATA_RECEIVE_LAT = (float) 3452.34;
    public static String DATA_RECEIVE_NAME = "name";
    public static String DATA_RECEIVE_CONTRACTOR = "contractor";
    public static float DATA_CURRENT_LATTITUDE = (float) 342.34;
    public static float DATA_CURRENT_LONGITUDE = (float) 342.343;

    public static String DATA_RECEIVE_ADDRESS = "addrerss";
    //float  f = 3342.34;
    float longtitude, currentLongitude;
    Bundle bundle;
    float lattitude, currentLattitude;
    String name, contractor, address;
    public static String TAG;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.mapfragment, container, false);
        TAG = getContext().getPackageName();


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();


        bundle = this.getArguments();
        longtitude = bundle.getFloat(String.valueOf(DATA_RECEIVE_LONG));
        lattitude = bundle.getFloat(String.valueOf(DATA_RECEIVE_LAT));
        Log.e("map lat" + lattitude, " map long" + longtitude);
        name = bundle.getString(DATA_RECEIVE_NAME);

        contractor = bundle.getString(DATA_RECEIVE_NAME);
        currentLattitude = bundle.getFloat(String.valueOf(DATA_CURRENT_LATTITUDE));
        currentLongitude = bundle.getFloat(String.valueOf(DATA_CURRENT_LONGITUDE));
        address = bundle.getString(DATA_RECEIVE_ADDRESS);
        Log.e(" addreaa" + TAG, address);

        fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, fragment).commit();
        }
        fragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng sydney = new LatLng(lattitude, longtitude);
        LatLng mylocation = new LatLng(currentLattitude, currentLongitude);
        Log.e("latitude " + lattitude + " longitude  " + longtitude, "currentlat  " + currentLattitude + "currLong  " + currentLongitude);
        mMap.addPolyline(new PolylineOptions()
                .add(sydney, mylocation)
                .width(5)
                .color(Color.RED));
        // add address
        if (name.equalsIgnoreCase("null")) {
            name = contractor;
            mMap.addMarker(new MarkerOptions().position(sydney).title(name));
            mMap.addMarker(new MarkerOptions().position(mylocation).title(address));

        } else {
            mMap.addMarker(new MarkerOptions().position(sydney).title(name));
            mMap.addMarker(new MarkerOptions().position(mylocation).title(address));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }


}
