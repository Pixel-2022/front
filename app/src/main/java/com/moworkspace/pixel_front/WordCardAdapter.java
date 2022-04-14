package com.moworkspace.pixel_front;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WordCardAdapter extends RecyclerView.Adapter<WordCardAdapter.ItemViewHolder> {

    //adapter에 들어갈 list
    private ArrayList<Data> listData = new ArrayList<>();
    String[] titles;
    int[] images;
    String[] videos;
    //Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    //직전에 클릭했던 item의 position
    private int prePosition = -1;
    private ArrayList<Data> data;
    public WordCardAdapter(ArrayList<Data> data){
        this.data = data;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordcard_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Data data1 = data.get(position);
        holder.wordTitle.setText(data1.getTitle());
        holder.wordImage.setImageResource(data1.getResId());
//        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void addItem(Data data){
        listData.add(data);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private Data data;
        public final View mView;
        private TextView wordTitle;
        private ImageView wordImage;
        private LinearLayout expanded;
//        private ImageButton wordStarBtn;
//        private ImageButton wordFilmBtn;
//        private ImageButton wordDeleteBtn;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            wordTitle = itemView.findViewById(R.id.wordTitle);
            wordImage = itemView.findViewById(R.id.wordImage);
            expanded = itemView.findViewById(R.id.expandedLayout);
            mView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int isVisible = expanded.getVisibility();

                    if(isVisible==8){
                        expanded.setVisibility(v.VISIBLE);
                    }else if(isVisible==0){
                        expanded.setVisibility(v.GONE);
                    }

                }
            });
        }

//        void onBind(Data data){
//            wordTitle.setText(data.getTitle());
//            wordImage.setImageResource(data.getResId());

//        }

    }
}
