package com.example.retrofitassignment.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.databinding.ActivityMainBinding;
import com.example.retrofitassignment.model.RealEstateModel;
import com.example.retrofitassignment.viewmodel.RealEstateViewModel;


public class MainActivity extends AppCompatActivity
  implements RealEstateAdapter.mainActivityCallbacks {
  
  private ActivityMainBinding mActivityMainBinding;
  private RealEstateAdapter mRealEstateAdapter;
  private RealEstateViewModel mRealEstateViewModel;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    
    initData();
  }
  
  private void initData() {
    mRealEstateAdapter = new RealEstateAdapter();
    mRealEstateAdapter.setCallbacks(this);
    
    mRealEstateViewModel = new RealEstateViewModel(this);
    mActivityMainBinding.setMainModel(mRealEstateViewModel);
    mActivityMainBinding.executePendingBindings();
    
    mActivityMainBinding.rvListItem.setAdapter(mRealEstateAdapter);
    
    mRealEstateViewModel.getMutableListRealEstateModel().observe(
      this,
      realEstateModels -> mRealEstateAdapter.setData(realEstateModels)
    );
  }
  
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.item_type_rent:
        mRealEstateViewModel.showPropertyByType("rent");
        break;
      
      case R.id.item_type_buy:
        mRealEstateViewModel.showPropertyByType("buy");
        break;
      
      case R.id.item_type_all:
        mRealEstateViewModel.showProperty();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.type_menu, menu);
    return true;
  }
  
  @Override
  public void onClickMainActivity(RealEstateModel realEstateModel) {
    Intent intent = new Intent(this, DetailActivity.class);
    intent.putExtra("RealEstateModel", realEstateModel);
    startActivity(intent);
  }
}