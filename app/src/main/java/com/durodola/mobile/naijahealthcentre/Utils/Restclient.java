package com.durodola.mobile.naijahealthcentre.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mobile on 2016-04-21.
 */
public class Restclient {
    private Retrofit retrofit;
    private ContactServiceApi contactServiceApi;

    public Restclient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        contactServiceApi = retrofit.create(ContactServiceApi.class);

    }
    public ContactServiceApi getContact() {
        return contactServiceApi;
    }
}
