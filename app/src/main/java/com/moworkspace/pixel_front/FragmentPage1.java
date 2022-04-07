package com.moworkspace.pixel_front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

public class FragmentPage1 extends Fragment {
    private LinearLayoutManager LinearLayoutManager;
    private View v;
    private String checked;

    FragmentManager manager;
    FragmentTransaction fragmentTransaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v= inflater.inflate(R.layout.fragment1,container,false);

        RadioGroup rg = (RadioGroup) v.findViewById(R.id.frag1_radio);
        RadioButton rbhand = (RadioButton) v.findViewById(R.id.frag1_hand);
        RadioButton rbstuff = (RadioButton) v.findViewById(R.id.frag1_stuff);

        manager = getChildFragmentManager();
        fragmentTransaction = manager.beginTransaction();

        rbhand.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checked = "frag1_hand";
                fragmentTransaction = manager.beginTransaction();
                Fragment1Hand fragment1hand = new Fragment1Hand();
                fragmentTransaction.replace(R.id.content,fragment1hand);
                fragmentTransaction.commit();
            }
        });
        rbstuff.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                checked = "frag1_stuff";
                fragmentTransaction = manager.beginTransaction();
                Fragment1Stuff fragment1stuff = new Fragment1Stuff();
                fragmentTransaction.replace(R.id.content,fragment1stuff);
                fragmentTransaction.commit();
            }
        });

        checked = "frag1_hand";
        if(checked == "frag1_hand"){
            Fragment1Hand fragment1hand = new Fragment1Hand();
            fragmentTransaction.replace(R.id.content,fragment1hand);
        }else if(checked =="frag1_stuff"){
            Fragment1Stuff fragment1stuff = new Fragment1Stuff();
            fragmentTransaction.replace(R.id.content,fragment1stuff);
        }fragmentTransaction.commit();
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

    }

}
