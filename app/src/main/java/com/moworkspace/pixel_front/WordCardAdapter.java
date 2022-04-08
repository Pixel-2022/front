package com.moworkspace.pixel_front;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordCardAdapter extends RecyclerView.Adapter<WordCardAdapter.ItemViewHolder> {

    //adapter에 들어갈 list
    private ArrayList<Data> listData = new ArrayList<>();
    String[] titles;

    public WordCardAdapter(String[] titles){
        this.titles = titles;
        System.out.println(titles);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordcard_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.wordTitle.setText(titles[position]);
//        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    void addItem(Data data){
        listData.add(data);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private TextView wordTitle;
//        private ImageView wordImage;
//        private ImageButton wordStarBtn;
//        private ImageButton wordFilmBtn;
//        private ImageButton wordDeleteBtn;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            wordTitle = itemView.findViewById(R.id.wordTitle);
//            wordImage = itemView.findViewById(R.id.wordImage);
//            wordStarBtn = itemView.findViewById(R.id.wordStarBtn);
//            wordFilmBtn = itemView.findViewById(R.id.wordFilmBtn);
//            wordDeleteBtn = itemView.findViewById(R.id.wordDeleteBtn);

        }

        void onBind(Data data){
            wordTitle.setText(data.getTitle());
//            wordImage.setImageResource(data.getResId());

        }
    }
}
