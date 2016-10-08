package com.daven.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.daven.base.Video;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daven on 24/09/2016.
 */
public class VideoDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "video-player.db";
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private VideoDatabaseHelper(Context context){
        super(context,TABLE_NAME,null,4);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Video.class);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try{
            TableUtils.dropTable(connectionSource,Video.class,true);
            onCreate(sqLiteDatabase,connectionSource);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Singleton Instance
    private static VideoDatabaseHelper instance;

    public static synchronized VideoDatabaseHelper getInstance(Context context){
        context = context.getApplicationContext();//avoid memory leak

        if (instance == null) {//防止多次创建同步锁
            synchronized (VideoDatabaseHelper.class) {//创建同步锁
                if (instance == null)
                    instance = new VideoDatabaseHelper(context);
            }
        }
        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException{
        Dao dao = null;
        String classname = clazz.getSimpleName();

        if(daos.containsKey(classname)){
            dao = daos.get(classname);
        }
        if(dao == null){
            dao = super.getDao(clazz);
            daos.put(classname,dao);
        }
        return dao;
    }

    @Override
    public void close(){
        super.close();
        for(String key:daos.keySet()){
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
