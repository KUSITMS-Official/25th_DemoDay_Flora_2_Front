package com.example.flora;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public RecyclerView mRecyclerView;
    public HomeRecyclerAdapter mRecyclerAdapter;
    public HomeRecyclerAdapter2 mRecyclerAdapter2;
    public HomeRecyclerAdapter3 mRecyclerAdapter3;
    public ArrayList<HomeItem> mHomeItems1;
    public ArrayList<HomeItem2> mHomeItems2;
    public ArrayList<HomeItem3> mHomeItems3;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView mRecyclerView1 = rootView.findViewById(R.id.recycler1);
        RecyclerView mRecyclerView2 = rootView.findViewById(R.id.recycler2);
        RecyclerView mRecyclerView3 = rootView.findViewById(R.id.recycler3);


        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));

        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));

        mRecyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));

        /* initiate adapter */
        mRecyclerAdapter = new HomeRecyclerAdapter();
        mRecyclerAdapter2 = new HomeRecyclerAdapter2();
        mRecyclerAdapter3 = new HomeRecyclerAdapter3();

        /* initiate recyclerview */
        mRecyclerView1.setAdapter(mRecyclerAdapter);
        mRecyclerView2.setAdapter(mRecyclerAdapter2);
        mRecyclerView3.setAdapter(mRecyclerAdapter3);

        /* adapt data */
        mHomeItems1 = new ArrayList<>();
        mHomeItems1.add(new HomeItem(R.drawable.temp_rec_img,"프리지어"));
        mHomeItems1.add(new HomeItem(R.drawable.temp_rec_img,"Forever"));
        mHomeItems1.add(new HomeItem(R.drawable.temp_rec_img,"Yucca"));
        mHomeItems1.add(new HomeItem(R.drawable.temp_rec_img,"프리지어"));
        mHomeItems1.add(new HomeItem(R.drawable.temp_rec_img,"Forever"));
        mHomeItems1.add(new HomeItem(R.drawable.temp_rec_img,"Yucca"));
        mRecyclerAdapter.setHomeList(mHomeItems1);

        mHomeItems2 = new ArrayList<>();
        mHomeItems2.add(new HomeItem2(R.drawable.temp_rec_img));
        mHomeItems2.add(new HomeItem2(R.drawable.temp_rec_img));
        mHomeItems2.add(new HomeItem2(R.drawable.temp_rec_img));
        mHomeItems2.add(new HomeItem2(R.drawable.temp_rec_img));
        mHomeItems2.add(new HomeItem2(R.drawable.temp_rec_img));
        mHomeItems2.add(new HomeItem2(R.drawable.temp_rec_img));
        mRecyclerAdapter2.setHomeList2(mHomeItems2);

        mHomeItems3 = new ArrayList<>();
        mHomeItems3.add(new HomeItem3(R.drawable.temp_rec_img));
        mHomeItems3.add(new HomeItem3(R.drawable.temp_rec_img));
        mHomeItems3.add(new HomeItem3(R.drawable.temp_rec_img));
        mHomeItems3.add(new HomeItem3(R.drawable.temp_rec_img));
        mHomeItems3.add(new HomeItem3(R.drawable.temp_rec_img));
        mHomeItems3.add(new HomeItem3(R.drawable.temp_rec_img));
        mRecyclerAdapter3.setHomeList3(mHomeItems3);

        return rootView;
    }
}