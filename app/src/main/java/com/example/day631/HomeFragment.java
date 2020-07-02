package com.example.day631;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day631.adapter.ProcAdapter;
import com.example.day631.base.BaseFragment;
import com.example.day631.bean.ProcBean;
import com.example.day631.presenter.ProcPresenter;
import com.example.day631.view.ProcView;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<ProcPresenter> implements ProcView {


    @BindView(R.id.rv)
    RecyclerView rv;
    private ArrayList<ProcBean.DataBean.DatasBean> list;
    private ProcAdapter adapter;


    @Override
    protected void initLister() {

    }

    @Override
    protected void initData() {
mPresenter.getdata();
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
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresnter() {
        mPresenter=new ProcPresenter();
    }

    @Override
    public void setData(ProcBean procBean) {
list.addAll(procBean.getData().getDatas());
adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {

    }
}
