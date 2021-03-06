package com.newolf.patternlocker.defaultIml;

import android.graphics.Canvas;
import android.support.annotation.NonNull;

import com.newolf.patternlocker.Data.CellBean;
import com.newolf.patternlocker.abs.AbsHitCellView;

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
public class RippleLockerHitCellView extends AbsHitCellView {
    /**
     * 绘制已设置的每个图案的样式
     *
     * @param canvas   The Canvas to which the View is rendered.
     * @param cellBean The target cell view
     * @param isError  If error is true else false
     */
    @Override
    public void draw(@NonNull Canvas canvas, @NonNull CellBean cellBean, boolean isError) {
        final int saveCount = canvas.save();

        this.paint.setColor(getColor(isError) & 0x14FFFFFF);
        canvas.drawCircle(cellBean.x, cellBean.y, cellBean.radius, this.paint);

        this.paint.setColor(getColor(isError) & 0x43FFFFFF);
        canvas.drawCircle(cellBean.x, cellBean.y, cellBean.radius * 2f / 3f, this.paint);

        this.paint.setColor(getColor(isError));
        canvas.drawCircle(cellBean.x, cellBean.y, cellBean.radius / 3f, this.paint);

        canvas.restoreToCount(saveCount);
    }
}
