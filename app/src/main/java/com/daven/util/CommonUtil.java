package com.daven.util;

import android.app.Activity;
import android.content.res.Configuration;

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
}
