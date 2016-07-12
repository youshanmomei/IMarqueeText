package org.qc.imarqueetext.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by admin on 2016/7/12.
 */
public class MarqueeText extends TextView implements Runnable {

    private int currentScrollX;//current scroll postion
    private boolean isStop = false;
    private int textWidth;
    private boolean isMeasure = false;
    private boolean scrollLft = false;


    public MarqueeText(Context context) {
        super(context);
    }

    public MarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isMeasure) {
            getTextWidth();
            isMeasure = true;
        }
    }

    private void getTextWidth() {
        Paint paint = this.getPaint();
        String str = this.getPaint().toString();
        textWidth = (int) paint.measureText(str);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        this.isMeasure = false;
    }

    @Override
    public void run() {
        if (scrollLft) {
            scrollLeft();
        }else{
            scrollRight();
        }

    }

    private void scrollLeft() {
        currentScrollX += 1;//rolling speed
        scrollTo(currentScrollX, 0); //left+; up+; right-; bottom-;
        if (isStop) {
            return;
        }
        if (getScrollX() >= textWidth) {
            scrollTo(-this.getWidth(), 0);
            currentScrollX = -this.getWidth();
        }
        postDelayed(this, 10);
    }

    private void scrollRight() {
        currentScrollX -= 1;//rolling speed
        scrollTo(currentScrollX, 0); //left+; up+; right-; bottom-;
        if (isStop) {
            return;
        }
        if (getScrollX() <= -getMeasuredWidth()) {
            scrollTo(0, 0);
            currentScrollX = 0;
        }
        postDelayed(this, 10);
    }

    public void startScroll(){
        isStop = false;
        this.removeCallbacks(this);
        post(this);
    }

    public void stopScroll(){
        currentScrollX = 0;
        isStop = true;
    }

    public void startFor0(){
        currentScrollX = 0;
        startScroll();
    }
}
