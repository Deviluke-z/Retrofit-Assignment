package com.example.retrofitassignment.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitassignment.model.RealEstateModel;
import com.example.retrofitassignment.network.RetrofitAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RealEstateViewModel extends BaseObservable {
  
  private final MutableLiveData<List<RealEstateModel>> mMutableListRealEstateModel;
  
  public RealEstateViewModel(Context mContext) {
    mMutableListRealEstateModel = new MutableLiveData<>();
    showProperty();
  }
  
  public MutableLiveData<List<RealEstateModel>> getMutableListRealEstateModel() {
    return mMutableListRealEstateModel;
  }
  
  public void showProperty() {
    Log.d("Bug", "showProperty()");
    RetrofitAPI mRetrofitAPI = RetrofitAPI.getInstance();
    mRetrofitAPI.retrofitApiService.getProperties().enqueue(new Callback<List<RealEstateModel>>() {
      @Override
      public void onResponse(
        Call<List<RealEstateModel>> call,
        Response<List<RealEstateModel>> response) {
        Log.d("Bug", "onResponse()");
        mMutableListRealEstateModel.postValue(response.body());
      }
      
      @Override
      public void onFailure(Call<List<RealEstateModel>> call, Throwable t) {
        Log.d("Bug", "onFailure()");
      }
    });
  }
  
  public void showPropertyByType(String type) {
    Log.d("Bug", "showProperty()");
    RetrofitAPI mRetrofitAPI = RetrofitAPI.getInstance();
    mRetrofitAPI.retrofitApiService.getType(type).enqueue(new retrofit2.Callback<List<RealEstateModel>>() {
      @Override
      public void onResponse(
        retrofit2.Call<List<RealEstateModel>> call,
        Response<List<RealEstateModel>> response) {
        Log.d("Bug", "onResponse()");
        mMutableListRealEstateModel.postValue(response.body());
      }
      
      @Override
      public void onFailure(Call<List<RealEstateModel>> call, Throwable t) {
        Log.d("Bug", "onFailure()");
      }
    });
  }
}
