package com.example.flora;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flora.adapters.ChatAdapter;
import com.example.flora.databinding.ActivityChatBinding;
import com.example.flora.models.ChatMessage;
import com.example.flora.models.Shop;
import com.example.flora.utilities.Constants;
import com.example.flora.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.BootpayAnalytics;
import kr.co.bootpay.enums.Method;
import kr.co.bootpay.enums.PG;
import kr.co.bootpay.enums.UX;
import kr.co.bootpay.listener.CancelListener;
import kr.co.bootpay.listener.CloseListener;
import kr.co.bootpay.listener.ConfirmListener;
import kr.co.bootpay.listener.DoneListener;
import kr.co.bootpay.listener.ReadyListener;
import kr.co.bootpay.model.BootExtra;
import kr.co.bootpay.model.BootUser;


public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private Shop receiverShop;
    private List<ChatMessage> chatMessages;
    private ChatAdapter chatAdapter;
    private PreferenceManager preferenceManager;
    private FirebaseFirestore database;
    Button payButton;
    private int stuck = 10;

    ImageView imageBack;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListeners();
        loadReceiverDetails();
        init();

        listenMessages();

        // ???????????? - ?????? ????????????(???????????????)??? application id ?????? ???????????????. ????????? ????????? ?????? ??? ???????????????.
        // ????????? ???????????? ?????? ??? ??? ??????????????? ????????????. ?????? application id ?????? ?????? ??????!!!
        BootpayAnalytics.init(this, "626f85be2701800023f6a9f8");

        payButton = findViewById(R.id.payApi);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payApi();
            }
        });

        imageBack = (ImageView) findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init() {
        preferenceManager = new PreferenceManager(getApplicationContext());
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(
                chatMessages,
                preferenceManager.getString(Constants.KEY_ID)
        );
        binding.chatRecyclerView.setAdapter(chatAdapter);
        database = FirebaseFirestore.getInstance();
    }

    private void sendMessage() {
        HashMap<String, Object> message = new HashMap<>();
        message.put(Constants.KEY_SENDER_ID, "juyeon");
        message.put(Constants.KEY_RECEIVED_ID, "Shop");
        message.put(Constants.KEY_MESSAGE, binding.inputMessage.getText().toString());
        message.put(Constants.KEY_TIMESTAMP, new Date());
        database.collection(Constants.KEY_COLLECTION_CHAT).add(message);
        binding.inputMessage.setText(null);
    }

    private void shoToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void listenMessages() {
        database.collection(Constants.KEY_COLLECTION_CHAT)
                .whereEqualTo(Constants.KEY_SENDER_ID, "juyeon")
                .whereEqualTo(Constants.KEY_RECEIVED_ID, "Shop")
                .addSnapshotListener(eventListener);

        database.collection(Constants.KEY_COLLECTION_CHAT)
                .whereEqualTo(Constants.KEY_SENDER_ID, "Shop")
                .whereEqualTo(Constants.KEY_RECEIVED_ID, "juyeon")
                .addSnapshotListener(eventListener);
    }

    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
        if (error != null) {
            return;
        }
        if (value != null) {
            int count = chatMessages.size();
            for (DocumentChange documentChange : value.getDocumentChanges()) {
                if (documentChange.getType() == DocumentChange.Type.ADDED) {
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.senderId = documentChange.getDocument().getString(Constants.KEY_SENDER_ID);
                    chatMessage.receiverId = documentChange.getDocument().getString(Constants.KEY_RECEIVED_ID);
                    chatMessage.message = documentChange.getDocument().getString(Constants.KEY_MESSAGE);
                    chatMessage.dateTime = getReadableDateTime(documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP));
                    chatMessage.dateObject = documentChange.getDocument().getDate(Constants.KEY_TIMESTAMP);
                    chatMessages.add(chatMessage);
                }
            }

            Collections.sort(chatMessages, (obj1, obj2) -> obj1.dateObject.compareTo(obj2.dateObject));
            if (count == 0) {
                chatAdapter.notifyDataSetChanged();
            } else {
                chatAdapter.notifyItemRangeInserted(chatMessages.size(), chatMessages.size());
                binding.chatRecyclerView.smoothScrollToPosition(chatMessages.size() - 1);
            }
            binding.chatRecyclerView.setVisibility(View.VISIBLE);
        }
        binding.progressBar.setVisibility(View.GONE);
    };

    private void loadReceiverDetails() {
        receiverShop = (Shop) getIntent().getSerializableExtra(Constants.KEY_FLOEWRSHOP);
        binding.textName.setText("????????? ??????");
    }

    private void setListeners() {
//        binding.imageBack.setOnClickListener(v -> onBackPressed());
        binding.LayoutSend.setOnClickListener(v -> sendMessage());
    }

    private String getReadableDateTime(Date date) {
        return new SimpleDateFormat("a hh:mm", Locale.getDefault()).format(date);
    }

    private void payApi() {
        BootUser bootUser = new BootUser().setPhone("010-0000-0000");
        BootExtra bootExtra = new BootExtra().setQuotas(new int[]{0, 2, 3});

        Bootpay.init(getFragmentManager())
                .setApplicationId("626f85be2701800023f6a9f8") // ?????? ????????????(???????????????)??? application id ???(?????? ??? ??????)
                .setPG(PG.INICIS) // ????????? PG ???
                .setMethod(Method.CARD) // ????????????
                .setContext(ChatActivity.this)
                .setBootUser(bootUser)
                .setBootExtra(bootExtra)
                .setUX(UX.PG_DIALOG)
                .setName("???????????? ?????? ?????????") // ????????? ?????????
                .setOrderId("1234") // ?????? ???????????? (expire_month)
                .setPrice(10000) // ????????? ??????
                .addItem("???????????? ?????? ?????????", 1, "ITEM_CODE_FLOWER", 49600) // ??????????????? ?????? ????????????, ????????? ?????? ??????
                .onConfirm(new ConfirmListener() { // ????????? ???????????? ?????? ?????? ???????????? ?????????, ?????? ???????????? ?????? ????????? ??????
                    @Override
                    public void onConfirm(@Nullable String message) {

                        if (0 < stuck) Bootpay.confirm(message); // ????????? ?????? ??????.
                        else Bootpay.removePaymentWindow(); // ????????? ?????? ????????? ???????????? ?????? ?????? ??????
                        Log.d("confirm", message);
                    }
                })
                .onDone(new DoneListener() { // ??????????????? ??????, ????????? ?????? ??? ????????? ????????? ????????? ???????????????
                    @Override
                    public void onDone(@Nullable String message) {
                        Log.d("done", message);
                    }
                })
                .onReady(new ReadyListener() { // ???????????? ?????? ??????????????? ???????????? ???????????? ???????????????.
                    @Override
                    public void onReady(@Nullable String message) {
                        Log.d("ready", message);
                    }
                })
                .onCancel(new CancelListener() { // ?????? ????????? ??????
                    @Override
                    public void onCancel(@Nullable String message) {

                        Log.d("cancel", message);
                    }
                })
                .onClose(
                        new CloseListener() { //???????????? ????????? ???????????? ??????
                            @Override
                            public void onClose(String message) {
                                Log.d("close", "close");
                            }
                        })
                .request();
    }



}
