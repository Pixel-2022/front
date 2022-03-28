package com.moworkspace.pixel_front;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNV;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String tag1, tag2, tag3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNV = findViewById(R.id.nav_view);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());

                return true;
            }
        });
        mBottomNV.setSelectedItemId(R.id.navigation_2);
    }
    private void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
        String tag = String.valueOf(id);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }


        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        //페이지 옮기기
        if (fragment == null) {
            if (id == R.id.navigation_1) {
                tag1=tag;
                fragment = new FragmentPage1();
            } else if (id == R.id.navigation_2){
                tag2=tag;
                fragment = new FragmentPage2();
            }else {
                tag3=tag;
                fragment = new FragmentPage3();
            }

            fragmentTransaction.add(R.id.content_layout, fragment, tag);
        } else {
            if(fragment==fragmentManager.findFragmentByTag(tag2)) { //메인 페이지 항시 리플래쉬
                fragmentTransaction.remove(fragment);
                fragment = new FragmentPage2();
                fragmentTransaction.add(R.id.content_layout, fragment, tag2);
            }else {
                fragmentTransaction.show(fragment);
            }
        }

        clearBackStack();
        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
    }

    //백스택 제거 작업
    private void clearBackStack() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        while (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.popBackStackImmediate();
        }
    }
}