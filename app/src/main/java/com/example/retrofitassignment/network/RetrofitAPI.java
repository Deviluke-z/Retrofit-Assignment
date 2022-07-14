package com.example.retrofitassignment.network;

import static com.example.retrofitassignment.network.RetrofitAPIService.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitAPI {
  private static RetrofitAPI instance;
  public RetrofitAPIService retrofitApiService;
  
  public RetrofitAPI() {
    Retrofit retrofitAPI =
      new Retrofit.Builder().
        addConverterFactory(MoshiConverterFactory.create()).
        baseUrl(BASE_URL).build();
    retrofitApiService = retrofitAPI.create(RetrofitAPIService.class);
  }
  
  public static RetrofitAPI getInstance() {
    synchronized (RetrofitAPI.class) {
      if (instance == null) {
        instance = new RetrofitAPI();
      }
      return instance;
    }
  }
}
