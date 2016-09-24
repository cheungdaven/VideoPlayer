package com.daven.util;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Daven on 23/09/2016.
 */
public class VitamioUtil {

    public static void checkVitamioLib(Activity context){
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(context))
            return;
    }

}
