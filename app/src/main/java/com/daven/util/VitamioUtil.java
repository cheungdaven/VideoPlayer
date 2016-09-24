package com.daven.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

import io.vov.vitamio.ThumbnailUtils;
import io.vov.vitamio.provider.MediaStore;

/**
 * Created by Daven on 23/09/2016.
 */
public class VitamioUtil {
    private static final String TAG = "VitamioUtil";
    public static void checkVitamioLib(Activity context){
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(context))
            return;
    }

    public static File createThumbnails(Context context, File f){
        File thumnail = null;
        try{
        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(context, f.getAbsolutePath(), MediaStore.Video.Thumbnails.MINI_KIND);
        if (bitmap == null) {
            //fail
            bitmap = Bitmap.createBitmap(
                    ThumbnailUtils.TARGET_SIZE_MINI_THUMBNAIL_WIDTH,
                    ThumbnailUtils.TARGET_SIZE_MINI_THUMBNAIL_HEIGHT,
                    Bitmap.Config.RGB_565);
            Log.e(TAG, "batchBuildThumbnail createBitmap faild : " + f.getAbsolutePath());
        }

        //缩略图
        bitmap = ThumbnailUtils.extractThumbnail(bitmap,
                ThumbnailUtils.TARGET_SIZE_MICRO_THUMBNAIL_WIDTH,
                ThumbnailUtils.TARGET_SIZE_MICRO_THUMBNAIL_HEIGHT,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        if (bitmap != null) {
            thumnail = new File(context.getExternalCacheDir(), f.getName() + ".jpg");
            FileOutputStream iStream = new FileOutputStream(thumnail);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, iStream);
            iStream.close();
        }

        if (bitmap != null)
            bitmap.recycle();

        }catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return thumnail;
    }

}
