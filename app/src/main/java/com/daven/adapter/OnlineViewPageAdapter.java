package com.daven.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daven.videoplayer.WebViewActivity;

/**
 * Created by Daven on 6/10/2016.
 */
public class OnlineViewPageAdapter extends PagerAdapter {

    ImageView[] imageViews;
    private Context mContext;

    public OnlineViewPageAdapter(ImageView[] imageViews){
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return imageViews.length;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) imageViews[position]);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews[position]);
        mContext = imageViews[position].getContext().getApplicationContext();
        final Intent intent = new Intent(mContext,WebViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//it is necessary
        imageViews[position].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(intent);
            }
        });
        return imageViews[position];
    }
}
