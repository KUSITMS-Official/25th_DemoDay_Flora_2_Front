package com.example.flora;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class HomeRecyclerAdapter2 extends RecyclerView.Adapter<HomeRecyclerAdapter2.ViewHolder> {

    private ArrayList<HomeItem2> mHomeList2;

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

    public void setHomeList2(ArrayList<HomeItem2> list){
        this.mHomeList2 = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHomeList2.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView temp_item1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            temp_item1 = (ImageView) itemView.findViewById(R.id.temp_item1);
        }

        void onBind(HomeItem2 item){
            temp_item1.setImageResource(item.getResourceFlowerId());
        }
    }

}
