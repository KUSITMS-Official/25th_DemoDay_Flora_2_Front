package com.example.flora.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flora.adapters.ShopAdapter;
import com.example.flora.databinding.ActivityMainBinding;
import com.example.flora.databinding.ActivityReservationBinding;
import com.example.flora.databinding.ActivityShopsBinding;
import com.example.flora.listeners.ShopListener;
import com.example.flora.models.Shop;
import com.example.flora.utilities.Constants;
import com.example.flora.utilities.PreferenceManager;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShopsActivity extends AppCompatActivity implements ShopListener {

    //binding
    //binding
    private ActivityShopsBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();
        getShops();
    }


    private void getShops() {
        //loading(true);
        FirebaseFirestore databse = FirebaseFirestore.getInstance();
        databse.collection(Constants.KEY_COLLECTION_SHOPS)
                .get()
                .addOnCompleteListener(task -> {
                    //loading(false);
                    //String currentShopId = preferenceManager.getString(Constants.KEY_ID);
                    if (task.isSuccessful() && task.getResult() != null) {
                        List<Shop> shops = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            //if (currentShopId.equals(queryDocumentSnapshot.getId())) {
                            //    continue;
                            //}
                            Shop shop = new Shop();
                            shop.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
                            //shop.image = queryDocumentSnapshot.getString(Constants.KEY_IMG);
                            shop.id = queryDocumentSnapshot.getId();

                            Log.d("FireBaseData", "getData: " + Constants.KEY_NAME);
                            shops.add(shop);
                        }
                        if (shops.size() > 0) {
                            ShopAdapter shopAdapter = new ShopAdapter(shops, this);
                            binding.shopsRecyclerView.setAdapter(shopAdapter);
                            binding.shopsRecyclerView.setVisibility(View.VISIBLE);

                        } else {
                            //showErrorMessage();
                        }
                    } else {
                        //showErrorMessage();
                    }
                });
    }

    @Override
    public void onUserClicked(Shop shop) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra(Constants.KEY_FLOEWRSHOP, shop);
        startActivity(intent);
        finish();
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> onBackPressed());
    }
}