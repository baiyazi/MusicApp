package com.weizu.mymusicdemo.fragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weizu.mymusicdemo.fragments.beans.FxPageSongListItemBean;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weizu.mymusicdemo.R;

import java.util.List;

/**
 * 用来加载发现页面更多电台下面的歌曲列表的RecycleView适配器
 * 对应的加载的子项xml为：fx_music_song_list_below_time_item.xml
 */
public class FxPageMusicSongListBelowTimeRecycleViewAdapter<T extends FxPageSongListItemBean> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private int mRescourseId;
    private List<T> mData;

    public FxPageMusicSongListBelowTimeRecycleViewAdapter(Context context, int rescourseId, List<T> data) {
        this.mContext = context;
        this.mRescourseId = rescourseId;
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 实例化对应的子项xml文件
        View rootView = LayoutInflater.from(mContext).inflate(mRescourseId, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        // 设置数据到每个子项
        FxPageSongListItemBean bean = (FxPageSongListItemBean) mData.get(position);
        Glide.with(mContext).load(bean.getCoverImageUrl()).into(myViewHolder.coverImageView);
        myViewHolder.playNumber.setText(bean.getPlayNumber());
        myViewHolder.introduce.setText(bean.getIntroduceInfo());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView coverImageView;
        private TextView playNumber;
        private TextView introduce;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImageView = itemView.findViewById(R.id.fx_music_song_list_below_time_item_img);
            playNumber = itemView.findViewById(R.id.fx_music_song_list_below_time_item_play_number);
            introduce = itemView.findViewById(R.id.fx_music_song_list_below_time_item_intro);
        }
    }
}
