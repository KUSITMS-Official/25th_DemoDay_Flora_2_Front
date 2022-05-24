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


public class ReservationRecyclerAdapter extends RecyclerView.Adapter<ReservationRecyclerAdapter.ViewHolder> {

    private ArrayList<ReservationItem> mReservationList;

    @NonNull
    @NotNull
    @Override
    public ReservationRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ReservationRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mReservationList.get(position));
    }

    public void setReservationListList(ArrayList<ReservationItem> list){
        this.mReservationList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mReservationList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView temp_item1;
        TextView temp_item_tv1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            temp_item1 = (ImageView) itemView.findViewById(R.id.timeImg);
            temp_item_tv1 = (TextView) itemView.findViewById(R.id.timeTv);
        }

        void onBind(ReservationItem item){
            temp_item1.setImageResource(item.getResourceFlowerId());
            temp_item_tv1.setText(item.getTitle());
        }
    }

}
