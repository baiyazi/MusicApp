package com.weizu.mymusicdemo.fragments.fxfragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weizu.mymusicdemo.R;
import com.weizu.mymusicdemo.fragments.LazyFragment;
import com.weizu.mymusicdemo.fragments.adapters.FxPageMusicSongListBelowTimeRecycleViewAdapter;
import com.weizu.mymusicdemo.fragments.beans.FxPageSongListItemBean;

import java.util.ArrayList;
import java.util.List;

public class FxPageMusicFragment extends LazyFragment {

    private View rootView;
    private RecyclerView recyclerView;


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
        recyclerView = findViewById(R.id.fx_music_song_list_below_time);

        List<FxPageSongListItemBean> beans = new ArrayList<>();
        FxPageSongListItemBean bean1 = new FxPageSongListItemBean();
        bean1.setCoverImageUrl("https://img-blog.csdnimg.cn/1bcf9238d6b247c78bb8fabccf2d3c76.png");
        bean1.setPlayNumber("123万");
        bean1.setIntroduceInfo("很好的一个歌曲");
        beans.add(bean1);

        FxPageSongListItemBean bean2 = new FxPageSongListItemBean();
        bean2.setCoverImageUrl("https://img2.baidu.com/it/u=1547628950,4072084608&fm=26&fmt=auto");
        bean2.setPlayNumber("123万");
        bean2.setIntroduceInfo("很好的一个歌曲");
        beans.add(bean2);
        beans.add(bean1);
        beans.add(bean2);
        beans.add(bean1);
        beans.add(bean2);

        FxPageMusicSongListBelowTimeRecycleViewAdapter<FxPageSongListItemBean> adapter =
                new FxPageMusicSongListBelowTimeRecycleViewAdapter<>(getContext(), R.layout.fx_music_song_list_below_time_item, beans);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(manager);
        // 设置Item的间距
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 8;
                outRect.right = 8;
                outRect.top = 16;
                outRect.bottom = 16;
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResources() {
        // 设置布局文件-音乐
        return R.layout.fx_viewpager_item_yy;
    }

    @Override
    protected void lazyLoadData() {
        Log.e("TAG", "lazyLoadData: 音乐加载数据");
        // todo 加载数据模拟

    }

    @Override
    protected void stopLoadData() {
        super.stopLoadData();
        Log.e("TAG", "lazyLoadData: 音乐停止加载数据");
    }
}
