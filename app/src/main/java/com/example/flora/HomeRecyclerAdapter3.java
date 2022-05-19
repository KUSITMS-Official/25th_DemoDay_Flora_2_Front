package com.example.flora;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class HomeRecyclerAdapter3 extends RecyclerView.Adapter<HomeRecyclerAdapter3.ViewHolder> {

    private ArrayList<HomeItem3> mHomeList3;

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

    public void setHomeList3(ArrayList<HomeItem3> list){
        this.mHomeList3 = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHomeList3.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView temp_item1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            temp_item1 = (ImageView) itemView.findViewById(R.id.temp_item1);
        }

        void onBind(HomeItem3 item){
            temp_item1.setImageResource(item.getResourceFlowerId());
        }
    }

}
