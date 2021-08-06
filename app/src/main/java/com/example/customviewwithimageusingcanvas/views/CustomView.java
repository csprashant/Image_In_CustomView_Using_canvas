package com.example.customviewwithimageusingcanvas.views;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.annotation.Nullable;

import com.example.customviewwithimageusingcanvas.R;
public class CustomView extends View {
    Bitmap image;
    public CustomView(Context context) {
        super(context);
        init(null);
    }
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    public void init(AttributeSet attr)
    {
        image= BitmapFactory.decodeResource(getResources(), R.drawable.image1);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
               if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN)
                   getViewTreeObserver().removeOnGlobalLayoutListener(this);
                else
                   getViewTreeObserver().removeOnGlobalLayoutListener(this);
                image=getResizeBitmap(image,getWidth(),getHeight());
            }
        });
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(image,0,0,null);
    }
    private Bitmap getResizeBitmap(Bitmap image,int reqWidth,int reqHeight){
        Matrix matrix=new Matrix();
        RectF srect=new RectF(0,0,image.getWidth(),image.getHeight());
        RectF drect=new RectF(0,0,reqWidth,reqHeight);
        matrix.setRectToRect(srect,drect,Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(image,0,0,image.getWidth(),image.getHeight(),matrix,true);
    }
}
