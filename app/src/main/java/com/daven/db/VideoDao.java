package com.daven.db;

import android.content.Context;

import com.daven.base.Video;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daven on 24/09/2016.
 */
public class VideoDao {

    private Context context;
    private Dao<Video, Integer> videoDaoOperation;
    private VideoDatabaseHelper helper;

    public VideoDao(Context context){
        this.context = context;
        try{
            helper = VideoDatabaseHelper.getInstance(context);
            videoDaoOperation = helper.getDao(Video.class);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void add(Video video){
        try{
            videoDaoOperation.create(video);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(Video video){
        try{
            videoDaoOperation.delete(video);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Video> getAllVideos(){
        List<Video> videos = new ArrayList<Video>();
        try{
            videos = videoDaoOperation.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return videos;
    }

    public Video getVideoById(int i){
        Video video = null;
        try{
            video = videoDaoOperation.queryForId(i);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return video;
    }

    public int getCount(){
        long count = 0;
        try{
            count = videoDaoOperation.countOf();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (int)count;
    }

}
