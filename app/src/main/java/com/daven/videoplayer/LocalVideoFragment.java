package com.daven.videoplayer;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daven.adapter.LocalVideoAdapter;
import com.daven.base.Constant;
import com.daven.base.Video;
import com.daven.db.VideoDao;
import com.daven.util.CommonUtil;
import com.daven.util.VitamioUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.ThumbnailUtils;
import io.vov.vitamio.Vitamio;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalVideoFragment extends Fragment {
    private final static String TAG = "LocalVideoFragment";
    private String mSDRoot = "";
    private RecyclerView mRecylerView;
    private LocalVideoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;
    private List<Video> mVideos = new ArrayList<Video>();
    private VideoDao mVideoDao;
    private SharedPreferences mPrefrence ;

    public LocalVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        View view = inflater.inflate(R.layout.local_videos, container, false);
        mVideoDao = new VideoDao(mContext);

        //get video path
        mSDRoot = Environment.getExternalStorageDirectory().getPath();

        //Setting RecyclerView
        mRecylerView = (RecyclerView) view.findViewById(R.id.local_video_list);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecylerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecylerView.setLayoutManager(mLayoutManager);

        // scan sd card
        //first time or not
        mPrefrence = mContext.getSharedPreferences(Constant.FIRST_TIME, Context.MODE_PRIVATE);

        Log.d(TAG,"mPrefrence。。="+mPrefrence.getBoolean(Constant.FIRST_TIME_LABEL,false));
        if (mPrefrence != null) {
            if( mPrefrence.getBoolean(Constant.FIRST_TIME_LABEL,false)){

                Log.d(TAG,"mPrefrence="+mPrefrence.getBoolean(Constant.FIRST_TIME_LABEL,false));

                new ScanVideoTask().execute();
                mPrefrence.edit().putBoolean(Constant.FIRST_TIME_LABEL,false).commit();
            }
        }

        mVideos = mVideoDao.getAllVideos();

        Log.d(TAG,"mVideos="+mVideos.size());

        // specify an adapter
        final Intent intent = new Intent(mContext, VideoPlayerActivity.class);
        mAdapter = new LocalVideoAdapter(mContext, mVideoDao);
        mAdapter.setOnItemClickListener(new LocalVideoAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                intent.putExtra("path",mVideos.get(position).getPath());
                mContext.startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                intent.putExtra("path",mVideos.get(position).getPath());
                mContext.startActivity(intent);
            }
        });
        mRecylerView.setAdapter(mAdapter);

        return view;
    }

    //scan local videos
    private class ScanVideoTask extends AsyncTask<Void,File,Void>{

        @Override
        protected Void doInBackground(Void...params){
            findAllVideos(Environment.getExternalStorageDirectory());
            return null;
        }

        @Override
        protected void onProgressUpdate(File...values){
           //Log.d(TAG,"video="+values[0].getPath());
            //Log.d(TAG,"video="+mContext.getFilesDir());
            Video v = new Video();
            v.setTitle(values[0].getName());//name of video
            v.setPath(values[0].getPath());//path of video
            //Log.d(TAG,"video size="+(((int)values[0].length())/1024));
            v.setSize(((int)values[0].length())/(1024*1024));//size of video

            v.setThumbnailPath(values[1].getPath());
            //Log.d(TAG,"video="+values[1].getPath());

            mVideoDao.add(v);

            mAdapter.notifyDataSetChanged();
        }

        public void findAllVideos(File f){
            if(f != null && f.exists() && f.isDirectory()){
                File[] files = f.listFiles();
                if(files != null){
                    for(File file: files){
                        if(file.isDirectory()){
                            findAllVideos(file);
                        }else if(file.exists() && file.canRead() && CommonUtil.isVideo(file)){
                            //use this method to cause onProgressUpdate to be notified
                            publishProgress(file, VitamioUtil.createThumbnails(mContext,file));
                        }
                    }
                }
            }
        }
    }

}
