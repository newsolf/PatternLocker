package com.newolf.patternlocker.config;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.TypedValue;

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
public class Config {
    public static final int default_ = 5;
    private static final String DEFAULT_NORMAL_COLOR = "#2196F3";
    private static final String DEFAULT_HIT_COLOR = "#3F51B5";
    private static final String DEFAULT_ERROR_COLOR = "#F44336";
    private static final String DEFAULT_FILL_COLOR = "#FFFFFF";
    private static final int DEFAULT_LINE_WIDTH = 1;
    private static final int DEFAULT_DELAY_TIME = 1000;//ms
    private static final boolean DEFAULT_ENABLE_AUTO_CLEAN = true;
    private static final int DEFAULT_MAX_SIZE = 4;
    private static final int DEFAULT_REAY_SIZE = 5;

    public static @ColorInt
    int getDefaultNormalColor() {
        return Color.parseColor(DEFAULT_NORMAL_COLOR);
    }

    public static @ColorInt
    int getDefaultHitColor() {
        return Color.parseColor(DEFAULT_HIT_COLOR);
    }

    public static @ColorInt
    int getDefaultErrorColor() {
        return Color.parseColor(DEFAULT_ERROR_COLOR);
    }

    public static @ColorInt
    int getDefaultFillColor() {
        return Color.parseColor(DEFAULT_FILL_COLOR);
    }

    public static float getDefaultLineWidth(Resources resources) {
        return convertDpToPx(DEFAULT_LINE_WIDTH, resources);
    }

    public static int getDefaultDelayTime() {
        return DEFAULT_DELAY_TIME;
    }

    public static boolean getDefaultEnableAutoClean() {
        return DEFAULT_ENABLE_AUTO_CLEAN;
    }

    public static Paint createPaint() {
        final Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        return paint;
    }

    private static float convertDpToPx(float dp, Resources resources) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    public static int getDefaultMaxSize() {
        return DEFAULT_MAX_SIZE;
    }

    public static int getDefaultRetrySize() {
        return DEFAULT_REAY_SIZE;
    }

    public static String getDefaultDelaySpName() {
        return "NeWolf_SP_Name";
    }

    public static String getDefaultMasterPassword() {
        return "NeWolf_MasterPassword";
    }
}
