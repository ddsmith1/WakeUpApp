package wakeup.wakeupapp;

/**
 * Created by Rebekah on 2/15/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class PatternGame extends Activity {
    private final int NR_PATTERNS = 10;

    private Context context;

    private TextView patternSpot;
    private Button endGame;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;

    int currentSolution;
    int patternsLeft = 0;

    private static int activityFlag;

    private void setNewPattern() {
        String pattern;
        int start;
        LinkedList<Integer> fakeAnswers = new LinkedList<Integer>();

        Random rand = new Random();

        int picker = rand.nextInt(3);

        if (picker == 0) {
            //for addition pattern
            start = rand.nextInt(11);
            int increment = rand.nextInt(10)+1;
            currentSolution = start + increment * 3;
            pattern = start + ", " + (start + increment) + ", " + (start + increment * 2) + "...";

            fakeAnswers.add(currentSolution + increment);
        }
        else if (picker == 1) {
            //for subtraction pattern
            int decrement = rand.nextInt(8)+1;
            start = decrement*3+ rand.nextInt(11);
            currentSolution = start - decrement * 3;
            pattern = start + ", " + (start - decrement) + ", " + (start - decrement * 2) + "...";

            fakeAnswers.add(currentSolution - decrement);
        }
        else {
            //for multiplication pattern
            start = rand.nextInt(3)+1;
            int factor = rand.nextInt(2)+2;
            currentSolution = start * factor * factor * factor;
            pattern = start + ", " + (start * factor) + ", " + (start * factor * factor) + "...";

            fakeAnswers.add(currentSolution + factor);
        }

        for (int i=0; i<3; i++) { //just for a nice answer pool
            int num1 = currentSolution + rand.nextInt(10) + 1;
            int num2 = currentSolution - rand.nextInt(8) - 1;

            if (fakeAnswers.contains(num1) == false) {
                fakeAnswers.add(num1);
            }
            if (fakeAnswers.contains(num2) == false) {
                fakeAnswers.add(num2);
            }
        }

        Collections.shuffle(fakeAnswers);

        patternSpot.setText(pattern);

        picker = rand.nextInt(4);

        ArrayList<Button> buttons = new ArrayList<Button>();
        buttons.add(answer1);
        buttons.add(answer2);
        buttons.add(answer3);
        buttons.add(answer4);

        buttons.remove(picker).setText(Integer.toString(currentSolution));

        while (buttons.isEmpty() == false) {
            picker = rand.nextInt(buttons.size());
            buttons.remove(picker).setText(Integer.toString(fakeAnswers.remove()));
        }
    }

    private void checkSolution(Button button) {
        String text= button.getText().toString();

        if (currentSolution == Integer.parseInt(text)) {
            patternsLeft--;
            answer1.setEnabled(true);
            answer2.setEnabled(true);
            answer3.setEnabled(true);
            answer4.setEnabled(true);

            answer1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            answer2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            answer3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            answer4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));


            if (patternsLeft == 0) {
                endGame.setVisibility(View.VISIBLE);
                answer1.setVisibility(View.INVISIBLE);
                answer2.setVisibility(View.INVISIBLE);
                answer3.setVisibility(View.INVISIBLE);
                answer4.setVisibility(View.INVISIBLE);
                patternSpot.setVisibility(View.INVISIBLE);
                endGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            } else {
                setNewPattern();
            }
        } else {
            button.setEnabled(false);
            button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dividerColor)));
        }

        ((TextView) findViewById(R.id.tv1)).setText("Patterns left: " + patternsLeft);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pattern_game);

        patternSpot = (TextView) findViewById(R.id.patternText);
        context = patternSpot.getContext();

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

        patternsLeft = NR_PATTERNS;
        setNewPattern();
    }

    public static void setActivityFlag() {
        activityFlag = 1;
    }

    public static void clearActivityFlag() {
        activityFlag = 0;
    }

    @Override
    public void onBackPressed() {
        if(activityFlag == 0) {
            super.onBackPressed();
        }
    }

}
