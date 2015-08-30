package com.example.mikhail.cubike.utily;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;

/**
 * Created by Mikhail on 30.08.2015.
 */
public class UtilMethods {

    /**
     * Converts byte[] to Bitmap
     */
    public static Bitmap getBitmapFromBytes(byte[] image) {
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        Bitmap decodedImage = BitmapFactory.decodeStream(imageStream);
        return decodedImage;
    }
}
