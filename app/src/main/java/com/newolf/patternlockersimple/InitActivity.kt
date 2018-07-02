package com.newolf.patternlockersimple

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_init.*

class InitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)
        btnSetting.setOnClickListener(View.OnClickListener { startActivity(Intent(this,SettingActivity::class.java)) })
        btnChecking.setOnClickListener(View.OnClickListener { startActivity(Intent(this,CheckingActivity::class.java)) })
    }
}
