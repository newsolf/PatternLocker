package com.newolf.patternlocker.Data;

import java.util.ArrayList;

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
public class CellFactory {
    private final int width;
    private final int height;
    private final ArrayList<CellBean> cellBeanList;

    public CellFactory(int width, int height) {
        this.width = width;
        this.height = height;
        this.cellBeanList = new ArrayList<>();
        this.create();
    }

    private void create() {
        final float pWidth = this.width / 8f;
        final float pHeight = this.height / 8f;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.cellBeanList.add(new CellBean(
                        (i * 3 + j),
                        (j * 3 + 1) * pWidth,
                        (i * 3 + 1) * pHeight,
                        pWidth));
            }
        }
    }

    public ArrayList<CellBean> getCellBeanList() {
        return cellBeanList;
    }
}
