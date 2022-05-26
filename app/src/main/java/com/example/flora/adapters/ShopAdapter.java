package com.example.flora.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flora.databinding.ItemContainerUserBinding;
import com.example.flora.listeners.ShopListener;
import com.example.flora.models.Shop;

import java.io.UnsupportedEncodingException;
import  android.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    private final List<Shop> shops;
    private final ShopListener shopListener;

    public ShopAdapter(List<Shop> shops, ShopListener shopListener) {
        this.shops = shops;
        this.shopListener = shopListener;
    }


    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerUserBinding itemContainerUserBinding = ItemContainerUserBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ShopViewHolder(itemContainerUserBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        holder.setShopData(shops.get(position));

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {
        ItemContainerUserBinding binding;

        ShopViewHolder(ItemContainerUserBinding itemContainerUserBinding) {
            super(itemContainerUserBinding.getRoot());
            binding = itemContainerUserBinding;
        }

        void setShopData(Shop shop) {
            binding.textName.setText(shop.name);
            //binding.textLastMsg.setText(shop.last_message);
        //    binding.flowerShopimage.setImageBitmap(getShopImage(shop.image));
            binding.getRoot().setOnClickListener(v -> shopListener.onUserClicked(shop));
        }
    }


    //private Bitmap getShopImage(String encodedImage) {
    //    byte[] bytes = Base64.decode(encodedImage, Base64.NO_WRAP);
    //    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    //}


    //리스트 클릭 시 이벤트
    /*
    public interface OnShopClickListener {
        void OnShopClick(View _view);
    }

    private OnShopClickListener onShopClickListener;

    public void setOnShopClickListener(OnShopClickListener _listener) {
        onShopClickListener = _listener;
    }
    */
}