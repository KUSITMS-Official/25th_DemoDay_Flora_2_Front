package com.example.flora;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flora.response.FlowerShopListResponse;
import com.example.flora.response.FlowerShopResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FeedItem> mFeedList = new ArrayList<FeedItem>();
    private Intent intent;
    private String token;

    @NonNull
    @NotNull
    @Override
    public FeedRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_recycler_item, parent, false);

        return new ViewHolder(view);

    }

    public void setFeedList(String token, Context context, ArrayList<FeedItem> list){
        this.token = token;
        this.context = context;
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
        TextView priceTv;
        TextView discountTv;
        ImageView clipImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            flowerImage = (ImageView) itemView.findViewById(R.id.flowerImage);
            profileImage = (ImageView) itemView.findViewById(R.id.profileImage);
            titleTv = (TextView) itemView.findViewById(R.id.titleTv);
            contextTv = (TextView) itemView.findViewById(R.id.contextTv);
            priceTv = (TextView) itemView.findViewById(R.id.priceTv);
            discountTv = (TextView) itemView.findViewById(R.id.discountTv);
            clipImage = (ImageView) itemView.findViewById(R.id.clip);
        }

        void onBind(FeedItem item){
            Glide.with(context).load(item.getPortfolioImage()).into(flowerImage);
            Glide.with(context).load(item.getFlowerShopImage()).into(profileImage);
            titleTv.setText(item.getTitle());
            contextTv.setText(item.getContext());
            priceTv.setText(item.getPrice());
            discountTv.setText(item.getDiscount());
            if(item.getClip()) clipImage.setImageResource(R.drawable.ic_fill_heart);
            else clipImage.setImageResource(R.drawable.ic_heart);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FeedRecyclerAdapter.ViewHolder holder, int position) {
        FeedItem feedItem = mFeedList.get(position);
        holder.onBind(feedItem);

        holder.flowerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), FeedSeeProduct.class);
                v.getContext().startActivity(intent);
            }
        });

        holder.clipImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = holder.clipImage.getDrawable();
                Drawable fillHeart = context.getResources().getDrawable(R.drawable.ic_fill_heart);
                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                Bitmap fillBitmap = ((BitmapDrawable)fillHeart).getBitmap();
                if(bitmap.equals(fillBitmap)) {
                    holder.clipImage.setImageResource(R.drawable.ic_heart);
                    Call<Void> unClip = RetrofitClient.getAPIService().unClipPortfolio(token, feedItem.portfolioId.toString());

                    unClip.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            Log.d("연결성공", response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.e("연결실패", t.getMessage());
                        }
                    });
                }
                else {
                    holder.clipImage.setImageResource(R.drawable.ic_fill_heart);
                    Call<Void> clip = RetrofitClient.getAPIService().clipPortfolio(token, feedItem.portfolioId.toString());

                    clip.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            Log.d("연결성공", response.body().toString());
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.e("연결실패", t.getMessage());
                        }
                    });
                }
            }
        });
    }

}