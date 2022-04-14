package com.moworkspace.pixel_front;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import java.io.IOException;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    SurfaceHolder holder;
    Camera camera = null;

    public CameraSurfaceView(Context context){
        super(context);

        init(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        holder=getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        camera =Camera.open();
        camera.setDisplayOrientation(90);
        try {
            camera.setPreviewDisplay(holder);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera=null;
    }

    public boolean capture(Camera.PictureCallback callback){
        if(camera !=null){
            camera.takePicture(null,null,callback);
            return true;
        }else {
            return false;
        }
    }
}
