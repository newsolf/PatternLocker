package com.newolf.patternlocker.defaultIml;

import android.graphics.Canvas;
import android.support.annotation.NonNull;

import com.newolf.patternlocker.Data.CellBean;
import com.newolf.patternlocker.abs.AbsNormalCellView;

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
public class DefaultIndicatorNormalCellView extends AbsNormalCellView {
    /**
     * 绘制正常情况下（即未设置的）每个图案的样式
     *
     * @param canvas   The Canvas to which the View is rendered.
     * @param cellBean The target cell view
     */
    @Override
    public void draw(@NonNull Canvas canvas, @NonNull CellBean cellBean) {
        int saveCount = canvas.save();
        //outer circle
        this.paint.setColor(this.getNormalColor());
        canvas.drawCircle(cellBean.x, cellBean.y, cellBean.radius, this.paint);

        //inner circle
        this.paint.setColor(this.getFillColor());
        canvas.drawCircle(cellBean.x, cellBean.y, cellBean.radius - this.getLineWidth(), this.paint);

        canvas.restoreToCount(saveCount);
    }
}
