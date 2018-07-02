package com.newolf.patternlocker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.newolf.patternlocker.Data.CellBean;
import com.newolf.patternlocker.Data.CellFactory;
import com.newolf.patternlocker.abs.AbsHitCellView;
import com.newolf.patternlocker.abs.AbsLockerLinkedLineView;
import com.newolf.patternlocker.abs.AbsNormalCellView;
import com.newolf.patternlocker.config.Config;
import com.newolf.patternlocker.defaultIml.DefaultLockerLinkedLineView;
import com.newolf.patternlocker.defaultIml.DefaultLockerNormalCellView;
import com.newolf.patternlocker.defaultIml.RippleLockerHitCellView;
import com.newolf.patternlocker.interfaces.OnPatternChangeListener;

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
public class PatternLockerView extends View {

    private @ColorInt
    int normalColor;
    private @ColorInt
    int hitColor;
    private @ColorInt
    int errorColor;
    private @ColorInt
    int fillColor;
    private float lineWidth;
    private boolean enableAutoClean;
    private List<Integer> hitList;
    private AbsNormalCellView mNormalCellView;
    private AbsLockerLinkedLineView mLinkedLineView;
    private AbsHitCellView mHitCellView;
    private String TAG = getClass().getSimpleName();
    private ArrayList<CellBean> cellBeanList;
    private float endX;
    private float endY;
    private boolean isError;
    private boolean logEnable;
    private int hitSize;
    private OnPatternChangeListener listener;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public PatternLockerView(Context context) {
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
     * @see (Context, AttributeSet, int)
     */
    public PatternLockerView(Context context, @Nullable AttributeSet attrs) {
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
     * @see View(Context, AttributeSet,int)
     */
    public PatternLockerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initAttrs(context, attrs, defStyleAttr);
        initData();
    }



    public PatternLockerView setLogEnable(boolean enable){
        logEnable = enable;
        return this;
    }

    public void setOnPatternChangedListener(OnPatternChangeListener onPatternChangeListener) {
        listener = onPatternChangeListener;
    }


    public int getNormalColor() {
        return normalColor;
    }

    public PatternLockerView setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        return this;
    }

    public int getHitColor() {
        return hitColor;
    }

    public PatternLockerView setHitColor(int hitColor) {
        this.hitColor = hitColor;
        return this;
    }

    public int getErrorColor() {
        return errorColor;
    }

    public PatternLockerView setErrorColor(int errorColor) {
        this.errorColor = errorColor;
        return this;
    }

    public int getFillColor() {
        return fillColor;
    }

    public PatternLockerView setFillColor(int fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public PatternLockerView setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        return this;
    }

    public AbsLockerLinkedLineView getLinkedLineView() {
        return mLinkedLineView;
    }

    public PatternLockerView setLinkedLineView(AbsLockerLinkedLineView linkedLineView) {
        mLinkedLineView = linkedLineView;
        return this;
    }

    public AbsNormalCellView getNormalCellView() {
        return mNormalCellView;
    }

    public PatternLockerView setNormalCellView(AbsNormalCellView normalCellView) {
        mNormalCellView = normalCellView;
        return this;
    }

    public AbsHitCellView getHitCellView() {
        return mHitCellView;
    }

    public PatternLockerView setHitCellView(AbsHitCellView hitCellView) {
        this.mHitCellView = hitCellView;
        return this;
    }

    private void buildWithDefaultStyle() {
        this.setNormalCellView(new DefaultLockerNormalCellView()
                .setNormalColor(this.getNormalColor())
                .setFillColor(this.getFillColor())
                .setLineWidth(this.getLineWidth())
        ).setHitCellView(new RippleLockerHitCellView()
                .setHitColor(this.getHitColor())
                .setErrorColor(this.getErrorColor())
                .setFillColor(this.getFillColor())
                .setLineWidth(this.getLineWidth())
        ).setLinkedLineView(new DefaultLockerLinkedLineView()
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
            logThis("build(), hitCellView is null");
            return;
        }

        if (getLinkedLineView() == null) {
            logThis("build(), linkedLineView is null");
        }

        postInvalidate();
    }


    public void clearHitState() {
        clearHitData();
        this.isError = false;
        if (this.listener != null) {
            this.listener.onClear(this);
        }

        postInvalidate();
    }


