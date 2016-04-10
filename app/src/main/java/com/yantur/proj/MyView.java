package com.yantur.proj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur on 4/4/2016.
 */
public class MyView extends View {

    private Bitmap currentBitmap;
    private Pic currentPic;
    private List<Pic> pics;
    private float touchX = 0, touchY = 0;

    public MyView(Context context, AttributeSet attr) {
        super(context, attr, R.layout.custom_row);
        pics = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // if (bitmaps != null) {
        for (Pic pic : pics) {
            pic.setBitmap(Bitmap.createScaledBitmap(pic.getBitmap(), pic.getWidth(), pic.getHeight(), true));
            canvas.drawBitmap(pic.getBitmap(), pic.getX(), pic.getY(), null);
        }
        //  }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = pics.size() - 1; i >= 0; i--) {
                    if (pics.get(i).touchInPic(event.getX(), event.getY())) {
                        currentPic = pics.get(i);
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentPic != null) {
                    currentPic.setX(event.getX());
                    currentPic.setY(event.getY());
                    invalidate();
                }
                return false;
            case MotionEvent.ACTION_UP:
                if (currentBitmap != null) {
                    Pic currentPic = new Pic(currentBitmap, event.getX(), event.getY());
                    pics.add(currentPic);
                    Log.d("ART", String.valueOf(pics.size()));
                    invalidate();
                }
                break;
        }
        return true;
    }

    public void setCurrentBitmap(Bitmap currentBitmap) {

        this.currentBitmap = currentBitmap;
    }
}
