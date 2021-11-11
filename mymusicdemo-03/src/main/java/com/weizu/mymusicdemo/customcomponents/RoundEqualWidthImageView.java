package com.weizu.mymusicdemo.customcomponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.weizu.mymusicdemo.R;

public class RoundEqualWidthImageView extends AppCompatImageView {
    private int width;
    private int height;
    private int roundDp;

    public RoundEqualWidthImageView(Context context) {
        super(context);
    }

    public RoundEqualWidthImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.roundEqualWidthImageView);
        // 默认为直角，没有弧度
        roundDp = array.getDimensionPixelOffset(R.styleable.roundEqualWidthImageView_radius, 0);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);  // 设置宽度一样
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 设置缩放类型
        setScaleType(ScaleType.CENTER_CROP);
        if (width < roundDp || height < roundDp) roundDp = 5;
        Path path = new Path();
        // 四个圆角裁剪
        path.moveTo(roundDp, 0);
        path.lineTo(width - roundDp, 0);
        path.quadTo(width, 0, width, roundDp);
        path.lineTo(width, height - roundDp);
        path.quadTo(width, height, width - roundDp, height);
        path.lineTo(roundDp, height);
        path.quadTo(0, height, 0, height - roundDp);
        path.lineTo(0, roundDp);
        path.quadTo(0, 0, roundDp, 0);
        canvas.clipPath(path);
        super.onDraw(canvas);
        // 绘制暗色渐变
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fx_music_song_list_below_time_item_cover_bg, null);
        canvas.drawBitmap(bitmap, 0, 0, null);
    }
}
