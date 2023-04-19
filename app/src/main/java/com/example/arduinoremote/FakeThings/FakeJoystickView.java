package com.example.arduinoremote.FakeThings;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import com.example.arduinoremote.R;

public class FakeJoystickView extends androidx.appcompat.widget.AppCompatImageView {
    /** realises fake joystick in remote-making screen
        object of this class have drag and scale can be dragged and scaled
     **/
    ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    static int lastId = 0;

    float x,y;
    float dx,dy;

    public FakeJoystickView(@NonNull Context context) {
        super(context);
        this.setId(Integer.parseInt("@+id/joystick"+Integer.toString(lastId)));
        lastId++;
        this.setLayoutParams(new ViewGroup.LayoutParams(150,150));
        this.setImageResource(R.drawable.joystick);
        this.setScaleType(ScaleType.FIT_CENTER);

    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);
        if (ev.getAction()==MotionEvent.ACTION_DOWN){
            x = ev.getX();
            y = ev.getY();
        }
        if (ev.getAction()==MotionEvent.ACTION_MOVE){
            dx = ev.getX()-x;
            dy = ev.getY()-y;
        }
        invalidate();

        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
//        ViewParent parent = this.getParent();
        super.onDraw(canvas);
        canvas.scale(mScaleFactor, mScaleFactor);
//        this.setScaleY(mScaleFactor);
//        this.setScaleX(mScaleFactor);
//        this.setX(this.getX()+dx);
//        this.setY(this.getY()+dy);
        canvas.save();
//        canvas.restore();
    }
    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();

            return true;
        }
    }

}
