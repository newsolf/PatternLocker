package com.newolf.patternlocker.defaultIml;

import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.newolf.patternlocker.Data.CellBean;
import com.newolf.patternlocker.abs.AbsLockerLinkedLineView;

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
public class DefaultLockerLinkedLineView extends AbsLockerLinkedLineView {
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
    @Override
    public void draw(@NonNull Canvas canvas, @Nullable List<Integer> hitList, @NonNull List<CellBean> cellBeanList, float endX, float endY, boolean isError) {
        if (hitList == null || hitList.isEmpty() || cellBeanList.isEmpty()) {
            return;
        }

        final int saveCount = canvas.save();

        final Path path = new Path();

        CellBean first = cellBeanList.get(hitList.get(0));
        path.moveTo(first.x, first.y);
        for (int i = 1; i < hitList.size(); i++) {
            CellBean c = cellBeanList.get(hitList.get(i));
            path.lineTo(c.x, c.y);
        }

        if (((endX != 0) || (endY != 0)) && (hitList.size() < 9)) {
            path.lineTo(endX, endY);
        }

        this.paint.setColor(this.getColor(isError));
        this.paint.setStrokeWidth(this.getLineWidth());
        canvas.drawPath(path, this.paint);

        canvas.restoreToCount(saveCount);

    }
}
