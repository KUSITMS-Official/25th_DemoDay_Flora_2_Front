package com.example.flora;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder> {

    private ArrayList<FeedItem> mFeedList;

    @NonNull
    @NotNull
    @Override
    public FeedRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_recycler_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FeedRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mFeedList.get(position));
    }

    public void setFeedList(ArrayList<FeedItem> list){
        this.mFeedList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView flowerImage;
        ImageView profileImage;
        TextView titleTv;
        TextView contextTv;
        TextView discountTv;
        TextView priceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            flowerImage = (ImageView) itemView.findViewById(R.id.flowerImage);
            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            contextTv = (TextView) itemView.findViewById(R.id.contextTv);
            discountTv = (TextView) itemView.findViewById(R.id.discountTv);
            priceTv = (TextView) itemView.findViewById(R.id.priceTv);
        }

        void onBind(FeedItem item){
            flowerImage.setImageResource(item.getResourceFlowerId());
            profileImage.setImageResource(item.getResourceProfileId());
            titleTv.setText(item.getTitle());
            contextTv.setText(item.getContext());
            discountTv.setText(item.getDiscount());
            discountTv.setText(item.getPrice());
        }
    }

}
