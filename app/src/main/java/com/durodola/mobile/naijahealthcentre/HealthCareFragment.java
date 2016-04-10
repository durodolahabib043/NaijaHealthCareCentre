package com.durodola.mobile.naijahealthcentre;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class HealthCareFragment extends AbstractHealthFragment implements RecyclerView.OnItemTouchListener, SearchView.OnQueryTextListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ListView listview;
    EditText searchview;
    GPSService mGPSService;
    String address = "";
    float latitudeN;
    float longitudeN;


    String urlreal = "https://api.myjson.com/bins/41u0g";
    private RVAdapter.MyItemClickListener mListener;


    ArrayList<HashMap<String, String>> towList , completeList;
    Fragment mapFragment = new MapFragment();
    ProgressBar progressbar;
    RVAdapter adapter;
    SearchView searchView;


    private static final String TAG_LGA = "lga";
    private static final String TAG_TOWN = "town";
    private static final String TAG_NAME = "name";
    private static final String TAG_CONTRACTOR = "contractor";
    private static final String TAG_LNG = "lng";
    private static final String TAG_LAT = "lat";
    RecyclerView rv;
    LinearLayoutManager llm;


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
        progressbarMtd(progressbar);
        searchView = (SearchView) view.findViewById(R.id.searchviewrecycler);

        towList = new ArrayList<HashMap<String, String>>();
        completeList = new ArrayList<HashMap<String, String>>();
        rv = (RecyclerView) view.findViewById(R.id.rv);
        mGPSService = new GPSService(getActivity());
        mGPSService.getLocation();
        // initializeData();

        // not sure
        rv.setHasFixedSize(true);
        llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        //  checkForUpdates();


        // searchview
        searchView.setOnQueryTextListener(this);


        new DownloadTask().execute();

        return view;

    }


    // mathod to parse json
    private void jsonParserr(String in) {

        JSONObject reader = null;
        String lga, town, name, contractor, lng, lat;
        // getCurrentLocation();

        try {
            /// healthcare

            reader = new JSONObject(in);
            JSONArray healthcareArray = reader.getJSONArray("healthcare");
            //JSONArray array = new JSONArray(in);
            for (int i = 0; i < healthcareArray.length(); i++) {
                JSONObject row = healthcareArray.getJSONObject(i);
                lga = row.getString("lga");
                town = row.getString("town");
                name = row.getString("name");
                contractor = row.getString("contractor");
                lng = row.getString("lng");
                lat = row.getString("lat");

                HashMap<String, String> contact = new HashMap<String, String>();
                if (name.equalsIgnoreCase("null")) {
                    name = contractor;

                }
                if (lga.equalsIgnoreCase("null")) {
                    lga = town;
                }

                // adding each child node to HashMap key => value
                contact.put(TAG_LGA, lga);
                contact.put(TAG_TOWN, town);
                contact.put(TAG_NAME, name);
                contact.put(TAG_CONTRACTOR, contractor);
                contact.put(TAG_LAT, lat);
                contact.put(TAG_LNG, lng);

                towList.add(contact);
                completeList.add(contact);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


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

            // Or you can continue without getting the location, remove the return; above and uncomment the line given below
            // address = "Location not available";
        } else {

            // Getting location co-ordinates

            latitudeN = (float) mGPSService.getLatitude();
            longitudeN = (float) mGPSService.getLongitude();
            //Toast.makeText(getActivity(), "Latitude:" + latitude + " | Longitude: " + longitude, Toast.LENGTH_LONG).show();

            address = mGPSService.getLocationAddress();

            Log.e("Latitude: ", " " + latitudeN + " \nLongitude: " + latitudeN + " " + address);
            //tvAddress.setText("Address: " + address);
        }


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final ArrayList<HashMap<String, String>> filteredModelList = adapter.filter(towList, completeList, newText);
        //adapter.
        adapter.animateTo(filteredModelList);
        rv.scrollToPosition(0);
        //not refre adapter.notifyDataSetChanged();
        // adapter.notifyDataSetChanged();

        return true;
    }

/*
    private ArrayList<HashMap<String, String>> filter(ArrayList<HashMap<String, String>> models, String query) {
        query = query.toLowerCase(Locale.getDefault());
        final ArrayList<HashMap<String, String>> filteredModelList = new ArrayList<HashMap<String, String>>();
        filteredModelList.clear();

        if (query.length() == 0) {
            filteredModelList.addAll(models);

        } else {
            for (int i = 0, l = models.size(); i < l; i++) {
                final HashMap<String, String> p = models.get(i);
                if (p.toString().toLowerCase().contains(query))
                    filteredModelList.add(p);

            }
        }


        */
/*for (HashMap<String, String> model : models) {


            String text = model.get("lga").toLowerCase();

            if (text.contains(query)) {
                filteredModelList.add(model);
                //adapter.notifyDataSetChanged();
            }
        }*//*

        //  adapter.notifyDataSetChanged();
        return filteredModelList;
    }
*/


    private class DownloadTask extends AsyncTask<String, Integer, String> {
        String stringUrl;
        String readStream;

        @Override
        protected String doInBackground(String... url) {

            try {
                URL url1 = new URL(urlreal);
                HttpURLConnection con = (HttpURLConnection) url1.openConnection();
                readStream = readStream(con.getInputStream());
                // Give output for the command line
                System.out.println(readStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return readStream;
        }

        @Override
        protected void onPostExecute(String result) {
            getCurrentLocation();

            if (result == null) {
                Log.e("result is null ", " result is null");
            } else {
                Log.d(" url string", result);
                jsonParserr(result);
                adapter = new RVAdapter(towList);
                rv.setAdapter(adapter);

                if (adapter != null) {
                    adapter.SetOnItemCLickListener(new RVAdapter.MyItemClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            Log.e(" longNn " + Float.parseFloat(towList.get(position).get(TAG_LNG)), " latn " + Float.parseFloat(towList.get(position).get(TAG_LAT)));
                            Switchfragment(R.id.mylayout, mapFragment, Float.parseFloat(towList.get(position).get(TAG_LNG)),
                                    Float.parseFloat(towList.get(position).get(TAG_LAT)), towList.get(position).get(TAG_NAME),
                                    towList.get(position).get(TAG_CONTRACTOR), latitudeN, longitudeN, address);

                        }
                    });

                }

            }

        }
    }


}
