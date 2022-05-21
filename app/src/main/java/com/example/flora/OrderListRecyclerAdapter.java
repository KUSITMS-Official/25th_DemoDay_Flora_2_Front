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


public class OrderListRecyclerAdapter extends RecyclerView.Adapter<OrderListRecyclerAdapter.ViewHolder> {

    private ArrayList<OrderListItem> mOrderList;

    @NonNull
    @NotNull
    @Override
    public OrderListRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderListRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mOrderList.get(position));
    }

    public void setOrderList(ArrayList<OrderListItem> list){
        this.mOrderList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView dateTv;
        ImageView flowerImage;
        TextView titleTv;
        TextView contextTv;
        TextView priceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            dateTv = (TextView) itemView.findViewById(R.id.date);
            flowerImage = (ImageView) itemView.findViewById(R.id.flowerImage);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            contextTv = (TextView) itemView.findViewById(R.id.contextTv);
            priceTv = (TextView) itemView.findViewById(R.id.priceTv);
        }

        void onBind(OrderListItem item){

            dateTv.setText(item.getDate());
            flowerImage.setImageResource(item.getResourceFlowerId());
            titleTv.setText(item.getTitle());
            contextTv.setText(item.getContext());
            priceTv.setText(item.getPrice());
        }
    }

}
