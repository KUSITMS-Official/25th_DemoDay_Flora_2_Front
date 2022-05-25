package com.example.flora;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public RecyclerView mRecyclerView;
    public FeedRecyclerAdapter mRecyclerAdapter;
    public ArrayList<FeedItem> mFeedItems;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance(String param1, String param2) {
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = (ViewGroup) inflater.inflate(R.layout.fragment_feed, container, false);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler);


//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        /* initiate adapter */
        mRecyclerAdapter = new FeedRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);

        /* adapt data */
        mFeedItems = new ArrayList<>();
        mFeedItems.add(new FeedItem(R.drawable.flower_temp1, R.drawable.profile_temp, "꽃빛나다", "프리지아 하늘색 꽃다발", "12%", "65,000원"));
        mFeedItems.add(new FeedItem(R.drawable.flower_temp2, R.drawable.profile_temp, "꽃빛나다", "프리지아 하늘색 꽃다발", "12%", "65,000원"));
        mFeedItems.add(new FeedItem(R.drawable.flower_temp3, R.drawable.profile_temp, "꽃빛나다", "프리지아 하늘색 꽃다발", "12%", "65,000원"));
        mFeedItems.add(new FeedItem(R.drawable.flower_temp4, R.drawable.profile_temp, "꽃빛나다", "프리지아 하늘색 꽃다발", "12%", "65,000원"));
        mFeedItems.add(new FeedItem(R.drawable.flower_temp1, R.drawable.profile_temp, "꽃빛나다", "프리지아 하늘색 꽃다발", "12%", "65,000원"));
        mFeedItems.add(new FeedItem(R.drawable.flower_temp2, R.drawable.profile_temp, "꽃빛나다", "프리지아 하늘색 꽃다발", "12%", "65,000원"));
        mFeedItems.add(new FeedItem(R.drawable.flower_temp3, R.drawable.profile_temp, "꽃빛나다", "프리지아 하늘색 꽃다발", "12%", "65,000원"));
        mFeedItems.add(new FeedItem(R.drawable.flower_temp4, R.drawable.profile_temp, "꽃빛나다", "프리지아 하늘색 꽃다발", "12%", "65,000원"));
        mRecyclerAdapter.setFeedList(mFeedItems);

        return rootView;
    }

}