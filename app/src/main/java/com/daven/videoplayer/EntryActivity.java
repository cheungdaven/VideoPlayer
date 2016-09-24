package com.daven.videoplayer;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EntryActivity extends AppCompatActivity {

    String mSDRoot = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        mSDRoot = Environment.getExternalStorageDirectory().getPath();
        String path = mSDRoot + "/EP29.mp4";

        Button btn = (Button)findViewById(R.id.play_test);
        final Intent intent = new Intent(this, VideoPlayerActivity.class);
        intent.putExtra("path",path);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }
}
