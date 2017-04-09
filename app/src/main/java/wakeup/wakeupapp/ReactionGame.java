package wakeup.wakeupapp;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class ReactionGame extends AppCompatActivity {

    private Drawable shapes[];
    private Drawable prevShape;
    private Drawable currentShape;

    private Button yes;
    private Button no;
    private Button start;
    private TextView instructions;
    private ImageButton image;

    private Random random;
    private int score;

    private final int SCORE_TO_REACH = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_game);

        init();
    }

    public void init() {
        instructions = (TextView) findViewById(R.id.textView);
        instructions.setText("Press yes if the current shape matches the previous shape, press no if it doesn't match, and do so as quickly as possible.");
        random = new Random();
        score = 0;
        initButtons();
        no.setVisibility(View.INVISIBLE);
        yes.setVisibility(View.INVISIBLE);
        initShapes();
        prevShape = shapes[0];
        currentShape = shapes[0];
        updateImage();
    }

    public void initButtons() {
        yes = (Button) findViewById(R.id.yesButton);
        no = (Button) findViewById(R.id.noButton);
        image = (ImageButton) findViewById(R.id.image);
        start = (Button) findViewById(R.id.startButton);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore(checkAnswer(1));
                updateImage();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore(checkAnswer(2));
                updateImage();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instructions.setVisibility(View.GONE);
                yes.setVisibility(View.VISIBLE);
                no.setVisibility(View.VISIBLE);
                start.setVisibility(View.INVISIBLE);
                updateImage();
            }
        });

    }

    public void initShapes() {
        shapes = new Drawable[3];
        shapes[0] = getDrawable(R.mipmap.red_circle);
        shapes[1] = getDrawable(R.mipmap.blue_rect);
        shapes[2] = getDrawable(R.mipmap.yellow_tri);
    }

    public void updateImage() {
        prevShape = currentShape;
        int choice = random.nextInt(3);
        switch(choice) {
            case 0:
                image.setBackground(shapes[0]);
                currentShape = shapes[0];
                break;
            case 1:
                image.setBackground(shapes[1]);
                currentShape = shapes[1];
                break;
            case 2:
                image.setBackground(shapes[2]);
                currentShape = shapes[2];
                break;
            default:
                image.setBackground(shapes[0]);
                currentShape = shapes[0];
                break;
        }
    }

    public boolean checkAnswer(int choice) {
        if(choice == 1) {
            if(prevShape == currentShape) {
                return true;
            } else return false;
        } else {
            if(prevShape != currentShape) {
                return true;
            } else return false;
        }
    }

    public void updateScore(boolean answer) {
        if(answer) {
            score++;
        } else {
            if(score > 0) {
                score--;
            }
        }
        if(score >= SCORE_TO_REACH) {
            finish();
        }
    }


}
