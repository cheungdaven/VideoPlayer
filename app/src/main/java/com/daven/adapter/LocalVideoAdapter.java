package com.daven.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daven.base.Video;
import com.daven.util.CommonUtil;
import com.daven.videoplayer.R;
import com.daven.videoplayer.VideoPlayerActivity;

import java.util.List;

/**
 * Created by Daven on 24/09/2016.
 */
public class LocalVideoAdapter extends RecyclerView.Adapter<LocalVideoAdapter.ViewHolder> {

    private List<Video> mVideos;
    private Context mContext;
    private View mCardView;

    public LocalVideoAdapter(Context context, List<Video> videos){
        mContext = context;
        mVideos = videos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        mCardView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_video_card,viewGroup, false);
        return new ViewHolder(mCardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        Video v = mVideos.get(i);
        viewHolder.mTextView.setText(v.mTitle);
        viewHolder.mImageView.setImageBitmap(CommonUtil.getBitmapFromPath(v.getThumbnailPath()));
    }

    @Override
    public int getItemCount(){
        return mVideos == null ? 0 : mVideos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener{
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(View v){
            super(v);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
            mTextView = (TextView)v.findViewById(R.id.video_title);
            mImageView = (ImageView)v.findViewById(R.id.video_icon);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(getAdapterPosition(),v);
        }

        @Override
        public boolean onLongClick(View v) {
            mClickListener.onItemLongClick(getAdapterPosition(),v);
            return false;
        }
    }

    //Click listener for RecyclerView Item
    public interface ClickListener{
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

    private static ClickListener mClickListener;

    public void setOnItemClickListener(ClickListener clickListener){
        mClickListener = clickListener;
    }


}
