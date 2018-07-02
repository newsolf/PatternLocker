package com.newolf.patternlocker.abs;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.newolf.patternlocker.Data.CellBean;
import com.newolf.patternlocker.config.Config;

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * date :  2018/6/29
 * desc:
 * history:
 * ================================================
 */
public abstract class AbsHitCellView {
    private @ColorInt
    int mHitColor;
    private @ColorInt
    int mErrorColor;
    private @ColorInt
    int mFillColor;
    private float mLineWidth;

    protected Paint paint;

    public AbsHitCellView() {
        this.paint = Config.createPaint();
        this.paint.setStyle(Paint.Style.FILL);
    }

    /**
     * 绘制已设置的每个图案的样式
     *
     * @param canvas   The Canvas to which the View is rendered.
     * @param cellBean The target cell view
     * @param isError  If error is true else false
     */
    public abstract void draw(@NonNull Canvas canvas, @NonNull CellBean cellBean, boolean isError);

    public AbsHitCellView setHitColor(int hitColor) {
        mHitColor = hitColor;
        return this;
    }

    public AbsHitCellView setErrorColor(int errorColor) {
        mErrorColor = errorColor;
        return this;
    }


    public AbsHitCellView setLineWidth(float lineWidth) {
        mLineWidth = lineWidth;
        return this;
    }

    public AbsHitCellView setFillColor(int fillColor) {
        mFillColor = fillColor;
        return this;
    }

    protected int getColor(boolean isError) {
        return isError ? mErrorColor : mHitColor;
    }

    protected int getFillColor() {
        return mFillColor;
    }

    public int getHitColor() {
        return mHitColor;
    }

    protected float getLineWidth() {
        return mLineWidth;
    }
}
