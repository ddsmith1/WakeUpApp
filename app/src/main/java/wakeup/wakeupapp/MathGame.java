package wakeup.wakeupapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MathGame extends AppCompatActivity {

    private Button expression1;
    private Button expression2;
    private Button expression3;
    private Button expression4;

    private View.OnClickListener exp1;
    private View.OnClickListener exp2;
    private View.OnClickListener exp3;
    private View.OnClickListener exp4;

    private final int RANGE = 5;
    private final int SCORE_NEEDED = 3;

    private int median;
    private int score;
    private Random random;

    private int expressionFlag;
    private int expAnswers[];

    private int buttonPressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);
        Log.i("onCreate", "Finished onCreate()");

    }

    @Override
    public void onStart() {
        super.onStart();
        expression1 = (Button) findViewById(R.id.expression1);
        expression2 = (Button) findViewById(R.id.expression2);
        expression3 = (Button) findViewById(R.id.expression3);
        expression4 = (Button) findViewById(R.id.expression4);
        initialize();
        Log.i("init", "finished initialize");
        Log.i("button", "finished buttons");
        gameLoop();
    }

    public void initialize() {
        score = 0;
        median = 0;
        expressionFlag = 0;
        buttonPressed = 0;
        random = new Random();
        initAnswers();
        initListeners();
        attachListeners();
    }

    public void attachListeners() {
        expression1.setOnClickListener(exp1);
        expression2.setOnClickListener(exp2);
        expression3.setOnClickListener(exp3);
        expression4.setOnClickListener(exp4);
    }

    public void initAnswers() {
        expAnswers = new int[4];
        for(int i = 0; i < 4; i++) {
            expAnswers[i] = 0;
        }
    }

    public void initListeners() {
        exp1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore(checkAnswer(0));
                checkScore();
                gameLoop();
            }
        };

        exp2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore(checkAnswer(1));
                checkScore();
                gameLoop();
            }
        };

        exp3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore(checkAnswer(2));
                checkScore();
                gameLoop();
            }
        };

        exp4 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore(checkAnswer(3));
                checkScore();
                gameLoop();
            }
        };
    }

    public void updateScore(boolean value) {
        if(value) {
            score++;
        } else {
            if(score != 0) {
                score--;
            }
        }
    }

    public void gameLoop() {
        generateMedian();
        Log.i("median", "generated median");
        updateButtons();
        Log.i("update", "updated buttons");
    }

    public boolean checkAnswer(int userChoice) {
        for(int i = 0; i < 4; i++) {
            if(expAnswers[i] > expAnswers[userChoice]) {
                return false;
            }
        }
        return true;
    }

    public void updateButtons() {
        expressionFlag = 0;
        expression1.setText(generateExpression());
        expressionFlag = 1;
        expression2.setText(generateExpression());
        expressionFlag = 2;
        expression3.setText(generateExpression());
        expressionFlag = 3;
        expression4.setText(generateExpression());
    }

    public String generateExpression() {
        int choice = random.nextInt(2) + 1;

        String result;
        switch(choice) {
            case 1:
                result = generateAddExpression();
                break;
            case 2:
                int num = random.nextInt(10) + 1 + median - RANGE;
                expAnswers[expressionFlag] = num;
                result = Integer.toString(num);
                break;
            case 3:
                result = generateMultExpression();
                break;
            case 4:
                result = generateSubExpression();
                break;
            default:
                result = "0";
        }
        return result;
    }

    public void generateMedian() {
        median = random.nextInt(20) + 1 + RANGE;
    }

    public String generateSubExpression() {
        return "0";
    }

    public String generateMultExpression() {
        return "0";
    }

    public String generateAddExpression() {
        int operand1 = 0;
        int operand2 = 0;
        int sum = 0;
        while(!(sum >= median - 5 && sum <= median + RANGE )) {
            operand1 = (random.nextInt(10) + 1 + median - RANGE)/2;
            operand2 = (random.nextInt(10) + 1 + median - RANGE)/2;
            sum = operand1 + operand2;
        }
        expAnswers[expressionFlag] = sum;
        return operand1 + "+" + operand2;
    }

    public void checkScore() {
        if(score >= SCORE_NEEDED) {
            finish();
        }
    }
}
