package com.newolf.patternlocker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.newolf.patternlocker.Data.CellBean;
import com.newolf.patternlocker.Data.CellFactory;
import com.newolf.patternlocker.abs.AbsHitCellView;
import com.newolf.patternlocker.abs.AbsIndicatorLinkedLineView;
import com.newolf.patternlocker.abs.AbsNormalCellView;
import com.newolf.patternlocker.config.Config;
import com.newolf.patternlocker.defaultIml.DefaultIndicatorHitCellView;
import com.newolf.patternlocker.defaultIml.DefaultIndicatorLinkedLineView;
import com.newolf.patternlocker.defaultIml.DefaultIndicatorNormalCellView;

import java.util.ArrayList;
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
public class PatternIndicatorView extends View {

    private int normalColor;
    private int fillColor;
    private int hitColor;
    private int errorColor;
    private float lineWidth;

    private boolean isError;
    private List<Integer> hitList;
    private List<CellBean> cellBeanList;

    private AbsIndicatorLinkedLineView linkedLineView;
    private AbsNormalCellView normalCellView;
    private AbsHitCellView hitCellView;
    private String TAG = getClass().getSimpleName();
    private boolean logEnable;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public PatternIndicatorView(Context context) {
        this(context, null);
    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * <p>
     * <p>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     * @see #(Context, AttributeSet, int)
     */
    public PatternIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Perform inflation from XML and apply a class-specific base style from a
     * theme attribute. This constructor of View allows subclasses to use their
     * own base style when they are inflating. For example, a Button class's
     * constructor would call this version of the super class constructor and
     * supply <code>R.attr.buttonStyle</code> for <var>defStyleAttr</var>; this
     * allows the theme's button style to modify all of the base view attributes
     * (in particular its background) as well as the Button class's attributes.
     *
     * @param context      The Context the view is running in, through which it can
     *                     access the current theme, resources, etc.
     * @param attrs        The attributes of the XML tag that is inflating the view.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     *                     the view. Can be 0 to not look for defaults.
     * @see #View(Context, AttributeSet, int)
     */
    public PatternIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    public void clearHitState() {
        clearHitData();
        this.isError = false;


        postInvalidate();
    }



    public int getNormalColor() {
        return normalColor;
    }

    public PatternIndicatorView setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        return this;
    }

    public int getFillColor() {
        return fillColor;
    }

