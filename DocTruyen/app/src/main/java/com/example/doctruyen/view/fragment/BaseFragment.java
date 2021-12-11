package com.example.doctruyen.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.doctruyen.listeners.OnActionCallBack;

public abstract class BaseFragment<V extends ViewModel> extends Fragment {
    protected Context mContext;
    protected View mRootView;
    protected OnActionCallBack callBack;

    protected V mViewModel;

    public void setCallBack(OnActionCallBack callBack) {
        this.callBack = callBack;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public final <T extends View> T findViewById(int id, View.OnClickListener event) {
        T v = mRootView.findViewById(id);
        if (v != null && event != null) {
            v.setOnClickListener(event);
        }
        return v;
    }

    public final <T extends View> T findViewById(int id) {
        return findViewById(id, null);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(getViewModelClass());
        initViews();
        return mRootView;
    }


    protected abstract Class<V> getViewModelClass();

    protected abstract int getLayoutId();

    protected abstract void initViews();


}
