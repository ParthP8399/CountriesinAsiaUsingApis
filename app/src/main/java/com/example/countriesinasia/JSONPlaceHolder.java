package com.example.countriesinasia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolder {


//    @GET("rest/v2/region/asia")
//    Call<List<Country>> getPosts();

    @GET("rest/v2/region/asia")
    Call<List<Example>> getPosts();



}
