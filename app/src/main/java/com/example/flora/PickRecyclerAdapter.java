package com.example.flora;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class PickRecyclerAdapter extends RecyclerView.Adapter<PickRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<PickItem> mPickList = new ArrayList<PickItem>();

    @NonNull
    @NotNull
    @Override
    public PickRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item2, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PickRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mPickList.get(position));
    }

    public void setPickList(Context context, ArrayList<PickItem> list){
        this.context = context;
        this.mPickList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPickList.size();
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

        void onBind(PickItem item){
            Glide.with(context).load(item.getPortfolioImage()).into(flowerImage);
            Glide.with(context).load(item.getFlowerShopImage()).into(profileImage);
            titleTv.setText(item.getTitle());
            contextTv.setText(item.getContext());
            priceTv.setText(item.getPrice());
            discountTv.setText(item.getDiscount());
        }
    }

}

