package tech.cyang.game2048;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public MainActivity(){
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore = (TextView) findViewById(R.id.tvScore);

    }

    public void clearScore(){
           score = 0;
    }

    public void showScore(){
        tvScore.setText(score+"");
    }

    public void addScore(int s){
        score+=s;
        showScore();
    }

    public int score;
    private TextView tvScore;
    private static MainActivity mainActivity = null;

    public static MainActivity getMainActivity(){
        return mainActivity;
    }
}
