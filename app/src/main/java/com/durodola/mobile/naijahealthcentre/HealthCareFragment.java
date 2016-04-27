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
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HealthCareFragment extends AbstractHealthFragment implements RecyclerView.OnItemTouchListener, SearchView.OnQueryTextListener {

    Fragment mapFragment = new MapFragment();
    ProgressBar progressbar;
    RVAdapter adapter;
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
    String urlreal = "https://api.myjson.com/bins/41u0g";
    private RVAdapter.MyItemClickListener mListener;
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
        progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
        //    progressbarMtd(progressbar);
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

            searchView.setOnQueryTextListener(this);

            downloadContact();

        } else {
            noNetworkAlert();
        }


        return view;

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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       /* final ArrayList<HashMap<String, String>> filteredModelList = adapter.filter(towList, completeList, newText);
        adapter.animateTo(filteredModelList);
        rv.scrollToPosition(0)*/
        ;
        return true;
    }
    /// download and  parsing of random api
    private void downloadContact() {
        final ProgressDialog loading = ProgressDialog.show(getContext(), "Fetching Contact", "Please wait...", false, false);
        Call<HealthCare> call = restclient.getContact().getdetailedContact();
        call.enqueue(new Callback<HealthCare>() {
            @Override
            public void onResponse(Call<HealthCare> call, Response<HealthCare> response) {
                Log.e("data34r234r", " " + response.body().healthcare);
                data = (ArrayList<HealthClass>) response.body().healthcare;
                rv.setAdapter(new ContactAdapter(getContext(), data));
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<HealthCare> call, Throwable t) {

            }
        });

    }
}
