package com.example.mikhail.cubike.utily;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

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

    public static Bitmap getRoundedCroppedMarker(Bitmap bitmap,int radius,Bitmap bage){
        Bitmap finalBitmap;
        if (bitmap.getWidth()!=radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap,radius,radius,false);
        else
            finalBitmap = bitmap;

        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0,0,finalBitmap.getWidth(),finalBitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(finalBitmap.getWidth() / 2 + 0.7f,
                finalBitmap.getHeight() / 2 + 0.7f,
                finalBitmap.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap getRoundedCroppedBitmap(Bitmap bitmap,int radius){
        Bitmap finalBitmap;
        if (bitmap.getWidth()!=radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap,radius,radius,false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0,0,finalBitmap.getWidth(),finalBitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0,0,0,0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(finalBitmap.getWidth() / 2 + 0.7f,
                finalBitmap.getHeight() / 2 + 0.7f,
                finalBitmap.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);

        return output;
    }

    public static byte[] getBytesFromBitmap(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imageInByte[] = stream.toByteArray();
        return imageInByte;
    }

    public static byte[] getBytesFromDrawable(Drawable image) {
        Bitmap bitmap = ((BitmapDrawable) image).getBitmap();
        byte imageInByte[] = getBytesFromBitmap(bitmap);
        return imageInByte;
    }
}
