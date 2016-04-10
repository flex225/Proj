package com.yantur.proj;

import android.graphics.Bitmap;

/**
 * Created by Artur on 4/9/2016.
 */
public class Pic {
    private Bitmap bitmap;
    private float x;
    private float y;
    private int height;
    private int width;

    public Pic(Bitmap bitmap, float x, float y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.height = 300;
        this.width = 300;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public boolean touchInPic(float touchX, float touchY) {
        if((touchX >= x && touchX <= x + width)
                && (touchY >= y && touchY <= y + height)) {
            return true;
        }
        return false;
    }
}
