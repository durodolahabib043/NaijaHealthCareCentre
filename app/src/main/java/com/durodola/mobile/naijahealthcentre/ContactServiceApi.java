package com.durodola.mobile.naijahealthcentre;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mobile on 2016-04-21.
 */
public interface ContactServiceApi {
    //https://api.myjson.com/bins/41u0g
    @GET("/bins/41u0g")
    Call<HealthCare> getdetailedContact();


}
