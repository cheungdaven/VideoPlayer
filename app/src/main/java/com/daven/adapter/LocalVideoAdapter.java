package com.daven.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daven.base.Video;
import com.daven.videoplayer.R;

import java.util.List;

/**
 * Created by Daven on 24/09/2016.
 */
public class LocalVideoAdapter extends RecyclerView.Adapter<LocalVideoAdapter.ViewHolder> {

    private List<Video> mVideos;
    private Context mContext;

    public LocalVideoAdapter(Context context, List<Video> videos){
        mContext = context;
        mVideos = videos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_video_card,viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        Video v = mVideos.get(i);
        viewHolder.mTextView.setText(v.mTitle);
        //ViewHolder
    }

    @Override
    public int getItemCount(){
        return mVideos == null ? 0 : mVideos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(View v){
            super(v);
            mTextView = (TextView)v.findViewById(R.id.video_title);
            mImageView = (ImageView)v.findViewById(R.id.video_icon);
        }
    }


}
