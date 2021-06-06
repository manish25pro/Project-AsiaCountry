package com.firestudio.projectasia.network;

import com.firestudio.projectasia.models.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

 @GET("rest/v2/region/asia")
    Call<List<Example>>getAsiaCountary();

 }


