package com.example.flora;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class HomeRecyclerAdapter3 extends RecyclerView.Adapter<HomeRecyclerAdapter3.ViewHolder> {

    private Context context;
    private ArrayList<HomeItem3> mHomeList3 = new ArrayList<HomeItem3>();

    @NonNull
    @NotNull
    @Override
    public HomeRecyclerAdapter3.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item3, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeRecyclerAdapter3.ViewHolder holder, int position) {
        holder.onBind(mHomeList3.get(position));
    }

    public void setHomeList3(Context context, ArrayList<HomeItem3> list){
        this.context = context;
        this.mHomeList3 = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHomeList3.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView flowerImage;
        ImageView profileImage;
        TextView titleTv;
        TextView contextTv;
        TextView priceTv;
        TextView discountTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            flowerImage = (ImageView) itemView.findViewById(R.id.flowerImage);
            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            contextTv = (TextView) itemView.findViewById(R.id.contextTv);
            priceTv = (TextView) itemView.findViewById(R.id.priceTv);
            discountTv = (TextView) itemView.findViewById(R.id.discountTv);
        }

        void onBind(HomeItem3 item){
            Glide.with(context).load(item.getPortfolioImage()).into(flowerImage);
            Glide.with(context).load(item.getFlowerShopImage()).into(profileImage);
            titleTv.setText(item.getTitle());
            contextTv.setText(item.getContext());
            priceTv.setText(item.getPrice());
            discountTv.setText(item.getDiscount());
        }

    }

}

