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


public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {

    private ArrayList<HomeItem> mHomeList;

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

    public void setHomeList(ArrayList<HomeItem> list){
        this.mHomeList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHomeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView temp_item1;
        TextView temp_item_tv1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            temp_item1 = (ImageView) itemView.findViewById(R.id.temp_item1);
            temp_item_tv1 = (TextView) itemView.findViewById(R.id.temp_item_tv1);
        }

        void onBind(HomeItem item){
            temp_item1.setImageResource(item.getResourceFlowerId());
            temp_item_tv1.setText(item.getTitle());
        }
    }

}
