package com.daven.videoplayer;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

public class EntryActivity extends AppCompatActivity {

    private static final int mTabNumbers = 2;
    private FragmentTabHost mFragmentTabHost;
    private String mTexts[] = { "本地视频", "在线视频" };
    private Class mFragmentArray[] = {LocalVideoFragment.class,OnlineVideoFragment.class};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

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
