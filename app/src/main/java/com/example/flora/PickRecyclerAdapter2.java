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


public class PickRecyclerAdapter2 extends RecyclerView.Adapter<PickRecyclerAdapter2.ViewHolder> {

    private Context context;
    private ArrayList<PickItem2> mPickList2 = new ArrayList<PickItem2>();

    @NonNull
    @NotNull
    @Override
    public PickRecyclerAdapter2.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PickRecyclerAdapter2.ViewHolder holder, int position) {
        holder.onBind(mPickList2.get(position));
    }

    public void setPickList2(Context context, ArrayList<PickItem2> list){
        this.context = context;
        this.mPickList2 = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPickList2.size();
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

        void onBind(PickItem2 item){
            Glide.with(context).load(item.getProfileImage()).into(profileImage);
            titleTv.setText(item.getTitle());
            contextTv.setText(item.getContext());
        }
    }

}


