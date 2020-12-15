package com.developer.crypto.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.developer.crypto.R;
import com.developer.crypto.connect.models.AssetObject;
import com.developer.crypto.databinding.ItemAssetsBinding;
import com.developer.crypto.utils.Constants;
import com.developer.crypto.utils.Utils;

import java.util.List;

public class AssetsAdapter extends RecyclerView.Adapter<AssetsAdapter.Holder> {

    private  AssetListener mListener;
    private  Context mContext;
    private  List<AssetObject> mList;

    public AssetsAdapter(List<AssetObject> assets, Context context,AssetListener listener) {
        this.mList = assets;
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ItemAssetsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        AssetObject asset = mList.get(position);
        holder.binding.textViewName.setText(asset.getName());
        holder.binding.textViewOrder.setText(String.valueOf(position+1));
        holder.binding.textViewPrice.setText("$"+Utils.formatBalance(asset.getMetrics().getMarket_data().getPrice_usd()));
        holder.binding.textViewStatus.setText(Utils.formatBalance(asset.getMetrics().getMarket_data().getPercent_change_usd_last_24_hours()) + "%");
        holder.binding.textViewBalanceUnit.setText(Utils.formatUnit(asset.getMetrics().getMarketcap().getCurrent_marketcap_usd()));
        holder.binding.textViewAllAmount.setText(Utils.roundUnit(asset.getMetrics().getMarketcap().getCurrent_marketcap_usd()));
        Glide.with(mContext).load(Constants.ASSET_IMAGES+asset.getId()+"/128.png").into(holder.binding.imageView);

        if (asset.getMetrics().getMarket_data().getPercent_change_usd_last_24_hours() > 0) {
            holder.binding.textViewStatus.setTextColor(ContextCompat.getColor(mContext, R.color.profit_color));
        }else {
            holder.binding.textViewStatus.setTextColor(ContextCompat.getColor(mContext, R.color.red));
        }

        holder.binding.linearLayout.setOnClickListener(v -> mListener.onAssesClicked(asset));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface AssetListener{
        void onAssesClicked(AssetObject asset);
    }

    static class Holder extends RecyclerView.ViewHolder{
        ItemAssetsBinding binding;
        public Holder(ItemAssetsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
