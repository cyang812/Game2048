package tech.cyang.game2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cy101 on 2016/7/25.
 */

public class GameView extends GridLayout {

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

    //初始化
    public void initGameViwe(){

        setBackgroundColor(0xffbbada0);
        setColumnCount(4);

        setOnTouchListener(new View.OnTouchListener() {

            private float startX=0,startY=0,offsetX=0,offsetY=0;

            @Override
            public boolean onTouch(View v,MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //当手指按下的时候
                    startX = event.getX();
                    startY = event.getY();
                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    //当手指离开的时候
                    offsetX = event.getX();
                    offsetY = event.getY();
                    if(startY - offsetY > 50) {
                        Log.e("TAG","up");
                        swipeUp();
                    } else if(offsetY - startY > 50) {
                        Log.e("TAG","down");
                        swipeDown();
                    } else if(startX - offsetX > 50) {
                        Log.e("TAG","left");
                        swipeLeft();
                    } else if(offsetX - startX > 50) {
                        Log.e("TAG","right");
                        swipeRight();
                    }
                }
                return true;
            }
        });

    }

    //屏幕大小改变
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = (Math.min(w,h)-10)/4;

        addCards(cardWidth,cardWidth);

        startGame();


    }

    private void addCards(int cardWidth,int cardHeight){
        Card c ;

        for(int y = 0; y<4; y++){
            for(int x = 0; x<4; x++){
                c = new Card(getContext());
                c.setNum(0);
                addView(c,cardWidth,cardHeight);

                cardsMap[x][y] = c;
            }
        }
    }

    private void addRandomNum(){

        emptyPoints.clear();

        for(int y = 0; y<4; y++){
            for(int x = 0; x<4; x++){
                if (cardsMap[x][y].getNum()<=0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }

        Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);//生成2和4的概率为9：1
    }

    private void startGame() {

        MainActivity.getMainActivity().clearScore();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cardsMap[x][y].setNum(0);
            }
        }

        addRandomNum();
        addRandomNum();

    }

    private void swipeUp(){

        boolean flag = false;

        for(int x = 0; x<4; x++){
            for(int y = 0; y<4; y++) {

                for(int y1 = y+1; y1<4;y1++){
                    if(cardsMap[x][y1].getNum()>0){

                        if (cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y--;
                            flag =true;
                        }else if (cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            flag =true;
                        }
                        break;
                    }
                }
            }
        }
        if (flag){
            addRandomNum();
            cheakComplete();
        }
    }

    private void swipeDown(){

        boolean flag = false;
        for(int x = 0; x<4; x++){
            for(int y = 3; y>=0; y--) {

                for(int y1 = y-1; y1>=0;y1--){
                    if(cardsMap[x][y1].getNum()>0){

                        if (cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);

                            y++;
                            flag =true;
                        }else if (cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            flag =true;
                        }
                        break;
                    }
                }
            }
        }
        if (flag){
            addRandomNum();
            cheakComplete();
        }
    }

    private void swipeLeft(){

        boolean flag = false;
        for(int y = 0; y<4; y++){
            for(int x = 0; x<4; x++) {

                for(int x1 = x+1; x1<4;x1++){
                    if(cardsMap[x1][y].getNum()>0){

                        if (cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x--;
                            flag =true;
                        }else if (cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            flag =true;
                        }
                        break;
                    }
                }
            }
        }
        if (flag){
            addRandomNum();
            cheakComplete();
        }
    }

    private void swipeRight(){

        boolean flag = false;
        for(int y = 0; y<4; y++){
            for(int x = 3; x>=0; x--) {

                for(int x1 = x-1; x1>=0;x1--){
                    if(cardsMap[x1][y].getNum()>0){

                        if (cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);

                            x++;
                            flag = true;
                        }else if (cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            flag =true;
                        }
                        break;
                    }
                }
            }
        }
        if (flag){
            addRandomNum();
            cheakComplete();
        }
    }

    private void cheakComplete(){

        boolean complete = true;
        ALL:
        for(int y = 0; y<4; y++){
            for(int x = 0; x<4; x++) {
                if (cardsMap[x][y].getNum()==0||
                        (x>0&&cardsMap[x][y].equals(cardsMap[x-1][y]))||
                        (x<3&&cardsMap[x][y].equals(cardsMap[x+1][y]))||
                        (y>0&&cardsMap[x][y].equals(cardsMap[x][y-1]))||
                        (y<3&&cardsMap[x][y].equals(cardsMap[x][y+1]))
                        ){
                    complete =false;
                    break ALL;
                }
            }
        }

        if (complete) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle("你好");
            dialog.setMessage("重来");
            dialog.setCancelable(false);
            dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startGame();
                }
            }).show();

        }

    }

    private Card[][] cardsMap = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<>();
}


