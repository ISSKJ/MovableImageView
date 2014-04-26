package org.dnu.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * The Class MovableBitmapView.
 */
public class MovableBitmapView extends View {

    /** The Constant TAG. */
    private static final String TAG = "MovableBitmapView";

    /** The Constant DOWN. */
    public static final int DOWN = 1;

    /** The Constant UP. */
    public static final int UP = 2;

    /** The Constant IDLE. */
    public static final int IDLE = 3;

    /** The bitmap. */
    private Bitmap mBitmap;

    /** The rect source. */
    private Rect mRectS;

    /** The rect destination. */
    private Rect mRectD;

    /** The paint. */
    private Paint mPaint;

    /** The drawing state. */
    private int mState;

    /** The margin top. */
    private int mMarginTop;


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
        mRectS.right = bmp.getWidth();
        mRectS.bottom = bmp.getHeight();
        setMarginTop(100);
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

    /**
     * Move up.
     *
     * @param state the new state
     */
    public void setState(int state) {
        mState = state;
    }



    /* (non-Javadoc)
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas c) {
        if (mBitmap == null) return;

        switch (mState) {
            case DOWN:
                mRectS.top++;
                mRectS.bottom++;
                if (mRectS.bottom >= mBitmap.getHeight()) {
                    mRectS.top = mMarginTop;
                    mRectS.bottom = mBitmap.getHeight();
                }
                break;
            case UP:
                mRectS.top--;
                mRectS.bottom--;
                if (mRectS.top <= 0) {
                    mRectS.top = 0;
                    mRectS.bottom = mBitmap.getHeight()-mMarginTop;
                }
                break;
            case IDLE:
                break;
            default:
                break;
        }

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
    }
}
