package tech.cyang.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

/**
 * Created by cy101 on 2016/7/25.
 */

public class GameView extends GridLayout{

    public GameView(Context context) {
        super(context);
        initGameViwe();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameViwe();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameViwe();
    }

    public void initGameViwe(){
        setOnTouchListener(new View.OnTouchListener() {

            private float startX,startY,offsetX,offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startX = event.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        Log.e("TAG","offsetX"+offsetX);
                        Log.e("TAG","offsetY"+offsetY);

                        if (Math.abs(offsetX)>Math.abs(offsetY)){
                            if (offsetX<-200){
                                Log.e("this","left");
                            }
                            else if (offsetX>200){
                                Log.e("this","right");
                            }
                        }else {
                            if (offsetY<-200){
                                Log.e("this","down");
                            }
                            else if (offsetY>200){
                                Log.e("this","up");
                            }
                        }
                        break;
                }
                return true;
            }
        });

    }

    private void swipeUp(){

    }
    private void swipeDown(){

    }
    private void swipeLeft(){

    }
    private void swipeRight(){

    }
}


