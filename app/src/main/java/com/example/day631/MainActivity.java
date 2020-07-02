package com.example.day631;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.tl)
    TabLayout tl;
    private HomeFragment homeFragment;
    private DownFragment downFragment;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        inittab();
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        downFragment = new DownFragment();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl,homeFragment);
        transaction.add(R.id.fl,downFragment);
        transaction.show(homeFragment).hide(downFragment).commit();
    }

    private void inittab() {
        tl.addTab(tl.newTab().setText("首页"));
        tl.addTab(tl.newTab().setText("下载"));
       tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               switch (tab.getPosition()){
                   case 0:
                       manager.beginTransaction().show(homeFragment).hide(downFragment).commit();
                       break;
                   case 1:
                       manager.beginTransaction().hide(homeFragment).show(downFragment).commit();
                       break;
               }
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });
    }
}
