package com.maskedgeek.androidinterviewprep.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    private String TAG = CustomView.class.getSimpleName();
    private int mHeight;
    private int mWidth;
    // TO fix width of arc
    private int stroke = 80;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        Log.d(TAG, " onMeasure ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // To maintain square shape of the customview
        int width = widthMeasureSpec + (2 * stroke);
        int height = heightMeasureSpec + (2 * stroke);
        int square = Math.min(width, height);
        setMeasuredDimension(square, square);
    }

    /*
    @Override
    protected void onLayout(Boolean changed, int left, int top,int right,int bottom){
        Log.d(TAG, " onLayout ");
        super.onLayout(changed, left, top, right, bottom);
    }
     */

    @Override
    public void onDraw(Canvas canvas){
        Log.d(TAG, " onDraw ");
        // super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true); // Smooth edges
        paint.setColor(Color.rgb(48,108,120));
        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeWidth(stroke);

        Paint paintLine = new Paint();
        paintLine.setAntiAlias(true); // Smooth edges
        paintLine.setColor(Color.rgb(255,255,255));
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(stroke);

         canvas.drawArc( 0f + (stroke/2), 0 + (stroke/2), (float)getWidth() - (stroke/2),(float)getHeight(),180f,180f,true,paint);

        // canvas.drawLine(0f, (getHeight()/2) -  stroke ,getWidth() , (getHeight()/2) - stroke, paintLine);

         canvas.drawLine(0f, (getHeight()/4)  + (getWidth()/4) + stroke/4,getWidth(), (getHeight()/4) + (getWidth()/4) + stroke/4, paintLine);

    }

}
