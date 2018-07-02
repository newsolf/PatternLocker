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
public abstract class AbsNormalCellView {
    private  @ColorInt
    int mNormalColor;
    private @ColorInt
    int mFillColor;
    private float mLineWidth;

    protected Paint paint;

    public AbsNormalCellView() {
        this.paint = Config.createPaint();
        this.paint.setStyle(Paint.Style.FILL);
    }

    /**
     * 绘制正常情况下（即未设置的）每个图案的样式
     *
     * @param canvas   The Canvas to which the View is rendered.
     * @param cellBean The target cell view
     */
    public abstract void draw(@NonNull Canvas canvas, @NonNull CellBean cellBean);

    public AbsNormalCellView setNormalColor(int normalColor) {
        mNormalColor = normalColor;
        return this;
    }

    public AbsNormalCellView setFillColor(int fillColor){
        mFillColor = fillColor;
        return this;
    }

    public AbsNormalCellView setLineWidth(float lineWidth) {
        mLineWidth = lineWidth;
        return this;
    }



    public float getLineWidth() {
        return mLineWidth;
    }

    public int getFillColor() {
        return mFillColor;
    }

    public int getNormalColor() {
        return mNormalColor;
    }
}
