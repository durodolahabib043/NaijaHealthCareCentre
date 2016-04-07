package com.durodola.mobile.naijahealthcentre;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mobile on 2016-04-05.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    RelativeLayout rv;
    private GoogleMap mMap;
    private SupportMapFragment fragment;
    public static float DATA_RECEIVE_LONG = (float) 3342.34;

    public static float DATA_RECEIVE_LAT = (float) 342.34;
    public static String DATA_RECEIVE_NAME = "name";
    public static String DATA_RECEIVE_CONTRACTOR = "contractor";
    //float  f = 3342.34;
    float longtitude;
    Bundle bundle;
    float lattitude;
    String name, contractor;
    int increment = 4;
    MyLocation myLocation = new MyLocation();

    LocationManager lm;
    String provider;
    Location l;
    GPSService mGPSService;
    String address = "";
    double latitudeN;
    double longitudeN;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.mapfragment, container, false);
        mGPSService = new GPSService(getActivity());
        mGPSService.getLocation();


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();


        bundle = this.getArguments();
        longtitude = bundle.getFloat(String.valueOf(DATA_RECEIVE_LONG));
        lattitude = bundle.getFloat(String.valueOf(DATA_RECEIVE_LAT));
        name = bundle.getString(DATA_RECEIVE_NAME);
        contractor = bundle.getString(DATA_RECEIVE_NAME);

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


        if (mGPSService.isLocationAvailable == false) {

            // Here you can ask the user to try again, using return; for that
            Toast.makeText(getActivity(), "Your location is not available, please try again.", Toast.LENGTH_SHORT).show();
            return;

            // Or you can continue without getting the location, remove the return; above and uncomment the line given below
            // address = "Location not available";
        } else {

            // Getting location co-ordinates
            latitudeN = mGPSService.getLatitude();
            longitudeN = mGPSService.getLongitude();
            //Toast.makeText(getActivity(), "Latitude:" + latitude + " | Longitude: " + longitude, Toast.LENGTH_LONG).show();

            address = mGPSService.getLocationAddress();

            //tvLocation.setText("Latitude: " + latitude + " \nLongitude: " + longitude);
            //tvAddress.setText("Address: " + address);
        }

    //    Toast.makeText(getActivity(), "Your address is: " + address, Toast.LENGTH_SHORT).show();

        // make sure you close the gps after using it. Save user's battery power
        mGPSService.closeGPS();


        LatLng sydney = new LatLng(lattitude, longtitude);
        LatLng mylocation = new LatLng(latitudeN, longitudeN);
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
