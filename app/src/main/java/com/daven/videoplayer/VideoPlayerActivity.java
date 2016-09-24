/*********************************************************************************
 ** Copyright (C), 2008-2015, *** Comm Corp., Ltd
 ** All rights reserved.
 **
 ** File: - VideoPlayerActivity.java
 ** Description: Used for play video
 **
 ** Version: 1.0
 ** Date: 2016-09-23
 ** Author: Daven
 **
 ** ------------------------------- Revision History: ----------------------------
 ** <author>                        <date>       <version>   <desc>
 ** ------------------------------------------------------------------------------
 ** Daven                           2016-09-23   1.0         Create this moudle
 ********************************************************************************/

package com.daven.videoplayer;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.daven.util.CommonUtil;
import com.daven.util.VitamioUtil;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoPlayerActivity extends AppCompatActivity {

    private final static String TAG = "TEST";

    VideoView mVideoView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(CommonUtil.isLandscape(this)){
            //Full Screen
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            if (getSupportActionBar() != null){
                getSupportActionBar().hide();
            }
        }


        setContentView(R.layout.activity_video_player);

        VitamioUtil.checkVitamioLib(this);
        mVideoView = (VideoView)findViewById(R.id.vv);

        String path = getIntent().getStringExtra("path");
        Log.i(TAG,"PATH="+path);


        playVideo(path);
    }

    private void playVideo(String path){

        mVideoView.setVideoPath(path);

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
            }
        });

        mVideoView.setMediaController(new MediaController(this));
    }
}
