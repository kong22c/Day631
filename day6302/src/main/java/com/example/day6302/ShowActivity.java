package com.example.day6302;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.day6302.adpater.VpAdaoter;
import com.example.day6302.bean.ProcBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

;

public class ShowActivity extends AppCompatActivity {


    @BindView(R.id.vp_show)
    ViewPager vpShow;
    @BindView(R.id.tv_ye)
    TextView tvYe;
    private VpAdaoter adaoter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        int posi1 = intent.getIntExtra("posi1", 0);
        ArrayList<ProcBean.DataBean.DatasBean> proc1 = (ArrayList<ProcBean.DataBean.DatasBean>) intent.getSerializableExtra("proc1");
        Log.i("111", "initView: " + proc1.toString());
        tvYe.setText("第" + (posi1 + 1)+"张/共"+proc1.size()+"张");
        adaoter = new VpAdaoter(this, proc1);
        vpShow.setAdapter(adaoter);
        vpShow.setCurrentItem(posi1);
        vpShow.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvYe.setText("第" + position+1+"张/共"+proc1.size()+"张");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
