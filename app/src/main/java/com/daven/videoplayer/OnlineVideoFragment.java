package com.daven.videoplayer;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daven.adapter.OnlineViewFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnlineVideoFragment extends Fragment {
    private static final String TAG = "OnlineVideoFragment";

    private ViewPager mViewPager;
    private List<Fragment> fragments;
    private OnlineViewFragmentAdapter onlineViewFragmentAdapter;
    private TextView tvTitle,movieTitle,cartoonTitle;
    private List<TextView> mTitles = new ArrayList<TextView>();

    public OnlineVideoFragment() {
        // Required empty public constructor
        fragments = new ArrayList<Fragment>(){{
            add(new TVFragment());
            add(new MoviesFragment());
            add(new CartoonFragment());
        }};

        };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        onlineViewFragmentAdapter = new OnlineViewFragmentAdapter(getChildFragmentManager(),fragments);
        View v = inflater.inflate(R.layout.online_videos,null);

        tvTitle = (TextView)v.findViewById(R.id.id_tv_tab) ;
        movieTitle =(TextView)v.findViewById(R.id.id_movie_tab);
        cartoonTitle =(TextView)v.findViewById(R.id.id_cartoon_tab);
        mTitles.clear();
        mTitles.add(tvTitle);
        mTitles.add(movieTitle);
        mTitles.add(cartoonTitle);

        mViewPager = (ViewPager)v.findViewById(R.id.online_videos_viewpager);
        mViewPager.setAdapter(onlineViewFragmentAdapter);

        mViewPager.addOnPageChangeListener(new TabOnPageChangeListener());
        Log.d(TAG,"test onCreateView");
        return v;
    }

    public class TabOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            resetTextView();
            mTitles.get(position).setTextColor(getResources().getColor(R.color.colorPrimary));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void resetTextView() {
        for(TextView t:mTitles){
            t.setTextColor(Color.BLACK);
        }
    }
}
