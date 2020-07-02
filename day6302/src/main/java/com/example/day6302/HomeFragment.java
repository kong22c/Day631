package com.example.day6302;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day6302.adpater.ProcAdapter;
import com.example.day6302.base.BaseFragment;
import com.example.day6302.bean.ProcBean;
import com.example.day6302.presenter.ProcPresenter;
import com.example.day6302.view.ProcView;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<ProcPresenter>implements ProcView {


    @BindView(R.id.rv)
    RecyclerView rv;
    private ArrayList<ProcBean.DataBean.DatasBean> list;
    private ProcAdapter adapter;
    private MyReceiver myReceiver;
    private int posi;


    @Override
    protected void initPresenter() {
        mPresnter=new ProcPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initLister() {
        adapter.setOnItemClickLister(new ProcAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(int position) {
                posi=position;
                Intent intent = new Intent("ccc");
                intent.putExtra("title",list.get(posi).getTitle());
                intent.putExtra("posi",posi);
                intent.putExtra("proc",list);
                getActivity().sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new ProcAdapter(getActivity(), list);
        rv.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        mPresnter.getData();
    }

    @Override
    public void setData(ProcBean procBean) {
            list.addAll(procBean.getData().getDatas());
            adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String title = intent.getStringExtra("title");
            Toast.makeText(context, title, Toast.LENGTH_SHORT).show();
            int posi = intent.getIntExtra("posi", 0);
            ArrayList<ProcBean.DataBean.DatasBean> proc = (ArrayList<ProcBean.DataBean.DatasBean>) intent.getSerializableExtra("proc");
            Intent intent1 = new Intent(context, ShowActivity.class);
            intent1.putExtra("posi1",posi);
            intent1.putExtra("proc1",proc);
            context.startActivity(intent1);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        myReceiver = new MyReceiver();
        IntentFilter ccc = new IntentFilter("ccc");
        getActivity().registerReceiver(myReceiver,ccc);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(myReceiver);
    }
}
