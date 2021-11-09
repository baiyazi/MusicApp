package com.weizu.mymusicdemo.services;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;

public class MyLaunchMusicService extends Service {

    MediaPlayer mediaPlayer = null;
    AssetManager assetManager = null;

    @Override
    public void onCreate() {
        mediaPlayer =  new MediaPlayer();
        assetManager = getAssets();

        /**
         * 监听播放完毕事件，播放完毕就发送广播
         */
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // todo 发送广播告知播放完毕
                Intent intent = new Intent("LaunchPlayerCompletion");
                sendBroadcast(intent);
            }
        });
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 加载Asserts资源文件，并设置播放
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try{
            AssetFileDescriptor afd = assetManager.openFd("launchMusic.mp3");
            mediaPlayer.reset();
            mediaPlayer.setDataSource(afd.getFileDescriptor()
                    , afd.getStartOffset()
                    , afd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (IOException e){
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}