package com.daven.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import com.daven.base.Constant;
import com.daven.util.VitamioUtil;

public class EntryActivity extends AppCompatActivity {
    private static final String TAG = "EntryActivity";
    private static final int mTabNumbers = 2;
    private FragmentTabHost mFragmentTabHost;
    private String mTexts[] = { "本地视频", "在线视频" };
    private Class mFragmentArray[] = {LocalVideoFragment.class,OnlineVideoFragment.class};
    private SharedPreferences mPrefrence ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        VitamioUtil.checkVitamioLib(this);
        //first time or not
        mPrefrence = getSharedPreferences(Constant.FIRST_TIME, Context.MODE_PRIVATE);
        Log.d(TAG,"mPrefrence null="+(mPrefrence.contains(Constant.FIRST_TIME_LABEL)));
        if (!mPrefrence.contains(Constant.FIRST_TIME_LABEL)) {
            mPrefrence.edit().putBoolean(Constant.FIRST_TIME_LABEL,true).commit();
            Log.d(TAG,"mPrefrence="+mPrefrence.getBoolean(Constant.FIRST_TIME_LABEL,false));
        }

        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this, getSupportFragmentManager(),R.id.maincontent);

        for(int i = 0; i < mTabNumbers; i++){
            TabHost.TabSpec spec = mFragmentTabHost.newTabSpec(mTexts[i]).setIndicator(getView(i));

            mFragmentTabHost.addTab(spec, mFragmentArray[i],null);
        }
    }

    private View getView(int i){

        View view = View.inflate(this, R.layout.tab_content,null);

        TextView textView = (TextView) view.findViewById(R.id.tab_text);

        textView.setText(mTexts[i]);

        return view;
    }
}
