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


public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<HomeItem> mHomeList = new ArrayList<HomeItem>();

    @NonNull
    @NotNull
    @Override
    public HomeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mHomeList.get(position));
    }

    public void setHomeList(Context context, ArrayList<HomeItem> list){
        this.context = context;
        this.mHomeList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHomeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView titleTv;
        TextView contextTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            contextTv = (TextView) itemView.findViewById(R.id.contextTv);
        }

        void onBind(HomeItem item){
            Glide.with(context).load(item.getProfileImage()).into(profileImage);
            titleTv.setText(item.getTitle());
            contextTv.setText(item.getContext());
        }
    }

}


