package com.example.day6302.presenter;

import com.example.day6302.base.BaseFragment;
import com.example.day6302.base.BasePreseneter;
import com.example.day6302.bean.ProcBean;
import com.example.day6302.model.ProcModel;
import com.example.day6302.net.ProcCallBack;
import com.example.day6302.view.ProcView;

public class ProcPresenter extends BasePreseneter<ProcView> {

    private ProcModel procModel;

    @Override
    protected void initModel() {
        procModel = new ProcModel();
        addModel(procModel);
    }
    public void getData(){
        procModel.getData(new ProcCallBack<ProcBean>() {
            @Override
            public void onSucess(ProcBean procBean) {
                mView.setData(procBean);
            }

            @Override
            public void onFain(String str) {
            mView.showToast(str);
            }
        });
    }
}
