package com.example.retrofitassignment.view;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.databinding.ItemLayoutBinding;
import com.example.retrofitassignment.model.RealEstateModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RealEstateAdapter extends RecyclerView.Adapter<RealEstateAdapter.RealEstateModelHolder> {
  
  private mainActivityCallbacks callbacks;
  
  private LayoutInflater layoutInflater;
  
  private List<RealEstateModel> mListRealEstate;
  
  public RealEstateAdapter() {
  }
  
  public void setCallbacks(mainActivityCallbacks callbacks) {
    this.callbacks = callbacks;
  }
  
  @SuppressLint("NotifyDataSetChanged")
  public void setData(List<RealEstateModel> mListRealEstate) {
    this.mListRealEstate = mListRealEstate;
    notifyDataSetChanged();
  }
  
  @NonNull
  @Override
  public RealEstateModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (layoutInflater == null) {
      layoutInflater = LayoutInflater.from(parent.getContext());
    }
    ItemLayoutBinding mBinding = DataBindingUtil.inflate(
      layoutInflater,
      R.layout.item_layout,
      parent,
      false);
    return new RealEstateModelHolder(mBinding);
  }
  
  @Override
  public void onBindViewHolder(@NonNull RealEstateModelHolder holder, int position) {
    Log.d("Bug", "ăn vào Holder");
    RealEstateModel realEstateModel = mListRealEstate.get(position);
    Uri url = Uri.parse(realEstateModel.getImage()).buildUpon().scheme("https").build();
    realEstateModel.setImage(url.toString());
    
    Picasso.with(holder.itemView.getContext()).load(url).into(holder.mBinding.ivPropertyImage);
    
    holder.itemView.setOnClickListener(v ->
      callbacks.onClickMainActivity(mListRealEstate.get(position)));
    
    holder.bind(realEstateModel);
  }
  
  @Override
  public int getItemCount() {
    if (mListRealEstate == null) {
      return 0;
    }
    return mListRealEstate.size();
  }
  
  public interface mainActivityCallbacks {
    void onClickMainActivity(RealEstateModel realEstateModel);
  }
  
  
  public class RealEstateModelHolder extends RecyclerView.ViewHolder {
    ItemLayoutBinding mBinding;
    
    
    public RealEstateModelHolder(@NonNull ItemLayoutBinding binding) {
      super(binding.getRoot());
      this.mBinding = binding;
    }
    
    private void bind(RealEstateModel realEstateModel) {
      mBinding.setRealEstateModel(realEstateModel);
      mBinding.executePendingBindings();
    }
  }
}
