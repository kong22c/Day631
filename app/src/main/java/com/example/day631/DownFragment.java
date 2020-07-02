package com.example.day631;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.day631.bean.PrsBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownFragment extends Fragment implements View.OnClickListener {


    private ProgressBar mPb;
    private TextView mTv;
    private Button mBrnDown;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_down, container, false);

        initViews(inflate);
        myCheckPermission();
        return inflate;
    }

    private void myCheckPermission() {
        int i = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (i != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat
                    .requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }

    private void initViews(View view) {
        mPb = view.findViewById(R.id.pb);
        mTv = view.findViewById(R.id.tv);
        mBrnDown = view.findViewById(R.id.brn_down);
        EventBus.getDefault().register(this);
        mBrnDown.setOnClickListener(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMess(PrsBean prsBean){
        if (prsBean.getType()==1){
            mPb.setMax(prsBean.getMax());
        }else if(prsBean.getType()==2){
            mPb.setProgress(prsBean.getProgress());
            mTv.setText("当前下载进度"+prsBean.getTxt()+"%");
            if (prsBean.getTxt()==100)
            Toast.makeText(getActivity(), "下载完成", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        getActivity().startService(new Intent(getActivity(),MyService.class));
    }
}
