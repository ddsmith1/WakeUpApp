package wakeup.wakeupapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class OperatorsGame extends AppCompatActivity {

    private TextView exp1;
    private TextView exp2;
    private TextView instructions;

    private String expression1;
    private String expression2;

    private ImageButton greaterThan;
    private ImageButton lessThan;
    private ImageButton equals;
    private Button endGame;

    private Drawable less;
    private Drawable greater;
    private Drawable equal;

    private int answer1;
    private int answer2;
    private int tempAnswer;

    private int roundsLeft;

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equality_game);

        init();
        generateExpression();
    }

    private void init() {
        instructions = (TextView) findViewById(R.id.instructions);
        endGame = (Button) findViewById(R.id.endGame);
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        endGame.setVisibility(View.INVISIBLE);
        random = new Random();
        roundsLeft = 8;
        initImages();
        initTextViews();
        initButtons();
    }

    private void initImages() {
        float scaleFactor = 0.7f;
        less = getDrawable(R.drawable.lessthan);
        less = scaleImage(less, scaleFactor);
        greater = getDrawable(R.drawable.greaterthan);
        greater = scaleImage(greater, scaleFactor);
        equal = getDrawable(R.drawable.equals);
        equal = scaleImage(equal, scaleFactor);
    }

    private void initTextViews() {
        exp1 = (TextView) findViewById(R.id.exp1);
        exp2 = (TextView) findViewById(R.id.exp2);
    }

    private void initButtons() {
        greaterThan = (ImageButton) findViewById(R.id.greaterThan);
        lessThan = (ImageButton) findViewById(R.id.lessThan);
        equals = (ImageButton) findViewById(R.id.equals);
        greaterThan.setBackground(greater);
        lessThan.setBackground(less);
        equals.setBackground(equal);

        greaterThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer1 > answer2) {
                    updateRoundsLeft(1);
                } else {
                    updateRoundsLeft(0);
                }
                generateExpression();
            }
        });

        lessThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer1 < answer2) {
                    updateRoundsLeft(1);
                } else {
                    updateRoundsLeft(0);
                }
                generateExpression();
            }
        });

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer1 == answer2) {
                    updateRoundsLeft(1);
                } else {
                    updateRoundsLeft(0);
                }
                generateExpression();
            }
        });

    }

    public void generateExpression() {
        expression1 = expressionBeginning() + expressionEnd();
        answer1 = tempAnswer;
        expression2 = expressionBeginning() + expressionEnd();
        answer2 = tempAnswer;
        exp1.setText(expression1);
        exp2.setText(expression2);
    }

    public String expressionBeginning() {
        int choice = random.nextInt(2);
        switch(choice) {
            case 0:
                int a = random.nextInt(7) + 2;
                int b = random.nextInt(7) + 2;
                int sum = a+b;
                tempAnswer = sum;
                return "( " + Integer.toString(a) + " + " + Integer.toString(b) + " )";
            case 1:
                int e = random.nextInt(5) + 2;
                int f = random.nextInt(5) + 2;
                tempAnswer = e * f;
                return " " + Integer.toString(e * f) + " ";
        }
        return "";
    }

    public String expressionEnd() {
        int choice = random.nextInt(3);
        switch(choice) {
            case 0:
                int a = random.nextInt(7) + 2;
                tempAnswer = tempAnswer + a;
                return "+ " + Integer.toString(a);
            case 1:
                int c = random.nextInt(5) + 2;
                tempAnswer = tempAnswer * c;
                return "* " + Integer.toString(c);
        }
        return " ";
    }

    public Drawable scaleImage (Drawable image, float scaleFactor) {

        if ((image == null) || !(image instanceof BitmapDrawable)) {
            return image;
        }

        Bitmap b = ((BitmapDrawable)image).getBitmap();

        int sizeX = Math.round(image.getIntrinsicWidth() * scaleFactor);
        int sizeY = Math.round(image.getIntrinsicHeight() * scaleFactor);

        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, sizeX, sizeY, false);

        image = new BitmapDrawable(getResources(), bitmapResized);

        return image;

    }

    public void updateRoundsLeft(int value) {
        switch(value) {
            case 0:
                if(roundsLeft < 5) {
                    roundsLeft++;
                }
                break;
            case 1:
                roundsLeft--;
                if(roundsLeft <= 0) {
                    exp1.setVisibility(View.INVISIBLE);
                    exp2.setVisibility(View.INVISIBLE);
                    greaterThan.setVisibility(View.INVISIBLE);
                    lessThan.setVisibility(View.INVISIBLE);
                    equals.setVisibility(View.INVISIBLE);
                    instructions.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
