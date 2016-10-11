package com.daven.videoplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daven.util.CommonUtil;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //CommonUtil.setFullScreen(this);
        //设置沉浸式状态栏
        CommonUtil.setImmersiveStatusBar(this,R.color.actionbar_bg);
        setContentView(R.layout.activity_about);
    }
}
