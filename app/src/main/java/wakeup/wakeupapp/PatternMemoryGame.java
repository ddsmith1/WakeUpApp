package wakeup.wakeupapp;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;

import java.util.Random;

import static android.view.View.INVISIBLE;

public class PatternMemoryGame extends AppCompatActivity {

    private Drawable blueSquare;
    private Drawable redSquare;
    private ImageButton imageButtons[];
    private Button checkButton;
    private Button advanceButton;

    private View.OnClickListener listeners[];
    private View.OnClickListener checkListener;
    private View.OnClickListener advanceListener;

    private Button endGame;
    private TableLayout tableButtons;

    private int pattern[];
    private int userPattern[];

    private int score;

    private Random random;

    private final int PATTERN_SIZE = 12;
    private final int NUM_TO_MATCH = 5;
    private final int SCORE_TO_REACH = 5;

    private static int activityFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_memory_game);

        blueSquare = getDrawable(R.mipmap.blue_rect);
        redSquare = getDrawable(R.mipmap.red_rect);

        init();
        gameSetup();
    }

    private void init() {
        tableButtons = (TableLayout) findViewById(R.id.buttonsTable);
        endGame = (Button) findViewById(R.id.endGame);
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        endGame.setVisibility(View.INVISIBLE);
        random = new Random();
        score = 0;
        initializePatterns();
        initButtons();
        initListeners();
        attachListeners();
    }

    private void initializePatterns() {
        pattern = new int[PATTERN_SIZE];
        userPattern = new int[PATTERN_SIZE];
        clearPatterns();
    }

    private void initButtons() {
        imageButtons = new ImageButton[PATTERN_SIZE];
        imageButtons[0] = (ImageButton) findViewById(R.id.imageButton1);
        imageButtons[1] = (ImageButton) findViewById(R.id.imageButton2);
        imageButtons[2] = (ImageButton) findViewById(R.id.imageButton3);
        imageButtons[3] = (ImageButton) findViewById(R.id.imageButton4);
        imageButtons[4] = (ImageButton) findViewById(R.id.imageButton5);
        imageButtons[5] = (ImageButton) findViewById(R.id.imageButton6);
        imageButtons[6] = (ImageButton) findViewById(R.id.imageButton7);
        imageButtons[7] = (ImageButton) findViewById(R.id.imageButton8);
        imageButtons[8] = (ImageButton) findViewById(R.id.imageButton9);
        imageButtons[9] = (ImageButton) findViewById(R.id.imageButton10);
        imageButtons[10] = (ImageButton) findViewById(R.id.imageButton11);
        imageButtons[11] = (ImageButton) findViewById(R.id.imageButton12);
        checkButton = (Button) findViewById(R.id.checkButton);
        advanceButton = (Button) findViewById(R.id.advanceButton);
    }

    private void initListeners() {
        listeners = new View.OnClickListener[PATTERN_SIZE];
        listeners[0] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtons[0].getBackground() == blueSquare) {
                    imageButtons[0].setBackground(redSquare);
                    userPattern[0] = 1;
                } else {
                    imageButtons[0].setBackground(blueSquare);
                    userPattern[0] = 0;
                }
            }
        };

        listeners[1] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtons[1].getBackground() == blueSquare) {
                    imageButtons[1].setBackground(redSquare);
                    userPattern[1] = 1;
                } else {
                    imageButtons[1].setBackground(blueSquare);
                    userPattern[1] = 0;
                }
            }
        };

        listeners[2] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtons[2].getBackground() == blueSquare) {
                    imageButtons[2].setBackground(redSquare);
                    userPattern[2] = 1;
                } else {
                    imageButtons[2].setBackground(blueSquare);
                    userPattern[2] = 0;
                }
            }
        };

        listeners[3] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtons[3].getBackground() == blueSquare) {
                    imageButtons[3].setBackground(redSquare);
                    userPattern[3] = 1;
                } else {
                    imageButtons[3].setBackground(blueSquare);
                    userPattern[3] = 0;
                }
            }
        };

        listeners[4] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtons[4].getBackground() == blueSquare) {
                    imageButtons[4].setBackground(redSquare);
                    userPattern[4] = 1;
                } else {
                    imageButtons[4].setBackground(blueSquare);
                    userPattern[4] = 0;
                }
            }
        };

        listeners[5] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtons[5].getBackground() == blueSquare) {
                    imageButtons[5].setBackground(redSquare);
                    userPattern[5] = 1;
                } else {
                    imageButtons[5].setBackground(blueSquare);
                    userPattern[5] = 0;
                }
            }
        };

        listeners[6] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtons[6].getBackground() == blueSquare) {
                    imageButtons[6].setBackground(redSquare);
                    userPattern[6] = 1;
                } else {
                    imageButtons[6].setBackground(blueSquare);
                    userPattern[6] = 0;
                }
            }
        };

        listeners[7] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtons[7].getBackground() == blueSquare) {
                    imageButtons[7].setBackground(redSquare);
                    userPattern[7] = 1;
                } else {
                    imageButtons[7].setBackground(blueSquare);
                    userPattern[7] = 0;
                }
            }
        };

        listeners[8] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtons[8].getBackground() == blueSquare) {
                    imageButtons[8].setBackground(redSquare);
                    userPattern[8] = 1;
                } else {
                    imageButtons[8].setBackground(blueSquare);
                    userPattern[8] = 0;
                }
            }
        };

        listeners[9] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtons[9].getBackground() == blueSquare) {
                    imageButtons[9].setBackground(redSquare);
                    userPattern[9] = 1;
                } else {
                    imageButtons[9].setBackground(blueSquare);
                    userPattern[9] = 0;
                }
            }
        };

        listeners[10] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtons[10].getBackground() == blueSquare) {
                    imageButtons[10].setBackground(redSquare);
                    userPattern[10] = 1;
                } else {
                    imageButtons[10].setBackground(blueSquare);
                    userPattern[10] = 0;
                }
            }
        };

        listeners[11] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageButtons[11].getBackground() == blueSquare) {
                    imageButtons[11].setBackground(redSquare);
                    userPattern[11] = 1;
                } else {
                    imageButtons[11].setBackground(blueSquare);
                    userPattern[11] = 0;
                }
            }
        };

        checkListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPatterns()) {
                    score++;
                }
                if(score >= SCORE_TO_REACH) {
                    tableButtons.setVisibility(INVISIBLE);
                    checkButton.setVisibility(INVISIBLE);
                    advanceButton.setVisibility(INVISIBLE);
                    endGame.setVisibility(View.VISIBLE);
                }
                if(score < SCORE_TO_REACH) {
                    gameSetup();
                    advanceButton.setVisibility(View.VISIBLE);
                    checkButton.setVisibility(INVISIBLE);
                }
            }
        };

        advanceListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButtons();
                checkButton.setVisibility(View.VISIBLE);
                advanceButton.setVisibility(INVISIBLE);
            }
        };

    }

    private void attachListeners() {
        for(int i = 0; i < PATTERN_SIZE; i++) {
            imageButtons[i].setOnClickListener(listeners[i]);
        }

        checkButton.setOnClickListener(checkListener);
        advanceButton.setOnClickListener(advanceListener);
    }

    private void gameSetup() {
        clearButtons();
        clearPatterns();
        generatePattern();
        showPattern();
        checkButton.setVisibility(INVISIBLE);
    }

    private void clearPatterns() {
        for(int i = 0; i < PATTERN_SIZE; i++) {
            pattern[i] = 0;
            userPattern[i] = 0;
        }
    }

    private void generatePattern() {
        int count = 0; //only want user to have to remember 5
        int bool;
        for(int i = 0; i < PATTERN_SIZE; i++) {
            bool = random.nextInt(2);
            if(bool == 1) {
                count++;
            }
            pattern[i] = bool;
            if(count > NUM_TO_MATCH) {
                break;
            }
        }
    }

    private void showPattern() {
        for(int i = 0; i < PATTERN_SIZE; i++) {
            if(pattern[i] == 1) {
                imageButtons[i].setBackground(redSquare);
            }
        }
    }

    private boolean checkPatterns() {
        for(int i = 0; i < PATTERN_SIZE; i++) {
            if(pattern[i] != userPattern[i]) {
                return false;
            }
        }
        return true;
    }

    private void clearButtons() {
        for(int i = 0; i < PATTERN_SIZE; i++) {
            imageButtons[i].setBackground(blueSquare);
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
