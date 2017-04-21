package wakeup.wakeupapp;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Random;

public class ReflexGame extends AppCompatActivity {

    private Drawable circles[];
    private Drawable squares[];
    private Drawable triangles[];
    private ImageButton buttons[];
    private TextView instructions;
    private Button endGame;
    private TableLayout tableButtons;

    private Random random;
    private int pressesLeft;

    private static int activityFlag;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable imageChanger = new Runnable() {
        public void run() {
            changeImageColor();
            timerHandler.postDelayed(imageChanger, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_change_game);

        init();
        timerHandler.postDelayed(imageChanger, 1000);
    }

    public void init() {
        tableButtons = (TableLayout) findViewById(R.id.buttonsTable);
        endGame = (Button) findViewById(R.id.endGame);
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        endGame.setVisibility(View.INVISIBLE);
        circles = new Drawable[4];
        squares = new Drawable[4];
        triangles = new Drawable[4];
        random = new Random();
        pressesLeft = 12;
        buttons = new ImageButton[12];
        loadImages();
        initButtons();
        initTextView();
        setBackgrounds();
    }

    public void initButtons() {
        buttons[0] = (ImageButton) findViewById(R.id.button1);
        buttons[1] = (ImageButton) findViewById(R.id.button2);
        buttons[2] = (ImageButton) findViewById(R.id.button3);
        buttons[3] = (ImageButton) findViewById(R.id.button4);
        buttons[4] = (ImageButton) findViewById(R.id.button5);
        buttons[5] = (ImageButton) findViewById(R.id.button6);
        buttons[6] = (ImageButton) findViewById(R.id.button7);
        buttons[7] = (ImageButton) findViewById(R.id.button8);
        buttons[8] = (ImageButton) findViewById(R.id.button9);
        buttons[9] = (ImageButton) findViewById(R.id.button10);
        buttons[10] = (ImageButton) findViewById(R.id.button11);
        buttons[11] = (ImageButton) findViewById(R.id.button12);

        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable image = buttons[0].getBackground();
                if(image == squares[0] || image == squares[1] || image == squares[2] || image == squares[3]) {
                    pressesLeft--;
                }
                if(pressesLeft == 0) {
                    instructions.setVisibility(View.INVISIBLE);
                    tableButtons.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable image = buttons[1].getBackground();
                if(image == squares[0] || image == squares[1] || image == squares[2] || image == squares[3]) {
                    pressesLeft--;
                }
                if(pressesLeft == 0) {
                    instructions.setVisibility(View.INVISIBLE);
                    tableButtons.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable image = buttons[2].getBackground();
                if(image == squares[0] || image == squares[1] || image == squares[2] || image == squares[3]) {
                    pressesLeft--;
                }
                if(pressesLeft == 0) {
                    instructions.setVisibility(View.INVISIBLE);
                    tableButtons.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable image = buttons[3].getBackground();
                if(image == squares[0] || image == squares[1] || image == squares[2] || image == squares[3]) {
                    pressesLeft--;
                }
                if(pressesLeft == 0) {
                    instructions.setVisibility(View.INVISIBLE);
                    tableButtons.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
            }
        });

        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable image = buttons[4].getBackground();
                if(image == squares[0] || image == squares[1] || image == squares[2] || image == squares[3]) {
                    pressesLeft--;
                }
                if(pressesLeft == 0) {
                    instructions.setVisibility(View.INVISIBLE);
                    tableButtons.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
            }
        });

        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable image = buttons[5].getBackground();
                if(image == squares[0] || image == squares[1] || image == squares[2] || image == squares[3]) {
                    pressesLeft--;
                }
                if(pressesLeft == 0) {
                    instructions.setVisibility(View.INVISIBLE);
                    tableButtons.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
            }
        });

        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable image = buttons[6].getBackground();
                if(image == squares[0] || image == squares[1] || image == squares[2] || image == squares[3]) {
                    pressesLeft--;
                }
                if(pressesLeft == 0) {
                    instructions.setVisibility(View.INVISIBLE);
                    tableButtons.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
            }
        });

        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable image = buttons[7].getBackground();
                if(image == squares[0] || image == squares[1] || image == squares[2] || image == squares[3]) {
                    pressesLeft--;
                }
                if(pressesLeft == 0) {
                    instructions.setVisibility(View.INVISIBLE);
                    tableButtons.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
            }
        });

        buttons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable image = buttons[8].getBackground();
                if(image == squares[0] || image == squares[1] || image == squares[2] || image == squares[3]) {
                    pressesLeft--;
                }
                if(pressesLeft == 0) {
                    instructions.setVisibility(View.INVISIBLE);
                    tableButtons.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
            }
        });

        buttons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable image = buttons[9].getBackground();
                if(image == squares[0] || image == squares[1] || image == squares[2] || image == squares[3]) {
                    pressesLeft--;
                }
                if(pressesLeft == 0) {
                    instructions.setVisibility(View.INVISIBLE);
                    tableButtons.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
            }
        });

        buttons[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable image = buttons[10].getBackground();
                if(image == squares[0] || image == squares[1] || image == squares[2] || image == squares[3]) {
                    pressesLeft--;
                }
                if(pressesLeft == 0) {
                    instructions.setVisibility(View.INVISIBLE);
                    tableButtons.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
            }
        });

        buttons[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable image = buttons[11].getBackground();
                if(image == squares[0] || image == squares[1] || image == squares[2] || image == squares[3]) {
                    pressesLeft--;
                }
                if(pressesLeft == 0) {
                    instructions.setVisibility(View.INVISIBLE);
                    tableButtons.setVisibility(View.INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void loadImages() {
        circles[0] = getDrawable(R.mipmap.blue_circle);
        circles[1] = getDrawable(R.mipmap.green_circle);
        circles[2] = getDrawable(R.mipmap.red_circle);
        circles[3] = getDrawable(R.mipmap.yellow_circle);

        squares[0] = getDrawable(R.mipmap.blue_rect);
        squares[1] = getDrawable(R.mipmap.green_rect);
        squares[2] = getDrawable(R.mipmap.red_rect);
        squares[3] = getDrawable(R.mipmap.yellow_rect);

        triangles[0] = getDrawable(R.mipmap.blue_tri);
        triangles[1] = getDrawable(R.mipmap.green_tri);
        triangles[2] = getDrawable(R.mipmap.red_tri);
        triangles[3] = getDrawable(R.mipmap.yellow_tri);
    }

    public void initTextView() {
        instructions = (TextView) findViewById(R.id.instructions);
        instructions.setText("Press the rectangles quickly after they change colors.");
    }

    public void setBackgrounds() {
        for(int i = 0; i < 12; i++) {
            int choice = random.nextInt(2);
            switch(choice) {
                case 0:
                    buttons[i].setBackground(squares[random.nextInt(4)]);
                    break;
                case 1:
                    switch(random.nextInt(2)) {
                        case 0:
                            buttons[i].setBackground(circles[random.nextInt(4)]);
                            break;
                        case 1:
                            buttons[i].setBackground(triangles[random.nextInt(4)]);
                            break;
                    }
            }
        }
    }

    public void changeImageColor() {
        int image1 = 0;
        int image2 = 0;
        while(image1 == image2) {
            image1 = random.nextInt(12);
            image2 = random.nextInt(12);
        }
        changeImage(image1);
        changeImage(image2);
    }

    public void changeImage(int buttonNum) {
        Drawable image = buttons[buttonNum].getBackground();

        for(int i = 0; i < 4; i++) {
            if(image == squares[i]) {
                if(i == 3) {
                    buttons[buttonNum].setBackground(squares[0]);
                } else {
                    buttons[buttonNum].setBackground(squares[i+1]);
                }
            } else if (image == circles[i]) {
                if(i == 3) {
                    buttons[buttonNum].setBackground(circles[0]);
                } else {
                    buttons[buttonNum].setBackground(circles[i+1]);
                }
            } else if (image == triangles[i]) {
                if(i == 3) {
                    buttons[buttonNum].setBackground(triangles[0]);
                } else {
                    buttons[buttonNum].setBackground(triangles[i+1]);
                }
            }
        }
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
