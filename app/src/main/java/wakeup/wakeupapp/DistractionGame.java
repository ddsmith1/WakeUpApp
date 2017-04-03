package wakeup.wakeupapp;

/**
 * Created by Rebekah on 2/15/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DistractionGame extends Activity {
    final private int SQUARE_SIZE = 3;
    final private int TOTAL_NR_SHAPES = 12;
    final private int NR_COLORS = 4;
    final private int NR_SHAPES = 3;
    final private static Object LOCK = new Object();

    private int[][] cards;
    private LinkedList<Card> cardList;

    private List<Drawable> blueImages;
    private List<Drawable> redImages;
    private List<Drawable> greenImages;
    private List<Drawable> yellowImages;
    private Context context;

    private ArrayList<String> colors = new ArrayList<String>();

    private TableLayout mainTable;

    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private Button endGame;
    ArrayList<Button> answerButtons;

    private String colorToWatch;
    private int nrChanges;
    private int nrAttempts = 0;

    private ShowModifiedHandler showModifiedHandler;
    private CountDownTimer countdown;

    private TextView tv1;
    private TextView tv2;

    private Boolean timesUp;
    private Handler handler = new Handler();


    private void checkSelection(Button button) {
        String text= button.getText().toString();

        if (nrChanges == Integer.parseInt(text)) {
           endTheGame();

            //TODO: make this something less douchey
            Toast toast = Toast.makeText(context, "You got it!", Toast.LENGTH_LONG);
            toast.show();
        } else {
            nrAttempts++;
            if (nrAttempts >= 3) {
                //TODO: make this something less douchey
                Toast toast = Toast.makeText(context, "Not your morning, huh?", Toast.LENGTH_LONG);
                toast.show();
                endTheGame();
            }
            else {
                ((TextView)findViewById(R.id.tv2)).setText("Not quite. The answer was " + nrChanges + ". Try again!");
                for (Button btn: answerButtons) {
                    btn.setVisibility(View.INVISIBLE);
                }
                handler.postDelayed(new Runnable() {
                    public void run() {
                        startGame();
                    }
                }, 3000);
            }
        }
    }
    private void endTheGame() {
        for (Button btn: answerButtons) {
            btn.setVisibility(View.INVISIBLE);
        }
        mainTable.setVisibility(View.GONE);
        endGame.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.GONE);
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        colors.add("red");
        colors.add("yellow");
        colors.add("green");
        colors.add("blue");

        showModifiedHandler = new ShowModifiedHandler();
        loadImages();

        setContentView(R.layout.distraction_game);

        mainTable = (TableLayout) findViewById(R.id.TableLayout03);
        context = mainTable.getContext();

        endGame = (Button) findViewById(R.id.endbutton);
        endGame.setVisibility(View.INVISIBLE);

        answerButtons = new ArrayList<Button>();
        answerButtons.add((Button) findViewById(R.id.answer1));
        answerButtons.add((Button) findViewById(R.id.answer2));
        answerButtons.add((Button) findViewById(R.id.answer3));
        answerButtons.add((Button) findViewById(R.id.answer4));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                checkSelection(btn);
            }
        };

        for (Button button: answerButtons) {
            button.setOnClickListener(listener);
            button.setVisibility(View.INVISIBLE);
        }


        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        startGame();
    }

    public void startGame() {
        timesUp = false;
        nrChanges = 0;

        cardList = new LinkedList<Card>();
        colorToWatch = colors.get(new Random().nextInt(NR_COLORS));

        mainTable.setVisibility(View.VISIBLE);

        cards = new int[SQUARE_SIZE][SQUARE_SIZE];
        TableRow tr = ((TableRow) findViewById(R.id.TableRow03));
        tr.removeAllViews();
        mainTable = new TableLayout(context);
        tr.addView(mainTable);

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        for (int y = 0; y < SQUARE_SIZE; y++) {
            mainTable.addView(createCardRow(y));
        }

        loadCards();

        tv2.setText("Count the number of times shapes change colors to " + colorToWatch.toUpperCase() + ".");

        showCards();

        handler.postDelayed(new Runnable() {
            public void run() {
                runAfterLoaded();
            }
        }, 4000);

    }

    private void runAfterLoaded() {
        tv2.setVisibility(View.INVISIBLE);

        countdown = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv1.setText("Seconds left: " + new SimpleDateFormat("ss").format(new Date(millisUntilFinished)));
            }

            public void onFinish() {
                timesUp = true;
                tv1.setText("Seconds left: " + 0);
                tv2.setVisibility(View.VISIBLE);
                tv2.setText("How many times did a shape change colors to " + colorToWatch.toUpperCase() + "?");

                generateAnswers();
            }
        }.start();

        doAnimations();
    }

    private void generateAnswers() {
        LinkedList<Integer> fakeAnswers = new LinkedList<Integer>();

        Random rand = new Random();

        for (int i=0; i<3; i++) { //just for a nice answer pool
            int num1 = nrChanges + rand.nextInt(10) + 1;
            int num2 = nrChanges - rand.nextInt(8) - 1;

            if (fakeAnswers.contains(num1) == false) {
                fakeAnswers.add(num1);
            }
            if (fakeAnswers.contains(num2) == false) {
                fakeAnswers.add(num2);
            }
        }

        Collections.shuffle(fakeAnswers);

        int picker = rand.nextInt(4);
        ArrayList<Button> removedButtons = new ArrayList<Button>();

        Button tempBtn = answerButtons.remove(picker);

        tempBtn.setText(Integer.toString(nrChanges));

        removedButtons.add(tempBtn);

        while (answerButtons.isEmpty() == false) {
            picker = rand.nextInt(answerButtons.size());

            tempBtn = answerButtons.remove(picker);
            tempBtn.setText(Integer.toString(fakeAnswers.remove()));

            removedButtons.add(tempBtn);
        }

        answerButtons = removedButtons;

        for (Button btn: answerButtons) {
            btn.setVisibility(View.VISIBLE);
        }
    }

    private void doAnimations() {
        final Timer animTimer = new Timer();

        TimerTask animTask = new TimerTask() {
            @Override
            public void run() {
                if (timesUp == false) {
                    Random rand = new Random();
                    final Card cardToSwitch = cardList.get(rand.nextInt(cardList.size()));

                    String startingColor = colors.remove(colors.indexOf(cardToSwitch.color));

                    int colorPicker = rand.nextInt(NR_COLORS - 1);
                    int shapePicker = rand.nextInt(NR_SHAPES);

                    final Drawable chosenBackground;

                    String chosenColor = colors.get(colorPicker);
                    if (chosenColor == colorToWatch) {
                        nrChanges++;
                    }


                    switch (chosenColor) {
                        case "red":
                            chosenBackground = redImages.get(shapePicker);
                            break;
                        case "yellow":
                            chosenBackground = yellowImages.get(shapePicker);
                            break;
                        case "green":
                            chosenBackground = greenImages.get(shapePicker);
                            break;
                        default:
                            chosenBackground = blueImages.get(shapePicker);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cardToSwitch.button.setBackground(chosenBackground);
                        }
                    });
                    cardToSwitch.color = chosenColor;

                    colors.add(startingColor);
                }
                else {
                    animTimer.cancel();
                    animTimer.purge();
                }
            }
        };

        animTimer.schedule(animTask, 100, 300);
    }

    private void showCards() {
        for (int i=0; i<cardList.size(); i++) {
            Card card = cardList.remove();
            Button button = card.button;
            int id = button.getId();

            int imageIndex = cards[id / 100][id % 100];
            List<Drawable> imageColorList;
            String color;

            switch (imageIndex/NR_SHAPES) {
                case 0:
                    imageColorList = redImages;
                    color = "red";
                    break;
                case 1:
                    imageColorList = yellowImages;
                    color = "yellow";
                    break;
                case 2:
                    imageColorList = greenImages;
                    color = "green";
                    break;
                default:
                    imageColorList = blueImages;
                    color = "blue";
            }

            button.setBackground(imageColorList.get(imageIndex%NR_SHAPES));
            card.color = color;

            cardList.add(card);
        }
    }

    private TableRow createCardRow(int y) {
        TableRow row = new TableRow(context);
        row.setHorizontalGravity(Gravity.CENTER);

        for (int x = 0; x < SQUARE_SIZE; x++) {
            row.addView(createImageButton(x, y));
        }
        return row;
    }

    private View createImageButton(int x, int y) {
        Button button = new Button(context);
        button.setId(100 * x + y);

        Card card = new Card(button, x, y);
        cardList.add(card);

        return button;
    }

    private void loadImages() {
        blueImages = new ArrayList<Drawable>();
        redImages = new ArrayList<Drawable>();
        yellowImages = new ArrayList<Drawable>();
        greenImages = new ArrayList<Drawable>();

        greenImages.add(getDrawable(R.mipmap.green_circle));
        greenImages.add(getDrawable(R.mipmap.green_rect));
        greenImages.add(getDrawable(R.mipmap.green_tri));

        yellowImages.add(getDrawable(R.mipmap.yellow_circle));
        yellowImages.add(getDrawable(R.mipmap.yellow_rect));
        yellowImages.add(getDrawable(R.mipmap.yellow_tri));

        redImages.add(getDrawable(R.mipmap.red_circle));
        redImages.add(getDrawable(R.mipmap.red_rect));
        redImages.add(getDrawable(R.mipmap.red_tri));

        blueImages.add(getDrawable(R.mipmap.blue_circle));
        blueImages.add(getDrawable(R.mipmap.blue_rect));
        blueImages.add(getDrawable(R.mipmap.blue_tri));
    }

    private void loadCards() {
        try {
            int size = SQUARE_SIZE*SQUARE_SIZE;
            ArrayList<Integer> list = new ArrayList<Integer>();

            for (int i = 0; i < TOTAL_NR_SHAPES; i++) {
                list.add(new Integer(i));
            }

            Random rand = new Random();

            for (int i = size - 1; i >= 0; i--) {
                int temp = 0;

                if (i > 0) {
                    temp = rand.nextInt(i+1); //gets random integer from [0,i)
                }

                temp = list.remove(temp).intValue(); //removes and gets value of removed item in list
                cards[i % 3][i / 3] = temp % TOTAL_NR_SHAPES;
            }
        } catch (Exception e) {
            Log.e("loadCards()", e + "");
        }
    }


    class ShowModifiedHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            synchronized (LOCK) {
                showModifiedCards();
            }
        }

        private void showModifiedCards() {

        }
    }

}

