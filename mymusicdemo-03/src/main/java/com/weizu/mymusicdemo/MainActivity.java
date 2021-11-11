package com.weizu.mymusicdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.weizu.mymusicdemo.fragments.FxPageFragment;
import com.weizu.mymusicdemo.fragments.KgPageFragment;
import com.weizu.mymusicdemo.fragments.WdPageFragment;
import com.weizu.mymusicdemo.fragments.ZbPageFragment;

public class MainActivity extends AppCompatActivity {

    private FxPageFragment fxPageFragment;
    private KgPageFragment kgPageFragment;
    private WdPageFragment wdPageFragment;
    private ZbPageFragment zbPageFragment;
    private FragmentManager fragmentManager;

    private ImageView[] bottomNavImgs;
    private TextView[] bottomNavTxts;
    private int[] bottomNavImgsRescourseDefault;
    private int[] bottomNavImgsRescourseChoosed;
    private Fragment[] fragments;

    private int currentBottomNavIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoActionBar);
        setContentView(R.layout.activity_main);

        initViews();

        fragmentManager.beginTransaction()
                .add(R.id.fx_framelayout, fxPageFragment, "fxPageFragment")
                .add(R.id.fx_framelayout, kgPageFragment, "kgPageFragment")
                .add(R.id.fx_framelayout, wdPageFragment, "wdPageFragment")
                .add(R.id.fx_framelayout, zbPageFragment, "zbPageFragment")
                .hide(kgPageFragment)
                .hide(wdPageFragment)
                .hide(zbPageFragment)
                .show(fxPageFragment)
                .commit();
    }

    /**
     * 底部导航栏的点击响应函数
     * @param view
     */
    @SuppressLint("NonConstantResourceId")
    public void onBottomNavClick(View view) {
        int clickedIndex = 0;
        switch (view.getId()){
            case R.id.nav_zb:
                clickedIndex = 1;
                break;
            case R.id.nav_cur:
                clickedIndex = 2;
                break;
            case R.id.nav_kg:
                clickedIndex = 3;
                break;
            case R.id.nav_wd:
                clickedIndex = 4;
                break;
        }
        if(clickedIndex != 2){
            // todo 响应对应的Fragment
            // 首先清除底部导航的所有默认样式
            clearAllBottomNavStyle();
            // 然后设置样式
            setBottomNavStyleByIndex(clickedIndex);
            // 切换对应的Fragment
            switchToFragmentByIndex(clickedIndex);
        }else{
            // todo 其余的响应逻辑
        }
    }

    private void switchToFragmentByIndex(int clickedIndex) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(clickedIndex != currentBottomNavIndex){
            transaction.hide(fragments[currentBottomNavIndex]);
            //如果没有，就添加Fragment
            if(fragments[clickedIndex].isAdded()){
                transaction.add(R.id.fx_framelayout, fragments[clickedIndex]);
            }
            transaction.show(fragments[clickedIndex]);
            transaction.commit();
        }
        currentBottomNavIndex = clickedIndex;
    }

    /**
     * 设置选中样式，通过下标标识
     * @param index
     */
    private void setBottomNavStyleByIndex(int index) {
        if(bottomNavTxts[index] != null && bottomNavImgsRescourseDefault[index] != -1) {
            bottomNavTxts[index].setTextColor(getColor(R.color.full_screen));
            bottomNavImgs[index].setImageResource(bottomNavImgsRescourseChoosed[index]);
        }
    }

    /**
     * 清除所有底部导航栏的选中样式
     */
    private void clearAllBottomNavStyle() {
        for (int i = 0; i < bottomNavTxts.length; i++) {
            if(bottomNavTxts[i] != null && bottomNavImgsRescourseDefault[i] != -1){
                bottomNavTxts[i].setTextColor(getColor(R.color.item_not_choosed));
                bottomNavImgs[i].setImageResource(bottomNavImgsRescourseDefault[i]);
            }
        }
    }

    private void initViews() {
        fxPageFragment = new FxPageFragment();
        kgPageFragment = new KgPageFragment();
        wdPageFragment = new WdPageFragment();
        zbPageFragment = new ZbPageFragment();
        fragments = new Fragment[]{fxPageFragment, zbPageFragment, null, kgPageFragment, wdPageFragment};
        fragmentManager = getSupportFragmentManager();

        // 为了清除样式的底部导航栏
        bottomNavImgs = new ImageView[5];
        bottomNavTxts = new TextView[5];
        bottomNavImgs[0] = findViewById(R.id.nav_fx_imageview);
        bottomNavTxts[0] = findViewById(R.id.nav_fx_textView);
        bottomNavImgs[1] = findViewById(R.id.nav_zb_imageview);
        bottomNavTxts[1] = findViewById(R.id.nav_zb_textview);
        bottomNavImgs[2] = findViewById(R.id.nav_cur_imageview);
        bottomNavTxts[2] = null; // 没有文本
        bottomNavImgs[3] = findViewById(R.id.nav_kg_imageview);
        bottomNavTxts[3] = findViewById(R.id.nav_kg_textview);
        bottomNavImgs[4] = findViewById(R.id.nav_wd_imageview);
        bottomNavTxts[4] = findViewById(R.id.nav_wd_textview);

        bottomNavImgsRescourseDefault = new int[]{R.drawable.fx_not_choosed,
                R.drawable.zb_not_choosed, -1, R.drawable.kg_not_choosed, R.drawable.wd_not_choosed};
        bottomNavImgsRescourseChoosed = new int[]{R.drawable.fx_choosed,
                R.drawable.zb_choosed, -1, R.drawable.kg_choosed, R.drawable.wd_choosed};

    }
}