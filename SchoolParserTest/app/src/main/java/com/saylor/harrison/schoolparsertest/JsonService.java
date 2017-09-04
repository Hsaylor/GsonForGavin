package com.saylor.harrison.schoolparsertest;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by harrisonsaylor on 9/3/17.
 */

public interface JsonService {
  @GET("Assignment1-1.json") Call<JsonObject> getJson1();
}
