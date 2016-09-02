package com.zhuanla.config;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/6/13.
 */
public class PointView  extends ImageView{

   //设置style的常量值
    /*没有消息*/
    public static final int NO_MESSAGE = 1;
    /*
    * 只有一个红色的圆点
    * */
    public static final int ONLY_POIN = 2;
    /*
    * 当消息数量的圆点
    * */
    public static final int NUMBER_MESSAGE = 3;
    /*
    当前的消息模式,默认是 没有红点
    * */
    private int currentStyle = NO_MESSAGE;
    /*
    * 记录当前的消息数量
    * */
    private int messageNumber = 0;

    private Paint paint;
    private TextPaint textPaint;
    private int i;
    private int j;

    public PointView(Context context) {
        this(context,null);
    }

    public PointView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public PointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
//    分析: 1.显示模式
//             1.什么都不显示
//             2.只显示一个小圆点
//             3.显示带数字的圆点
//                  1.0 <   <100 直接去显示数字
//                  2. >100   显示 99+

//           2.提供可以设置消息数量的方法
//                1.设置了数字后,更新显示

    private void init() {
        //获得写圆点的画笔
        paint = new Paint();
        paint.setColor(0xffbc1717);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        //设置填充满图形
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        //获得写文本的画笔
        textPaint = new TextPaint();
        //设置画笔颜色
        textPaint.setColor(0xffffffff);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(25);
        i = DensityUtil.dip2px(getContext(), 6);
        j = DensityUtil.dip2px(getContext(), 9);

    }

     public void setPointStyle(int style){
         if(style > 0 &&  style < 4){
             currentStyle = style;
             invalidate();
         }
}
    /*
    * 2.提供可以设置消息数量的方法
    * */
     public void setMessageNumber(int number){
        if(number > 0){
            messageNumber = number;
            //同时重新绘制
            invalidate();
        }
     }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //根据不同的状态重新绘制
        switch (currentStyle) {
            case NO_MESSAGE://不绘制任何东西
            break;
            case ONLY_POIN: //只 绘制红色圆点
                canvas.drawCircle(getWidth()-getPaddingRight()+4,getPaddingTop()-2,i,paint);
                break;
            case NUMBER_MESSAGE://绘制带数据的圆点
//                3.显示带数字的圆点
//                    0. 0(其他) 就不显示
//                  1.0 <   <100 直接去显示数字
//                  2. >100   显示 99+
               if(messageNumber > 0 && messageNumber <100){
                    //先绘制一个红色的圆点
                   //在圆点上写数字
                   canvas.drawCircle(getWidth() - getPaddingRight()-12,getPaddingTop(),j,paint);
                    //获得文本长度
                    textPaint.measureText(messageNumber + "");
                   canvas.drawText(messageNumber + "", getWidth()-getPaddingRight()-textPaint.measureText(messageNumber + "")/2-12,(int)(getPaddingTop()+ 1.5*textPaint.getFontMetrics().bottom),textPaint);
               }else if(messageNumber > 100){
                   canvas.drawCircle(getWidth() - getPaddingRight(),getPaddingTop(),j,paint);
                   canvas.drawText("99+", getWidth()-getPaddingRight()-textPaint.measureText("99+")/2,(int)(getPaddingTop()+ 1.5*textPaint.getFontMetrics().bottom),textPaint);

               }



                break;
            default:
                break;
        }

    }
}
