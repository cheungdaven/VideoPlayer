package com.daven.base;

import android.widget.ImageView;

/**
 * Created by Daven on 24/09/2016.
 */
public class Video {
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String mTitle;

    public ImageView getImageView() {
        return mImageView;
    }

    public void setImageView(ImageView mImageView) {
        this.mImageView = mImageView;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int mSize) {
        this.mSize = mSize;
    }

    public int getTime() {
        return mTime;
    }

    public void setTime(int mTime) {
        this.mTime = mTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageView mImageView;
    public int mSize;
    public int mTime;
    public String description;

    public String getPath() {
        return mPath;
    }

    public void setPath(String mPath) {
        this.mPath = mPath;
    }

    public String mPath;

    public String getThumbnailPath() {
        return mThumbnailPath;
    }

    public void setThumbnailPath(String mThumbnailPath) {
        this.mThumbnailPath = mThumbnailPath;
    }

    public String mThumbnailPath;


}
