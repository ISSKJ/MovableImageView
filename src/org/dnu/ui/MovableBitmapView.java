package org.dnu.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * The Class MovableBitmapView.
 * @author ISSKJ
 */
public class MovableBitmapView extends View {

    /** The Constant TAG. */
    private static final String TAG = "MovableBitmapView";

    /** The bitmap. */
    private Bitmap mBitmap;

    /** The rect source. */
    private Rect mRectS;

    /** The rect destination. */
    private Rect mRectD;

    /** The paint. */
    private Paint mPaint;

    /** The margin top. */
    private int mMarginTop;

    /** The current view location. */
    private int[] mLocation = new int[2];

    /** The initial view location. */
    private int[] mInitialLocation = new int[2];

    /** The bitmap width. */
    private int mBitmapWidth;

    /** The bitmap height. */
    private int mBitmapHeight;



    /**
     * Instantiates a new movable bitmap view.
     *
     * @param context the context
     * @param attrs the attrs
     * @param defStyleAttr the def style attr
     */
    public MovableBitmapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView(context);
    }

    /**
     * Instantiates a new movable bitmap view.
     *
     * @param context the context
     * @param attrs the attrs
     */
    public MovableBitmapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    /**
     * Instantiates a new movable bitmap view.
     *
     * @param context the context
     */
    public MovableBitmapView(Context context) {
        super(context);
        initializeView(context);
    }

    /**
     * Initialize view.
     *
     * @param context the context
     */
    private void initializeView(Context context) {
        mRectS = new Rect();
        mRectD = new Rect();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

    }

    /**
     * Sets the bitmap.
     *
     * @param bmp the new bitmap
     */
    public void setBitmap(Bitmap bmp) {
        mBitmap = bmp;
        mBitmapHeight = bmp.getHeight();
        mBitmapWidth = bmp.getWidth();
        mRectS.right = mBitmapWidth;
        mRectS.bottom = mBitmapHeight;
        setMarginTop(150);
    }

    /**
     * Sets the image resource.
     *
     * @param resourceId the new image resource
     */
    public void setImageResource(int resourceId) {
        setBitmap(BitmapFactory.decodeResource(getResources(), resourceId));
    }

    /**
     * Sets the margin top.
     *
     * @param top the new margin top
     */
    public void setMarginTop(int top) {
        mMarginTop = top;
        mRectS.bottom -= top;
    }


    /* (non-Javadoc)
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas c) {
        if (mBitmap == null) return;

        super.getLocationOnScreen(mLocation);
        double diff = mInitialLocation[1] - mLocation[1];

        int span = (int)(diff / mBitmapHeight * (double)mMarginTop/2.0);

        mRectS.top = span;
        mRectS.bottom = mBitmapHeight - mMarginTop + span;
        if (mRectS.bottom >= mBitmapHeight) {
            mRectS.top = mMarginTop;
            mRectS.bottom = mBitmapHeight;
        }
        if (mRectS.top <= 0) {
            mRectS.top = 0;
            mRectS.bottom = mBitmapHeight - mMarginTop;
        }

        //Log.v(TAG, String.format("ID:%d diff:%f span:%d",
        //            getId(), diff, span));


        c.drawBitmap(mBitmap, mRectS, mRectD, mPaint);
    }

    /* (non-Javadoc)
     * @see android.view.View#onSizeChanged(int, int, int, int)
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectD.right = w;
        mRectD.bottom = h;
        super.getLocationOnScreen(mInitialLocation);
    }
}
