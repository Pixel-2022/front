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

public class Fragment_WordCard extends Fragment {
    private View v;
    WordCardAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    String[] titles = {"하나","둘","셋"};
    int[] images = {R.drawable.test1,R.drawable.test1,R.drawable.test1 };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        v= inflater.inflate(R.layout.f2_wordcard,container,false);
        RecyclerView recyclerView = v.findViewById(R.id.cardList);
        Context context = v.getContext();

        mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);

        adapter = new WordCardAdapter(titles);

        recyclerView.setAdapter(adapter);

        return v;
    }
}
