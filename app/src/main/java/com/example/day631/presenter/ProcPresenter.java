package com.example.day631.presenter;

import com.example.day631.base.BasePresenter;
import com.example.day631.bean.ProcBean;
import com.example.day631.model.ProcModel;
import com.example.day631.net.ProcCallBack;
import com.example.day631.view.ProcView;

public class ProcPresenter extends BasePresenter<ProcView> {

    private ProcModel procModel;

    @Override
    protected void initModel() {
        procModel = new ProcModel();
        addModel(procModel);
    }
    public void getdata(){
        procModel.getData(new ProcCallBack<ProcBean>() {
            @Override
            public void onSusess(ProcBean procBean) {
                mView.setData(procBean);
            }

            @Override
            public void onFain(String str) {
mView.showToast(str);
            }
        });
    }
}
