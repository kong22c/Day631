package com.example.day6302;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;
    private FrameLayout mFl;
    private TabLayout mTl;
    private HomeFragment homeFragment;
    private WebFragment webFragment;
    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mTv = findViewById(R.id.tv);
        mFl = findViewById(R.id.fl);
        mTl = findViewById(R.id.tl);
        initFagment();
        initTab();

    }

    private void initFagment() {
        homeFragment = new HomeFragment();
        webFragment = new WebFragment();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl,homeFragment);
        transaction.add(R.id.fl,webFragment);
        transaction.show(homeFragment).hide(webFragment).commit();
    }

    private void initTab() {
        mTl.addTab(mTl.newTab().setText("首页"));
        mTl.addTab(mTl.newTab().setText("网页"));
        mTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        mTv.setText(tab.getText());
                        manager.beginTransaction().show(homeFragment).hide(webFragment).commit();

                        break;
                    case 1:
                        mTv.setText(tab.getText());
                        manager.beginTransaction().hide(homeFragment).show(webFragment).commit();

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
