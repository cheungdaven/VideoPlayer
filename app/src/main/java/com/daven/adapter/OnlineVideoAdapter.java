package com.daven.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daven.base.Constant;
import com.daven.base.Video;
import com.daven.db.VideoDao;
import com.daven.util.CommonUtil;
import com.daven.videoplayer.R;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.HashMap;

/**
 * Created by Daven on 24/09/2016.
 */
public class OnlineVideoAdapter extends RecyclerView.Adapter<OnlineVideoAdapter.ViewHolder> {
    private static final String TAG = "LocalVideoAdapter";
    private Context mContext;
    private View mCardView;
    private String[] mVideoIconUrls;
    private String[] mVideoUrls;
    private String[] mNames;


    public OnlineVideoAdapter(Context context, String[] icon, String[] urls,String[] name){
        mContext = context;
        this.mVideoIconUrls = icon;
        this.mVideoUrls = urls;
        this.mNames = name;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        mCardView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.online_video_card, viewGroup, false);
        return new ViewHolder(mCardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        viewHolder.mTextView.setText(mNames[i]);
        UrlImageViewHelper.setUrlDrawable(viewHolder.mImageView, mVideoIconUrls[i]);
    }

    @Override
    public int getItemCount(){
        return mVideoIconUrls.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener{
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(View v){
            super(v);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
            mTextView = (TextView)v.findViewById(R.id.online_video_title);
            mImageView = (ImageView)v.findViewById(R.id.online_video_icon);
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
