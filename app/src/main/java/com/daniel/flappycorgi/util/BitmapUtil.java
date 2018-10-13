package com.daniel.flappycorgi.util;

import android.graphics.Bitmap;

public class BitmapUtil {
    public static Bitmap resizeBitmap(Bitmap bmp, int desiredWidth) {
        int newHeight = (int) (bmp.getHeight() / (double) bmp.getWidth() * desiredWidth);
        return Bitmap.createScaledBitmap(bmp, desiredWidth, newHeight, false);
    }
}
