package wakeup.wakeupapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MathGame extends AppCompatActivity {

    private Button expression1;
    private Button expression2;
    private Button expression3;
    private Button expression4;
    private Button endGame;

    private View.OnClickListener exp1;
    private View.OnClickListener exp2;
    private View.OnClickListener exp3;
    private View.OnClickListener exp4;

    private final int RANGE = 3;

    private int median;
    private int score;
    private Random random;

    private TextView scoreDisplay;
    private TextView instructions;

    private int expressionFlag;
    private int expAnswers[];

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
        endGame = (Button) findViewById(R.id.endGame);

        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        endGame.setVisibility(View.INVISIBLE);
        score = 15;
        median = 0;
        expressionFlag = 0;
        random = new Random();
        initAnswers();
        initListeners();
        attachListeners();
        scoreDisplay = (TextView) findViewById(R.id.score);
        scoreDisplay.setText("Rounds left: " + Integer.toString(score));
        instructions = (TextView) findViewById(R.id.instructions);

    }

    public void initAnswers() {
        expAnswers = new int[4];
        clearAnswers();
    }

    public void initListeners() {
        exp1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore(checkAnswer(0));
                checkScore();
                clearAnswers();
                gameLoop();
            }
        };

        exp2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore(checkAnswer(1));
                checkScore();
                clearAnswers();
                gameLoop();
            }
        };

        exp3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore(checkAnswer(2));
                checkScore();
                clearAnswers();
                gameLoop();
            }
        };

        exp4 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore(checkAnswer(3));
                checkScore();
                clearAnswers();
                gameLoop();
            }
        };
    }

    public void attachListeners() {
        expression1.setOnClickListener(exp1);
        expression2.setOnClickListener(exp2);
        expression3.setOnClickListener(exp3);
        expression4.setOnClickListener(exp4);
    }

    public void updateScore(boolean value) {
        if(value) {
            score--;
        } else {
            if(score != 15) {
                score++;
            }
        }
        scoreDisplay.setText("Rounds left: " + Integer.toString(score));
    }

    public void gameLoop() {
        generateMedian();
        updateButtons();
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
        String result = null;
            int choice = random.nextInt(4) + 1;

            switch (choice) {
                case 1:
                    result = generateAddExpression();
                    break;
                case 2:
                    int num = random.nextInt(10) + 1 + median - RANGE;
                    expAnswers[expressionFlag] = num;
                    result = Integer.toString(num);
                    break;
                case 3:
                    result = generateSubExpression();
                    break;
                case 4:
                    result = generateMultExpression();
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
        int operand1 = 0;
        int operand2 = 0;
        int difference = 0;
        while(!(difference >= median - RANGE && difference <= median + RANGE && difference != 0)) {
            operand1 = random.nextInt(median * 2) + 1;
            operand2 = random.nextInt(median * 2) + 1;
            difference = operand1 - operand2;
            for(int i = 0; i < 4; i++) {
                if(difference == expAnswers[i]) {
                    difference = -1;
                }
            }
        }
        expAnswers[expressionFlag] = difference;
        return operand1 + " - " + operand2;
    }

    public String generateMultExpression() {
        int operand1 = 0;
        int operand2 = 0;
        int product = 0;
        while(!(product >= median - RANGE && product <= median + RANGE )) {
            operand1 = random.nextInt(median) + 1;
            operand2 = random.nextInt(median) + 1;
            product = operand1 * operand2;
            for(int i = 0; i < 4; i++) {
                if(product == expAnswers[i]) {
                    product = -1;
                }
            }
        }
        expAnswers[expressionFlag] = product;
        return operand1 + " x " + operand2;
    }

    public String generateAddExpression() {
        int operand1 = 0;
        int operand2 = 0;
        int sum = 0;
        while(!(sum >= median - RANGE && sum <= median + RANGE )) {
            operand1 = (random.nextInt(median + RANGE)/2) + 1;
            operand2 = (random.nextInt(median + RANGE)/2) + 1;
            sum = operand1 + operand2;
            for(int i = 0; i < 4; i++) {
                if(sum == expAnswers[i]) {
                    sum = -1;
                }
            }
        }
        expAnswers[expressionFlag] = sum;
        return operand1 + " + " + operand2;
    }

    public void checkScore() {
        if(score <= 0) {
            expression1.setVisibility(View.INVISIBLE);
            expression2.setVisibility(View.INVISIBLE);
            expression3.setVisibility(View.INVISIBLE);
            expression4.setVisibility(View.INVISIBLE);
            scoreDisplay.setVisibility(View.INVISIBLE);
            instructions.setVisibility(View.INVISIBLE);
            endGame.setVisibility(View.VISIBLE);
        }
    }

    public void clearAnswers() {
        for(int i = 0; i < 4; i++) {
            expAnswers[i] = 0;
        }
    }

}