    public void updateStatus(boolean isError) {
        this.isError = isError;
        postInvalidate();
    }



    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return super.onTouchEvent(event);
        }
        boolean isHandle = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handleActionDown(event);
                isHandle = true;
                break;
            case MotionEvent.ACTION_MOVE:
                handleActionMove(event);
                isHandle = true;
                break;
            case MotionEvent.ACTION_UP:
                handleActionUp(event);
                isHandle = true;
                break;
            default:
                break;
        }
        postInvalidate();
        return isHandle || super.onTouchEvent(event);
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
            this.cellBeanList = new CellFactory(getWidth(), getHeight())
                    .getCellBeanList();
        }
        drawLinkedLine(canvas);
        drawCells(canvas);
    }


    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PatternLockerView, defStyleAttr, 0);

        this.normalColor = ta.getColor(R.styleable.PatternLockerView_plv_color, Config.getDefaultNormalColor());
        this.hitColor = ta.getColor(R.styleable.PatternLockerView_plv_hitColor, Config.getDefaultHitColor());
        this.errorColor = ta.getColor(R.styleable.PatternLockerView_plv_errorColor, Config.getDefaultErrorColor());
        this.fillColor = ta.getColor(R.styleable.PatternLockerView_plv_fillColor, Config.getDefaultFillColor());
        this.lineWidth = ta.getDimension(R.styleable.PatternLockerView_plv_lineWidth, Config.getDefaultLineWidth(getResources()));
        this.enableAutoClean = ta.getBoolean(R.styleable.PatternLockerView_plv_enableAutoClean, Config.getDefaultEnableAutoClean());

        ta.recycle();

        this.setNormalColor(this.normalColor);
        this.setHitColor(this.hitColor);
        this.setErrorColor(this.errorColor);
        this.setFillColor(this.fillColor);
        this.setLineWidth(this.lineWidth);

    }

    private void initData() {
        this.hitList = new ArrayList<>();
        this.buildWithDefaultStyle();
    }



    private void drawLinkedLine(Canvas canvas) {
        if ((this.hitList != null) && !this.hitList.isEmpty() && (getLinkedLineView() != null)) {
            getLinkedLineView().draw(canvas,
                    this.hitList,
                    this.cellBeanList,
                    this.endX,
                    this.endY,
                    this.isError);
        }
    }

    private void drawCells(Canvas canvas) {
        if (getHitCellView() == null) {
            logThis( "drawCells(), hitCellView is null");
            return;
        }

        if (getNormalCellView() == null) {
            logThis( "drawCells(), normalCellView is null");
            return;
        }

        for (int i = 0; i < this.cellBeanList.size(); i++) {
            final CellBean item = this.cellBeanList.get(i);
            if (item.isHit) {
                getHitCellView().draw(canvas, item, this.isError);
            } else {
                getNormalCellView().draw(canvas, item);
            }
        }
    }



    private void handleActionDown(MotionEvent event) {
        //1. reset to default state
        clearHitData();

        //2. update hit state
        updateHitState(event);

        //3. notify listener
        if (this.listener != null) {
            this.listener.onStart(this);
        }
    }


    private void handleActionMove(MotionEvent event) {
        //1. update hit state
        updateHitState(event);

        //2. update end point
        this.endX = event.getX();
        this.endY = event.getY();

        //3. notify listener if needed
        final int size = this.hitList.size();
        if ((this.listener != null) && (this.hitSize != size)) {
            this.hitSize = size;
            this.listener.onChange(this, this.hitList);
        }
    }

    private void handleActionUp(MotionEvent event) {
        //1. update hit state
        updateHitState(event);
        this.endX = 0;
        this.endY = 0;

        //2. notify listener
        if (this.listener != null) {
            this.listener.onComplete(this, this.hitList);
        }

        //3. startTimer if needed
        if (this.enableAutoClean && this.hitList.size() > 0) {
            startTimer();
        }
    }


    private void clearHitData() {
        for (int i = 0; i < this.hitList.size(); i++) {
            this.cellBeanList.get(hitList.get(i)).isHit = false;
        }
        this.hitList.clear();
        this.hitSize = 0;
    }

    private void updateHitState(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();
        for (CellBean c : this.cellBeanList) {
            if (!c.isHit && c.of(x, y)) {
                c.isHit = true;
                this.hitList.add(c.id);
            }
        }
    }


    private final Runnable action = new Runnable() {
        @Override
        public void run() {
            setEnabled(true);
            clearHitState();
        }
    };




    private void startTimer() {
        setEnabled(false);
        this.postDelayed(this.action, Config.getDefaultDelayTime());
    }



    private void logThis(String msg){
        if (logEnable){
            Log.d(TAG, msg);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        this.setOnPatternChangedListener(null);
        this.removeCallbacks(this.action);
        super.onDetachedFromWindow();
    }
}
