package com.weizu.mymusicdemo.fragments.adapters;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class PageViewPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    // 外部传入的ViewPager对应的Item对象
    private List<T> mList;

    public PageViewPagerAdapter(@NonNull FragmentManager fm, List<T> mList) {
        super(fm);
        this.mList = mList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }
}
