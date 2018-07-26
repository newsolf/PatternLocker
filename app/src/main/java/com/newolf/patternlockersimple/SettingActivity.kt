package com.newolf.patternlockersimple

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.newolf.patternlocker.PatternLockerView
import com.newolf.patternlocker.defaultIml.DefaultLockerNormalCellView
import com.newolf.patternlocker.interfaces.OnPatternChangeListener
import com.newolf.patternlocker.utils.PatternHelper
import kotlinx.android.synthetic.main.activity_main.*

class SettingActivity : AppCompatActivity() {

    lateinit var patternHelper: PatternHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initHelper()
        initView()

        initListener()

    }

    private fun initHelper() {
        patternHelper = PatternHelper.Builder()
                .setApplication(this.application)

                .build()
    }

    private fun initView() {
        tvShow.setText("设置解锁图案")
        patternIndicatorView
                .setNormalCellView(DefaultLockerNormalCellView())

                .buildWithDefaultStyle()
    }

    private fun initListener() {
        patternLockerView.setOnPatternChangedListener(object : OnPatternChangeListener {
            /**
             * 开始绘制图案时（即手指按下触碰到绘画区域时）会调用该方法
             *
             * @param view
             */
            override fun onStart(view: PatternLockerView?) {

            }

            /**
             * 图案绘制改变时（即手指在绘画区域移动时）会调用该方法，请注意只有 @param hitList改变了才会触发此方法
             *
             * @param view
             * @param hitList
             */
            override fun onChange(view: PatternLockerView?, hitList: MutableList<Int>?) {

            }

            /**
             * 图案绘制完成时（即手指抬起离开绘画区域时）会调用该方法
             *
             * @param view
             * @param hitList
             */
            override fun onComplete(view: PatternLockerView, hitList: MutableList<Int>) {
                val isOk = isPatternOk(hitList)
                view.updateStatus(!isOk)
                patternIndicatorView.updateState(hitList, !isOk)
                updateMsg()
            }

            /**
             * 已绘制的图案被清除时会调用该方法
             *
             * @param view
             */
            override fun onClear(view: PatternLockerView?) {
                finishIfNeeded()
            }

        })
    }

    private fun isPatternOk(hitList: List<Int>): Boolean {
        this.patternHelper.validateForSetting(hitList)
        return this.patternHelper.isOk()
    }

    private fun updateMsg() {
        this.tvShow.setText(this.patternHelper.getMessage())
        this.tvShow.setTextColor(if (this.patternHelper.isOk())
            resources.getColor(R.color.colorPrimaryDark)
        else
            resources.getColor(R.color.colorAccent))
    }

    private fun finishIfNeeded() {
        if (this.patternHelper.isFinish()) {
            finish()
        }
    }
}
