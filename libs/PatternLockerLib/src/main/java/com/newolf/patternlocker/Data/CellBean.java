package com.newolf.patternlocker.Data;

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
public class CellBean {
    public int id;

    public float x;
    public float y;
    public float radius;

    public boolean isHit;

    /**
     *
     * @param id
     * @param x The x-coordinate of the center of the cirle to be drawn
     * @param y The y-coordinate of the center of the cirle to be drawn
     * @param radius The radius of the cirle to be drawn
     */
    public CellBean(int id, float x, float y, float radius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * 是否触碰到该view
     *
     * @param x
     * @param y
     * @return
     */
    public boolean of(float x, float y) {
        final float dx = this.x - x;
        final float dy = this.y - y;
        return Math.sqrt(dx * dx + dy * dy) <= this.radius;
    }

    @Override
    public String toString() {
        return "CellBean{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                ", isHit=" + isHit +
                '}';
    }
}
