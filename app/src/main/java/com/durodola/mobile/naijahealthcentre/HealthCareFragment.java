package com.durodola.mobile.naijahealthcentre;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.durodola.mobile.naijahealthcentre.AbstractFragment.AbstractHealthFragment;
import com.durodola.mobile.naijahealthcentre.Fragment.MapFragment;
import com.durodola.mobile.naijahealthcentre.Utils.GPSService;
import com.durodola.mobile.naijahealthcentre.Utils.HealthClass;
import com.durodola.mobile.naijahealthcentre.Utils.Restclient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HealthCareFragment extends AbstractHealthFragment implements RecyclerView.OnItemTouchListener {

    Fragment mapFragment = new MapFragment();
    ContactAdapter adapterN;
    SearchView searchView;
    RecyclerView rv;
    LinearLayoutManager llm;
    Restclient restclient;
    ArrayList<HealthClass> data;
    GPSService mGPSService;
    String address = "";
    float latitudeN;
    float longitudeN;
    public HealthCareFragment() {
        // Required empty public constructor
    }

    public static HealthCareFragment newInstance() {
        return new HealthCareFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_for_recyclerview, container, false);
        searchView = (SearchView) view.findViewById(R.id.searchviewrecycler);
        restclient = new Restclient();
        data = new ArrayList<HealthClass>();
        //  isConnected();
        if (isConnected() == true) {
            rv = (RecyclerView) view.findViewById(R.id.rv);
            mGPSService = new GPSService(getActivity());
            mGPSService.getLocation();
            rv.setHasFixedSize(true);
            llm = new LinearLayoutManager(getContext());
            rv.setLayoutManager(llm);
            downloadContact();
        } else {
            noNetworkAlert();
        }
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private void getCurrentLocation() {
        if (mGPSService.isLocationAvailable == false) {

            // Here you can ask the user to try again, using return; for that
            Toast.makeText(getActivity(), "Your location is not available, please try again.", Toast.LENGTH_SHORT).show();
            return;

        } else {

            // Getting location co-ordinates

            latitudeN = (float) mGPSService.getLatitude();
            longitudeN = (float) mGPSService.getLongitude();
            address = mGPSService.getLocationAddress();

            Log.e("Latitude: ", " " + latitudeN + " \nLongitude: " + latitudeN + " " + address);

        }


    }
    /// download and  parsing of random api
    private void downloadContact() {
        final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Contact", "Please wait...", false, false);
        getCurrentLocation();
        Call<HealthCare> call = restclient.getContact().getdetailedContact();
        call.enqueue(new Callback<HealthCare>() {
            @Override
            public void onResponse(Call<HealthCare> call, Response<HealthCare> response) {
                data = (ArrayList<HealthClass>) response.body().healthcare;
                adapterN = new ContactAdapter(getContext(), data);
                rv.setAdapter(adapterN);
                loading.dismiss();
                if (adapterN != null) {
                    adapterN.SetOnItemCLickListener(new ContactAdapter.MyItemClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            Switchfragment(R.id.mylayout, mapFragment, Float.parseFloat(data.get(position).getLng()),
                                    Float.parseFloat(data.get(position).getLat()), data.get(position).getName(),
                                    data.get(position).getContractor(), latitudeN, longitudeN, address);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<HealthCare> call, Throwable t) {

            }
        });

    }
}
