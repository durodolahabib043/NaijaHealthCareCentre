package com.durodola.mobile.naijahealthcentre;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class HealthCareFragment extends AbstractHealthFragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ListView listview;
    EditText searchview;

    String urlreal = "https://api.myjson.com/bins/41u0g";


    ArrayList<HashMap<String, String>> towList;
    Fragment mapFragment = new MapFragment();
    HCBaseAdapter hcBaseAdapter;

    private static final String TAG_LGA = "lga";
    private static final String TAG_TOWN = "town";
    private static final String TAG_NAME = "name";
    private static final String TAG_CONTRACTOR = "contractor";
    private static final String TAG_LNG = "lng";
    private static final String TAG_LAT = "lat";


    public HealthCareFragment() {
        // Required empty public constructor
    }

    public static HealthCareFragment newInstance() {
        return new HealthCareFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.health_centre_fragment, container, false);
        // initializeData();

        towList = new ArrayList<HashMap<String, String>>();
        searchview = (EditText) view.findViewById(R.id.edittextid);

        listview = (ListView) view.findViewById(R.id.health_centre_list);



        /*rv = (RecyclerView) view.findViewById(R.id.rv);
        // not sure
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

*/

        new DownloadTask().execute();
        //testbtn.setOnClickListener(this);
        listview.setOnItemClickListener(this);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String text = searchview.getText().toString().toLowerCase(Locale.getDefault());
                hcBaseAdapter.filter(text);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    // mathod to parse json
    private void jsonParserr(String in) {

        JSONObject reader = null;
        String lga, town, name, contractor, lng, lat;


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

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Switchfragment(R.id.mylayout, mapFragment, Float.parseFloat(towList.get(position).get(TAG_LNG)),
                Float.parseFloat(towList.get(position).get(TAG_LAT)), towList.get(position).get(TAG_NAME),
                towList.get(position).get(TAG_CONTRACTOR));


    }


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
            // call the adapter from here
            if (result == null) {
                Log.e("result is null ", " result is null");
            } else {
                Log.d(" url string", result);
                jsonParserr(result);

                hcBaseAdapter = new HCBaseAdapter(getContext(), towList);
                listview.setAdapter(hcBaseAdapter);


            }

        }
    }


}
