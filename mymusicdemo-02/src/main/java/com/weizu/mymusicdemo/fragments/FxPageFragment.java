package com.weizu.mymusicdemo.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.weizu.mymusicdemo.R;
import com.weizu.mymusicdemo.fragments.adapters.PageViewPagerAdapter;
import com.weizu.mymusicdemo.fragments.utils.InitViewPagerData;

import java.util.List;

/**
 * 发现页面的Fragment
 */
public class FxPageFragment extends Fragment implements View.OnClickListener{

    private ViewPager viewPager = null;
    private List<Fragment> fragments = null;
    private TextView t_yy = null;
    private TextView t_sp = null;
    private TextView t_ts = null;
    private TextView t_yy_hr = null;
    private TextView t_sp_hr = null;
    private TextView t_ts_hr = null;
    private int lastTopNavIndex = 0;
    private int curTopNavIndex = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_fx, container, false);
        // 设置ViewPager数据项
        viewPager = inflate.findViewById(R.id.fx_viewpager);
        fragments = InitViewPagerData.initFxPageViewPagerFragments();
        PageViewPagerAdapter<Fragment> adapter = new PageViewPagerAdapter<>(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        // 找到fragment_fx的顶部音乐、视频和听书三个文本TextView对象
        findTopNavTextViewElements(inflate);
        // 为ViewPager设置滑动监听
        addViewPagerListener(viewPager);
        return inflate;
    }


    private void addViewPagerListener(ViewPager viewPager) {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(curTopNavIndex != position){
                    lastTopNavIndex = curTopNavIndex;
                    curTopNavIndex = position;
                }
                setTopNavTextViewFontSize(curTopNavIndex, 24);
                setTopNavTextViewBottomLine(curTopNavIndex, true);
                setTopNavTextViewFontSize(lastTopNavIndex, 18);
                setTopNavTextViewBottomLine(lastTopNavIndex, false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setTopNavTextViewBottomLine(int index, boolean visible) {
        int visibility = visible ? View.VISIBLE : View.INVISIBLE;
        switch (index){
            case 0:
                t_yy_hr.setVisibility(visibility);
                break;
            case 1:
                t_sp_hr.setVisibility(visibility);
                break;
            case 2:
                t_ts_hr.setVisibility(visibility);
                break;
        }
    }

    private void setTopNavTextViewFontSize(int index, float fontSize) {
        switch (index){
            case 0:
                t_yy.setTextSize(fontSize);
                break;
            case 1:
                t_sp.setTextSize(fontSize);
                break;
            case 2:
                t_ts.setTextSize(fontSize);
                break;
        }
    }

    private void findTopNavTextViewElements(View inflate) {
        t_yy = inflate.findViewById(R.id.fx_textview_yy);
        t_sp = inflate.findViewById(R.id.fx_textview_sp);
        t_ts = inflate.findViewById(R.id.fx_textview_ts);

        t_yy_hr = inflate.findViewById(R.id.fx_textview_yy_hr);
        t_sp_hr = inflate.findViewById(R.id.fx_textview_sp_hr);
        t_ts_hr = inflate.findViewById(R.id.fx_textview_ts_hr);

        setTopNavTextViewElementsOnClickListener();
    }

    private void setTopNavTextViewElementsOnClickListener() {
        t_yy.setOnClickListener(this);
        t_sp.setOnClickListener(this);
        t_ts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fx_textview_yy:
                viewPager.setCurrentItem(0);
                break;
            case R.id.fx_textview_sp:
                viewPager.setCurrentItem(1);
                break;
            case R.id.fx_textview_ts:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
