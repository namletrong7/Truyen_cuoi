package com.example.doctruyen.view.act;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.doctruyen.listeners.OnActionCallBack;

public abstract class BaseActivity<VM extends ViewModel> extends AppCompatActivity
        implements OnActionCallBack {

    protected  VM viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(getViewModelClass());
        setContentView(getLayoutId());
        initViews();
    }

    protected abstract Class<VM> getViewModelClass();
    protected  abstract void initViews();
    protected abstract int getLayoutId();

    protected void showFragment(int layoutID, Fragment fragment, boolean addToBackStack,
                                int anim_start, int anim_end){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (anim_start != 0 && anim_end != 0){
            ft.setCustomAnimations(anim_start, anim_end);
        }
        ft.replace(layoutID, fragment);
        if (addToBackStack){
            ft.addToBackStack("add");
        }
        ft.commit();
    }
}
