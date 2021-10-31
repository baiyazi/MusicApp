package com.weizu.mymusicdemo.activitys;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.MessageQueue;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.Window;

import com.weizu.mymusicdemo.MainActivity;
import com.weizu.mymusicdemo.R;
import com.weizu.mymusicdemo.services.MyLaunchMusicService;

import java.io.IOException;

public class WelcomeActivity extends AppCompatActivity {

    private LaunchReceiver receiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setExitTransition(new Slide().setDuration(2000));
        super.onCreate(savedInstanceState);

        // setTheme(R.style.WelcomeTheme); // 恢复原有的样式
        setContentView(R.layout.activity_welcome);
        // todo 启动一个服务来播放欢迎音乐【Hello 酷狗】
        playLaunchMusic();
        // todo 注册广播接收器
        receiver = new LaunchReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("LaunchPlayerCompletion");
        registerReceiver(receiver, filter);

    }

    /**
     * 启动MyLaunchMusicService来播放音乐
     */
    private void playLaunchMusic() {
        Looper looper = Looper.myLooper();
        MessageQueue queue = looper.getQueue();
        queue.addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                // todo 启动Service
                Intent intent = new Intent(WelcomeActivity.this, MyLaunchMusicService.class);
                startService(intent);
                // 只需要启动一次，返回false
                return false;
            }
        });
    }

    /**
     * 广播接收器，接收来自MyLaunchMusicService的广播
     */
    class LaunchReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            // todo 播放完毕，页面进行跳转
            jumpToMainActivity();
        }
    }

    /**
     * 跳转到应用主页
     */
    private void jumpToMainActivity() {
        // 注销广播接收器，否则会内存泄漏
        unregisterReceiver(receiver);
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, R.anim.out);
        finish();
    }

    @Override
    public void finish() {
        super.finish();
    }
}