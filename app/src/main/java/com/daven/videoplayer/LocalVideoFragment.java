package com.daven.videoplayer;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daven.adapter.LocalVideoAdapter;
import com.daven.base.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalVideoFragment extends Fragment {

    private String mSDRoot = "";
    private RecyclerView mRecylerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;

    public LocalVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        View view = inflater.inflate(R.layout.local_videos, container, false);

        //get video path
        mSDRoot = Environment.getExternalStorageDirectory().getPath();
        String path = mSDRoot + "/EP29.mp4";

        //play button
        Button btn = (Button)view.findViewById(R.id.play_test);
        final Intent intent = new Intent(mContext, VideoPlayerActivity.class);
        intent.putExtra("path",path);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        //Setting RecyclerView
        mRecylerView = (RecyclerView) view.findViewById(R.id.local_video_list);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecylerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecylerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new LocalVideoAdapter(mContext, getVideo());
        mRecylerView.setAdapter(mAdapter);

        return view;
    }

    public List<Video> getVideo(){
        List<Video> vs = new ArrayList<Video>();
        String[] names = { "朱茵", "张柏芝", "张敏", "巩俐", "黄圣依", "赵薇", "莫文蔚", "如花" };

        for(int i=0; i < names.length; i++){
            Video v = new Video();
            v.setmTitle(names[i]);
            vs.add(v);
        }

        return vs;
    }

}
