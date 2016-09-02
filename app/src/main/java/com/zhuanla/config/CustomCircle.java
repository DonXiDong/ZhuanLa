package com.zhuanla.config;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/8/30.
 */
public class CustomCircle extends ImageView {
    private Paint paint;

    public CustomCircle(Context context) {
       this(context,null);
    }

    public CustomCircle(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CustomCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
         paint = new Paint();
        paint.setColor(0xffffffff);
        //设置画笔的宽度
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(20,20,20,paint);
    }
}
