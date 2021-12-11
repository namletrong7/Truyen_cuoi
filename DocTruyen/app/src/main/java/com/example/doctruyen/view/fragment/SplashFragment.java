package com.example.doctruyen.view.fragment;

import android.os.Handler;

import com.example.doctruyen.Constants;
import com.example.doctruyen.R;
import com.example.doctruyen.viewmodel.SplashViewModel;

public class SplashFragment extends BaseFragment<SplashViewModel> {

    @Override
    protected Class<SplashViewModel> getViewModelClass() {
        return SplashViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.spalsh_fragment;
    }

    @Override
    protected void initViews() {
        new Handler().postDelayed(this::gotoHome, 2000);
    }


    private void gotoHome(){
        callBack.callBack(Constants.KEY_SHOW_HOME, null);
    }
}