    public PatternIndicatorView setFillColor(int fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public int getHitColor() {
        return hitColor;
    }

    public PatternIndicatorView setHitColor(int hitColor) {
        this.hitColor = hitColor;
        return this;
    }

    public int getErrorColor() {
        return errorColor;
    }

    public PatternIndicatorView setErrorColor(int errorColor) {
        this.errorColor = errorColor;
        return this;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public PatternIndicatorView setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    public AbsIndicatorLinkedLineView getLinkedLineView() {
        return linkedLineView;
    }

    public PatternIndicatorView setLinkedLineView(AbsIndicatorLinkedLineView linkedLineView) {
        this.linkedLineView = linkedLineView;
        return this;
    }

    public AbsNormalCellView getNormalCellView() {
        return normalCellView;
    }

    public PatternIndicatorView setNormalCellView(AbsNormalCellView normalCellView) {
        this.normalCellView = normalCellView;
        return this;
    }

    public AbsHitCellView getHitCellView() {
        return hitCellView;
    }

    public PatternIndicatorView setHitCellView(AbsHitCellView hitCellView) {
        this.hitCellView = hitCellView;
        return this;
    }

    public void buildWithDefaultStyle() {

        if (getNormalCellView()==null) {
            setNormalCellView(new DefaultIndicatorNormalCellView());
        }

        if (getHitCellView()==null){
            setHitCellView(new DefaultIndicatorHitCellView());
        }

        if (getLinkedLineView()==null){
            setLinkedLineView(new DefaultIndicatorLinkedLineView());
        }

        this.setNormalCellView(getNormalCellView()
                .setNormalColor(this.getNormalColor())
                .setFillColor(this.getFillColor())
                .setLineWidth(this.getLineWidth())
        ).setHitCellView(getHitCellView()
                .setErrorColor(this.getErrorColor())
                .setHitColor(this.getHitColor())
        ).setLinkedLineView(getLinkedLineView()
                .setNormalColor(this.getHitColor())
                .setErrorColor(this.getErrorColor())
                .setLineWidth(this.getLineWidth())
        ).build();
    }

    public void build() {
        if (getNormalCellView() == null) {
            logThis("build(), normalCellView is null");
            return;
        }

        if (getHitCellView() == null) {
            Log.e(TAG, "build(), hitCellView is null");
            return;
        }

        if (getLinkedLineView() == null) {
            Log.w(TAG, "build(), linkedLineView is null");
        }
        postInvalidate();
    }

    public void updateState(List<Integer> hitList, boolean isError) {
        //1. reset to default state
        if (!this.hitList.isEmpty()) {
            for (int i : this.hitList) {
                this.cellBeanList.get(i).isHit = false;
            }
            this.hitList.clear();
        }

        //2. update hit state
        if (hitList != null) {
            this.hitList.addAll(hitList);
        }
        if (!this.hitList.isEmpty()) {
            for (int i : this.hitList) {
                this.cellBeanList.get(i).isHit = true;
            }
        }

        //3. update result
        this.isError = isError;

        //4. update view
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int a = Math.min(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(a, a);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.cellBeanList == null) {
            this.cellBeanList = new CellFactory(getWidth() - getPaddingLeft() - getPaddingRight(),
                    getHeight() - getPaddingTop() - getPaddingBottom())
                    .getCellBeanList();
        }

        drawLinkedLine(canvas);
        drawCells(canvas);
    }



    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this.initAttrs(context, attrs, defStyleAttr);
        this.initData();
    }

    private void initAttrs(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PatternIndicatorView, defStyleAttr, 0);

        this.normalColor = ta.getColor(R.styleable.PatternIndicatorView_piv_color, Config.getDefaultNormalColor());
        this.fillColor = ta.getColor(R.styleable.PatternIndicatorView_piv_fillColor, Config.getDefaultFillColor());
        this.hitColor = ta.getColor(R.styleable.PatternIndicatorView_piv_hitColor, Config.getDefaultHitColor());
        this.errorColor = ta.getColor(R.styleable.PatternIndicatorView_piv_errorColor, Config.getDefaultErrorColor());
        this.lineWidth = ta.getDimension(R.styleable.PatternIndicatorView_piv_lineWidth, Config.getDefaultLineWidth(getResources()));

        ta.recycle();

    }

    private void initData() {
        this.hitList = new ArrayList<>();
        this.buildWithDefaultStyle();
    }


    private void clearHitData() {
        for (int i = 0; i < this.hitList.size(); i++) {
            this.cellBeanList.get(hitList.get(i)).isHit = false;
        }
        this.hitList.clear();
    }
    private void drawLinkedLine(Canvas canvas) {
        if (!this.hitList.isEmpty() && (this.getLinkedLineView() != null)) {
            this.getLinkedLineView().draw(canvas,
                    this.hitList,
                    this.cellBeanList,
                    this.isError);
        }
    }

    private void drawCells(Canvas canvas) {
        if (this.getHitCellView() == null) {
            logThis("drawCells(), hitCellView is null");
            return;
        }

        if (this.getNormalCellView() == null) {
            logThis("drawCells(), normalCellView is null");
            return;
        }

        for (int i = 0; i < this.cellBeanList.size(); i++) {
            CellBean item = this.cellBeanList.get(i);
            if (item.isHit) {
                this.getHitCellView().draw(canvas, item, this.isError);
            } else {
                this.getNormalCellView().draw(canvas, item);
            }
        }
    }

    private void logThis(String msg) {
        if (logEnable) {
            Log.d(TAG, msg);
        }
    }
}
