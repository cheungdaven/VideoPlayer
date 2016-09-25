package com.daven.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff;

import com.daven.videoplayer.R;

public class RoundImageView extends ImageView {
    
    private int type;
    private boolean mHasBorder,mHasDefaultPic;
    private static final int CIRCLE = 0;
    private static final int ROUND = 1;
    private static final int DEFAULT_BORDER_RADIUS = 10;
    private static final int BORDER_CIRCLE_WIDTH = 5;
    private int mBorderRadius;
    private RectF mRoundRect;
    private Drawable mDefaultDrawable;
    
    private Paint mBitmapPaint,mOutCricle;
    private Matrix mMatrix;
    private BitmapShader mBitmapShader;
    private int mWidth;
    private int mRadius;

    public RoundImageView(Context context) {
        super(context);
       mMatrix = new Matrix();
       mBitmapPaint = new Paint();
       mBitmapPaint.setAntiAlias(true);
        mOutCricle = new Paint();
        mOutCricle.setColor(Color.WHITE);
       type = CIRCLE;
       mWidth = getResources().getDimensionPixelSize(R.dimen.single_video_card_radius);
    }
    
    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
       mMatrix = new Matrix();
       mBitmapPaint = new Paint();
       mBitmapPaint.setAntiAlias(true);
       mBitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

       mOutCricle = new Paint();
       mOutCricle.setColor(Color.WHITE);
       
       TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
       mBorderRadius = a.getDimensionPixelSize(R.styleable.RoundImageView_BorderRadius,
               (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_BORDER_RADIUS, getResources().getDisplayMetrics()));
       type = a.getInt(R.styleable.RoundImageView_Type, CIRCLE);
       a.recycle();
    }
    
    public void setHasBorder(boolean b){
        mHasBorder = b;
    }

    public void setHasDefaultPic(boolean b){
        mHasDefaultPic = b;
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    protected void onDraw(Canvas canvas)
    {
        setupShader();
        
        if( type == CIRCLE){
            if(mHasBorder){
                canvas.drawCircle(mRadius, mRadius, mRadius, mOutCricle);
                canvas.drawCircle(mRadius, mRadius, mRadius-BORDER_CIRCLE_WIDTH, mBitmapPaint);
            } else {
                canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);
            }

        } else if(type == ROUND){
            canvas.drawRoundRect(mRoundRect, mBorderRadius, mBorderRadius, mBitmapPaint);
        }
        
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        
        if(type == ROUND){
            mRoundRect = new RectF(0,0,getWidth(),getHeight());
        }
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        
        if(type == CIRCLE){
            int minWidth =  Math.min(getMeasuredHeight(), getMeasuredWidth());
            mWidth = minWidth == 0 ? mWidth : minWidth;
            mRadius = mWidth/2;
            setMeasuredDimension(mWidth, mWidth);
        }
    }

    private void setupShader(){
        Drawable drawable = getDrawable();
        if(drawable == null){
            if (mDefaultDrawable == null && mHasDefaultPic)
            {
                mDefaultDrawable = getResources().getDrawable(R.mipmap.video);
            } else {
                return;
            }
            drawable = mDefaultDrawable;
        }
        
        Bitmap bitmap = drawableToBitmap(drawable);
        mBitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        float scale = 1.0f;
        
        if(type == CIRCLE){
            int bSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
            scale = mWidth * 1.0f / bSize;
        } else if (type == ROUND){
            scale = Math.max(getWidth() * 1.0f / bitmap.getWidth(), 
                    getHeight() * 1.0f /bitmap.getHeight());
        }
        
        mMatrix.setScale(scale, scale);
        mBitmapShader.setLocalMatrix(mMatrix);
        mBitmapPaint.setShader(mBitmapShader);
    }
    
    private Bitmap drawableToBitmap(Drawable drawable)
    {
        if(drawable instanceof BitmapDrawable){
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        
        int h = drawable.getIntrinsicHeight();
        int w = drawable.getIntrinsicWidth();
        Bitmap  bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,w,h);
        drawable.draw(canvas);
        return bitmap;
    }
}
