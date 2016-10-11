package com.daven.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;

/**
 * Created by Daven on 23/09/2016.
 */
public class CommonUtil {

    public static boolean isLandscape(Activity activity) {

        Configuration mConfiguration = activity.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation ; //获取屏幕方向
        if(ori == mConfiguration.ORIENTATION_LANDSCAPE){
            return true;
        }else if(ori == mConfiguration.ORIENTATION_PORTRAIT){
            return false;
        }
        return false;
    }

    public static boolean isVideo(File file){
        String name = file.getName();
        int i = name.indexOf('.');
        if (i != -1) {
            name = name.substring(i);
            if (name.equalsIgnoreCase(".mp4")
                    || name.equalsIgnoreCase(".3gp")
                    || name.equalsIgnoreCase(".wmv")
                    || name.equalsIgnoreCase(".ts")
                    || name.equalsIgnoreCase(".rmvb")
                    || name.equalsIgnoreCase(".mov")
                    || name.equalsIgnoreCase(".m4v")
                    || name.equalsIgnoreCase(".avi")
                    //|| name.equalsIgnoreCase(".m3u8")
                    //|| name.equalsIgnoreCase(".3gpp")
                    //|| name.equalsIgnoreCase(".3gpp2")
                    || name.equalsIgnoreCase(".mkv")
                    || name.equalsIgnoreCase(".flv")
                    //|| name.equalsIgnoreCase(".divx")
                    || name.equalsIgnoreCase(".f4v")
                    || name.equalsIgnoreCase(".rm")
                    || name.equalsIgnoreCase(".asf")
                   // || name.equalsIgnoreCase(".ram")
                    || name.equalsIgnoreCase(".mpg")
                    //|| name.equalsIgnoreCase(".v8")
                    || name.equalsIgnoreCase(".swf")
                    //|| name.equalsIgnoreCase(".m2v")
                    //|| name.equalsIgnoreCase(".asx")
                    //|| name.equalsIgnoreCase(".ra")
                    //|| name.equalsIgnoreCase(".ndivx")
                    || name.equalsIgnoreCase(".xvid")) {
                return true;
            }
        }

        return false;
    }

    public static Bitmap getBitmapFromPath(String path){

        Bitmap mp = BitmapFactory.decodeFile(path);

        return mp;
    }

    public static void setImmersiveStatusBar(Activity activity, int statusbarcolor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //状态栏透明 需要在创建SystemBarTintManager 之前调用。
            Window win = activity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (true) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        //使StatusBarTintView 和 actionbar的颜色保持一致，风格统一。
        tintManager.setStatusBarTintResource(statusbarcolor);
        // 设置状态栏的文字颜色
        tintManager.setStatusBarDarkMode(false, activity);

    }

    //set to full screen
    public static void setFullScreen(Activity activity){
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (((AppCompatActivity)activity).getSupportActionBar() != null){
            ((AppCompatActivity)activity).getSupportActionBar().hide();
        }
    }

    public static String getVideoSize(int size){
        String res;
        int temp = size/(1024*1024);
        if(temp <= 0){
            res = size/1024+" KB";
        } else{
            res = temp+" MB";
        }
        return res;
    }



}
