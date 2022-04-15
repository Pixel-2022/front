package com.moworkspace.pixel_front;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class DictAdapter extends RecyclerView.Adapter<DictAdapter.ViewHolder>{
    int[] images;
    private Context context;
    private Dialog dialog;

    private ArrayList<Dict> dict;
    public DictAdapter(ArrayList<Dict> dict){
        this.dict=dict;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        this.context=parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_word,parent, false);
        return new ViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dict dict1=dict.get(position);
        holder.imageView.setImageResource(dict1.getImage());
        holder.textView.setText(dict1.getSub());
    }


    @Override
    public int getItemCount() {
        return dict.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        ImageView save;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.dict_image);
            textView = itemView.findViewById(R.id.dict_name);
            save=itemView.findViewById(R.id.save_word);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


            //카드 선택 시 이동
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    MainActivity main = (MainActivity) DictAdapter.this.context;
                   // main.toMain(b);
                }
            });
        }
    }

}
