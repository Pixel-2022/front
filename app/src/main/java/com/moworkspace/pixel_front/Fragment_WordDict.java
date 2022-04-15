package com.moworkspace.pixel_front;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Dictionary;

public class Fragment_WordDict extends Fragment {
    private View v;
    private RecyclerView recyclerView;
    private LinearLayoutManager LinearLayoutManager;
    ArrayList<Dict> dataList=new ArrayList();
    String[] names = {"신나","쓰레빠","타타","앙마"};
    int[] images = {R.drawable.test1, R.drawable.test1,R.drawable.test1,R.drawable.test1};
    DictAdapter adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v= inflater.inflate(R.layout.f2_worddict,container,false);
        Context context = v.getContext();
        DictAdapter adapter=new DictAdapter(context, dataList);

        // 리사이클
        recyclerView = v.findViewById(R.id.recyclerView1);
        //생쇼중
        dataList.add(new Dict(names[0], images[0]));
        dataList.add(new Dict(names[1], images[1]));
        dataList.add(new Dict(names[2], images[2]));
        dataList.add(new Dict(names[3], images[3]));



        LinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(LinearLayoutManager);
        recyclerView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

}
