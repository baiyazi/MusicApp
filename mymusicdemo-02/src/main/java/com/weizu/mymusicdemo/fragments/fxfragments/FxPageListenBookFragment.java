package com.weizu.mymusicdemo.fragments.fxfragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.weizu.mymusicdemo.R;
import com.weizu.mymusicdemo.fragments.LazyFragment;

public class FxPageListenBookFragment extends LazyFragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = getRootView();
        initViews();
        return rootView;
    }

    private void initViews() {
        // todo 初始化一些数据
    }

    @Override
    protected int getLayoutResources() {
        // 设置布局文件-听书
        return R.layout.fx_viewpager_item_ts;
    }

    @Override
    protected void lazyLoadData() {
        Log.e("TAG", "lazyLoadData: 听书加载数据");
        Log.e("TAG", "lazyLoadData: 听书停止加载数据");
        // todo 加载数据模拟
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0){
                    Button button = findViewById(R.id.ts_page_loading_button);
                    button.setText("数据加载完毕。");
                }
            }
        };
        Message msg = new Message();
        msg.what = 0;
        handler.sendMessageDelayed(msg, 1000);
    }

    @Override
    protected void stopLoadData() {
        super.stopLoadData();
    }
}
