package com.daven.videoplayer;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daven.adapter.OnlineVideoAdapter;
import com.daven.adapter.OnlineViewPageAdapter;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import com.daven.base.Constant;

/**
 * A simple {@link Fragment} subclass.
 */

public class TVFragment extends Fragment implements ViewPager.OnPageChangeListener{

    private final String TAG = this.getClass().getSimpleName();
    private static final int TV_COUNTS = Constant.TV_URLS.length;
    private ImageView[] mDotsView = new ImageView[TV_COUNTS];
    private ImageView[] mImageViews = new ImageView[TV_COUNTS];
    private ViewGroup mViewGroup;
    private Context mContext;
    private RecyclerView mRecylerView;
    private OnlineVideoAdapter mOnlineVideoAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public TVFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG," getActivity().getApplicationContext()="+getActivity());
        mContext = getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment_tv, container, false);

        //initial image array
        initVideoArray(mContext);
        //initial view pager
        initViewPager(view);
        //initial dots
        initDotsView(view);
        //initial all tv
        initAllTvs(view);

        return view;
    }

    private void initVideoArray(Context context){
        for (int i=0; i< TV_COUNTS;i++){
            mImageViews[i] = new ImageView(context);
            UrlImageViewHelper.setUrlDrawable(mImageViews[i],Constant.TV_URLS[i]);
        }
    }

    private void initViewPager(View v){
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.online_videos_tv);
        OnlineViewPageAdapter onlineViewPageAdapter = new OnlineViewPageAdapter(mImageViews);
        viewPager.setAdapter(onlineViewPageAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    private void initAllTvs(View v){
        mRecylerView = (RecyclerView) v.findViewById(R.id.online_video_tv_all);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecylerView.setHasFixedSize(true);
        // use a grid layout manager
        GridLayoutManager gridManager = new GridLayoutManager(mContext,3);
        mRecylerView.setLayoutManager(gridManager);
        mOnlineVideoAdapter = new OnlineVideoAdapter(mContext,Constant.TV_ALL_URLS,null,Constant.TV_ALL_NAME);
        mOnlineVideoAdapter.setOnItemClickListener(new OnlineVideoAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
        mRecylerView.setAdapter(mOnlineVideoAdapter);

    }

    private void initDotsView(View v){
        mViewGroup = (ViewGroup) v.findViewById(R.id.viewpager_tag_viewgroup);
        for( int i = 0; i < TV_COUNTS; i++){
            mDotsView[i] = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            //mDotsView[i].setLayoutParams(new ViewGroup.LayoutParams(20,20));
            params.setMargins(10,0,10,0);
            mDotsView[i].setLayoutParams(params);
            if(i == 0){
                mDotsView[i].setBackgroundResource(R.drawable.dot_focused);
            } else {
                mDotsView[i].setBackgroundResource(R.drawable.dot_normal);
            }
            mViewGroup.addView(mDotsView[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for( int i = 0; i < TV_COUNTS; i++){
            mDotsView[i].setBackgroundResource(R.drawable.dot_normal);
            if(position == i){
                mDotsView[i].setBackgroundResource(R.drawable.dot_focused);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
