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

import com.bumptech.glide.Glide;


public class HomeRecyclerAdapter2 extends RecyclerView.Adapter<HomeRecyclerAdapter2.ViewHolder> {

    private Context context;
    private ArrayList<HomeItem2> mHomeList2 = new ArrayList<HomeItem2>();

    @NonNull
    @NotNull
    @Override
    public HomeRecyclerAdapter2.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item2, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeRecyclerAdapter2.ViewHolder holder, int position) {
        holder.onBind(mHomeList2.get(position));
    }

    public void setHomeList2(Context context, ArrayList<HomeItem2> list){
        this.context = context;
        this.mHomeList2 = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHomeList2.size();
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

        void onBind(HomeItem2 item){
            Glide.with(context).load(item.getPortfolioImage()).into(flowerImage);
            Glide.with(context).load(item.getFlowerShopImage()).into(profileImage);
            titleTv.setText(item.getTitle());
            contextTv.setText(item.getContext());
            priceTv.setText(item.getPrice());
            discountTv.setText(item.getDiscount());
        }
    }

}

