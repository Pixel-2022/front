package com.moworkspace.pixel_front;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class Fragment1Hand extends Fragment {
    private androidx.recyclerview.widget.LinearLayoutManager LinearLayoutManager;
    private View v;

    //카메라
    CameraSurfaceView surfaceView;
    //권한
    private final int MY_PERMISSIONS_REQUEST_CAMERA=1001;
    Context ct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v= inflater.inflate(R.layout.fragment1_hand,container,false);
        surfaceView=v.findViewById(R.id.surfacaview);
        //권한
        ct=container.getContext();
        int permisionCheck= ContextCompat.checkSelfPermission(ct, Manifest.permission.CAMERA);
        if(permisionCheck!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(ct,"권한 승인이 필요합니다.", Toast.LENGTH_LONG).show();
            if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) ct,Manifest.permission.CAMERA)){
                Toast.makeText(ct,"000부분 사용을 위해 카메라 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            }else{
                ActivityCompat.requestPermissions((Activity) ct,
                        new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                Toast.makeText(ct,"000부분 사용을 위해 카메라 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            }
        }
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResults){
        switch(requestCode){
            case MY_PERMISSIONS_REQUEST_CAMERA:{
                //비어있을 때
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ct,"승인이 허가되어 있습니다.",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ct,"아직 승인받지 않았습니다.",Toast.LENGTH_LONG).show(); } return;
            }
        }
    }
}
