package com.moworkspace.pixel_front;

import android.media.Image;

import androidx.appcompat.app.AppCompatActivity;

public class Dict extends AppCompatActivity {
    String Word;
    String image;

    public String getWord(){return Word;}
    public void setWord(String s){Word = s;}
    public String getImage(){return image;}
    public void setImage(String im){image=im;}

    public Dict(String Word, String image){
        this.Word=Word;
        this.image=image;
    }
}
