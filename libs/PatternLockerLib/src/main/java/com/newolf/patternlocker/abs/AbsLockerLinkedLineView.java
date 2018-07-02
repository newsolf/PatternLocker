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
public abstract class AbsLockerLinkedLineView {
    private  @ColorInt
    int mHitColor;
    private  @ColorInt
    int mErrorColor;
    private float mLineWidth;

    protected Paint paint;

    public AbsLockerLinkedLineView() {
        this.paint = Config.createPaint();
        this.paint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 绘制图案密码连接线
     *
     * @param canvas
     * @param hitList
     * @param cellBeanList
     * @param endX
     * @param endY
     * @param isError
     */
    public abstract void draw(@NonNull Canvas canvas,
                              @Nullable List<Integer> hitList,
                              @NonNull List<CellBean> cellBeanList,
                              float endX,
                              float endY,
                              boolean isError);

    public AbsLockerLinkedLineView setNormalColor(int hitColor) {
        mHitColor = hitColor;
        return this;
    }


    public AbsLockerLinkedLineView setErrorColor(int errorColor) {
        mErrorColor = errorColor;
        return this;
    }


    public AbsLockerLinkedLineView setLineWidth(float lineWidth) {
        mLineWidth = lineWidth;
        return this;
    }

    protected @ColorInt int getColor(boolean isError) {
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
