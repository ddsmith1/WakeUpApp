package wakeup.wakeupapp;

/**
 * Created by Rebekah on 2/15/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ColorGame extends Activity {
    final private static Object LOCK = new Object();

    private Context context;
    private Handler handler;

    private TextView colorSpot;
    private Button endGame;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;

    int currentColor;
    int colorsLeft = 0;
    int [] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
    String [] texts = {"RED", "YELLOW", "GREEN", "BLUE"};

    private void setNewColor() {
        Random rand = new Random();

        int picker1 = rand.nextInt(4);
        int picker2 = rand.nextInt(4);;

        while (picker2 == picker1 || colors[picker2] == currentColor) {
            picker2 = rand.nextInt(4);
        }

        colorSpot.setText(texts[picker1]);
        colorSpot.setTextColor(colors[picker2]);

        currentColor = colors[picker2];
    }

    private void checkSolution(Button button) {
        String text = button.getText().toString();
        int color = Color.parseColor(text);

        if (currentColor == color) {
            colorsLeft--;
            answer1.setEnabled(true);
            answer2.setEnabled(true);
            answer3.setEnabled(true);
            answer4.setEnabled(true);

            if (colorsLeft == 0) {
                endGame.setVisibility(View.VISIBLE);
                answer1.setVisibility(View.INVISIBLE);
                answer2.setVisibility(View.INVISIBLE);
                answer3.setVisibility(View.INVISIBLE);
                answer4.setVisibility(View.INVISIBLE);
                colorSpot.setVisibility(View.INVISIBLE);
                endGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            } else {
                setNewColor();
            }
        } else {
            button.setEnabled(false);
//                if (colorsLeft < 15) {
//                    colorsLeft++;
//                }
        }

        ((TextView) findViewById(R.id.tv1)).setText("Colors left: " + colorsLeft);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.color_game);

        colorSpot = (TextView) findViewById(R.id.colorText);
        context = colorSpot.getContext();

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                checkSolution(btn);
            }
        };

        answer1.setOnClickListener(listener);
        answer2.setOnClickListener(listener);
        answer3.setOnClickListener(listener);
        answer4.setOnClickListener(listener);


        endGame = (Button) findViewById(R.id.endbutton);
        endGame.setVisibility(View.INVISIBLE);

        colorsLeft = 10;

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                synchronized (LOCK) {
                    setNewColor();
                }
            };
        };

        TimerTask tt = new TimerTask() {

            @Override
            public void run() {
                try {
                    synchronized (LOCK) {
                        handler.sendEmptyMessage(0);
                    }
                } catch (Exception e) {
                    Log.e("E1", e.getMessage());
                }
            }
        };

        Timer t = new Timer(false);
        t.schedule(tt, 2000);
    }

}
