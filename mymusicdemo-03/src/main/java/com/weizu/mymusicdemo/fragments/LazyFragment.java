package com.weizu.mymusicdemo.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class LazyFragment extends Fragment {

    private View rootView; // 表示当前View实例对象
    private boolean isViewCreated = false; // rootView是否创建
    private boolean isDatasLoaded = false; // 是否加载过
    private boolean isCurrentVisible = false; // 是否可见

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(getLayoutResources(), container, false);
        }
        isViewCreated = true; // rootView创建完毕
        if(getUserVisibleHint()) setUserVisibleHint(true);
        return rootView;
    }

    protected View getRootView(){
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isViewCreated){
            Log.e("TAG", "isCurrentVisible: " + isCurrentVisible + " | isVisibleToUser: " + isVisibleToUser );
            if(!isCurrentVisible && isVisibleToUser){
                // 加载数据
                if(!isDatasLoaded) lazyLoadData();
                isDatasLoaded = true;
                isCurrentVisible = isVisibleToUser;
            }else if(isCurrentVisible && !isVisibleToUser){
                // 停止加载数据
                stopLoadData();
                isCurrentVisible = isVisibleToUser;
            }
        }
    }

    /**
     * 对子类提供一个查找元素的方法
     */
    protected <T extends View> T findViewById(int id) {
        if(isViewCreated) return (T) rootView.findViewById(id);
        return null;
    }

    /**
     * 由具体子类来实现这个方法，以实现返回当前页面的布局文件ID
     */
    protected abstract int getLayoutResources();

    /**
     * 提供两个方法，用来进行加载数据，或者停止加载数据
     */
    protected abstract void lazyLoadData();
    protected void stopLoadData(){}
}
