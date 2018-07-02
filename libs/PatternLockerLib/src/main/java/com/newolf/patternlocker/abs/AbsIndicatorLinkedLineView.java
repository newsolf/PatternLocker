package com.newolf.patternlocker.abs;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.newolf.patternlocker.Data.CellBean;
import com.newolf.patternlocker.config.Config;

import java.util.List;

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
public abstract class AbsIndicatorLinkedLineView {

    private  @ColorInt
    int mHitColor;
    private  @ColorInt
    int mErrorColor;
    private float mLineWidth;

    protected Paint paint;

    public AbsIndicatorLinkedLineView() {
        this.paint = Config.createPaint();
        this.paint.setStyle(Paint.Style.STROKE);
    }
    /**
     * 绘制指示器连接线
     *
     * @param canvas
     * @param hitList  Selected point list
     * @param cellBeanList
     * @param isError
     */
    public abstract void draw(@NonNull Canvas canvas,
                              @Nullable List<Integer> hitList,
                              @NonNull List<CellBean> cellBeanList,
                              boolean isError);


    public AbsIndicatorLinkedLineView setNormalColor(int hitColor) {
        mHitColor = hitColor;
        return this;
    }


    public AbsIndicatorLinkedLineView setErrorColor(int errorColor) {
        mErrorColor = errorColor;
        return this;
    }


    public AbsIndicatorLinkedLineView setLineWidth(float lineWidth) {
        mLineWidth = lineWidth;
        return this;
    }

    protected @ColorInt
    int getColor(boolean isError) {
        return isError ? mErrorColor : mHitColor;
    }

    public int getErrorColor() {
        return mErrorColor;
    }

    public int getHitColor() {
        return mHitColor;
    }

    protected float getLineWidth() {
        return mLineWidth;
    }
}
