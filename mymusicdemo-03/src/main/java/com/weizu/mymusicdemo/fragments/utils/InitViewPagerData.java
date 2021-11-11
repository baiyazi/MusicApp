package com.weizu.mymusicdemo.fragments.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.weizu.mymusicdemo.R;
import com.weizu.mymusicdemo.fragments.fxfragments.FxPageListenBookFragment;
import com.weizu.mymusicdemo.fragments.fxfragments.FxPageMusicFragment;
import com.weizu.mymusicdemo.fragments.fxfragments.FxPageVideoFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class InitViewPagerData {

    public static List<Fragment> initFxPageViewPagerFragments() {
        List<Fragment> lists = new ArrayList<>();
        Fragment item_yy = new FxPageMusicFragment(); // fx_viewpager_item_yy.xml
        Fragment item_sp = new FxPageVideoFragment(); // fx_viewpager_item_sp.xml
        Fragment item_ts = new FxPageListenBookFragment(); // fx_viewpager_item_ts.xml
        lists.add(item_yy);
        lists.add(item_sp);
        lists.add(item_ts);
        return lists;
    }

}
