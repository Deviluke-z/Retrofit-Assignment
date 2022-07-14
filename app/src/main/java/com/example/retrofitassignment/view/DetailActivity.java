package com.example.retrofitassignment.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.databinding.DetailActivityBinding;
import com.example.retrofitassignment.model.RealEstateModel;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

  private DetailActivityBinding mDetailActivityBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mDetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.detail_activity);

    initData();
  }

  @SuppressLint("SetTextI18n")
  private void initData() {
    Intent intent = getIntent();
  
    RealEstateModel realEstateModel =
      (RealEstateModel) intent.getSerializableExtra("RealEstateModel");

    mDetailActivityBinding.tvPropertyType.setText(
      "FOR " + realEstateModel.getType().toUpperCase(Locale.ROOT));
    mDetailActivityBinding.tvPropertyPrice.setText("$ " + realEstateModel.getPrice());
    mDetailActivityBinding.tvPropertyId.setText("ID: " + realEstateModel.getId());

    Uri imgSrc = Uri.parse(realEstateModel.getImage())
        .buildUpon()
        .scheme("https")
        .build();

    Picasso.with(this).load(imgSrc).into(mDetailActivityBinding.ivPropertyImage);
  }

}