package wakeup.wakeupapp;

/**
 * Created by Rebekah on 2/15/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MatchingGame extends Activity {
    final private int NR_ROWS = 4;
    final private int NR_COLS = 3;
    final private int TOTAL_NR_SHAPES = NR_ROWS*NR_COLS;
    final private static Object LOCK = new Object();

    private int row = -1;
    private int col = -1;

    private int[][] cards;
    private Card firstCard;
    private Card secondCard;
    private List<Drawable> images;
    private Context context;
    private Drawable backImage;

    private ButtonListener buttonListener;
    private TableLayout mainTable;
    private UpdateCardsHandler handler;
    private Button endGame;

    int turns;
    int cardsLeft = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new UpdateCardsHandler();
        loadImages();
        setContentView(R.layout.matching_game);

        backImage = getDrawable(R.mipmap.back_image);

        buttonListener = new ButtonListener();
        mainTable = (TableLayout) findViewById(R.id.TableLayout03); //wtf is TableLayout03
        context = mainTable.getContext();

        endGame = (Button) findViewById(R.id.endbutton);
        endGame.setVisibility(View.INVISIBLE);

        startGame();
    }

    public void startGame() {
        cards = new int[NR_COLS][NR_ROWS];
        TableRow tr = ((TableRow) findViewById(R.id.TableRow03));
        tr.removeAllViews();
        mainTable = new TableLayout(context);
        tr.addView(mainTable);

        for (int y = 0; y < NR_ROWS; y++) {
            mainTable.addView(createCardRow(y));
        }

        firstCard = null;
        secondCard = null;

        loadCards();

        turns = 0;
        ((TextView) findViewById(R.id.tv1)).setText("Tries: " + turns);
    }

    private void loadImages() {
        images = new ArrayList<Drawable>();

        images.add(getDrawable(R.mipmap.green_circle));
        images.add(getDrawable(R.mipmap.green_rect));
        images.add(getDrawable(R.mipmap.green_tri));

        images.add(getDrawable(R.mipmap.yellow_circle));
        images.add(getDrawable(R.mipmap.yellow_rect));
        images.add(getDrawable(R.mipmap.yellow_tri));

        images.add(getDrawable(R.mipmap.red_circle));
        images.add(getDrawable(R.mipmap.red_rect));
        images.add(getDrawable(R.mipmap.red_tri));

        images.add(getDrawable(R.mipmap.blue_circle));
        images.add(getDrawable(R.mipmap.blue_rect));
        images.add(getDrawable(R.mipmap.blue_tri));
    }

    private void loadCards() {
        try {
            int size = NR_COLS*NR_ROWS;
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
                cards[i % NR_COLS][i / NR_COLS] = temp % (TOTAL_NR_SHAPES / 2);
                cardsLeft++;
            }
        } catch (Exception e) {
            Log.e("loadCards()", e + "");
        }

    }

    private TableRow createCardRow(int y) {
        TableRow row = new TableRow(context);
        row.setHorizontalGravity(Gravity.CENTER);

        for (int x = 0; x < NR_COLS; x++) {
            row.addView(createImageButton(x, y));
        }
        return row;
    }

    private View createImageButton(int x, int y) {
        ImageButton button = new ImageButton(context);
        button.setBackground(backImage);
        button.setId(100 * x + y);
        button.setOnClickListener(buttonListener);

        int pixelDimension = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());

        TableRow.LayoutParams params = new TableRow.LayoutParams(pixelDimension, pixelDimension);
        params.setMargins(8, 8, 8, 8);
        button.setLayoutParams(params);

        return button;
    }


    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            synchronized (LOCK) {
                if (firstCard != null && secondCard != null) {
                    return;
                }

                int id = view.getId();
                int x = id / 100;
                int y = id % 100;

                turnCard((ImageButton) view, x, y);
            }
        }

        private void turnCard(ImageButton button, int x, int y) {
            button.setBackground(images.get(cards[x][y]));

            if (firstCard == null) { //haven't clicked a card yet
                firstCard = new Card(button, x, y);
            } else {
                if (firstCard.x == x && firstCard.y == y) {
                    return; //the user pressed the same card
                }

                secondCard = new Card(button, x, y);

                turns++;
                ((TextView) findViewById(R.id.tv1)).setText("Tries: " + turns);

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
                t.schedule(tt, 1300);
            }
        }
    }

    class UpdateCardsHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            synchronized (LOCK) {
                checkCards();
            }
        }

        public void checkCards() {

            if (cards[firstCard.x][firstCard.y] == cards[secondCard.x][secondCard.y]) { //if match
                firstCard.button.setVisibility(View.INVISIBLE);
                secondCard.button.setVisibility(View.INVISIBLE);
                cardsLeft = cardsLeft - 2;
            } else {
                firstCard.button.setBackground(backImage);
                secondCard.button.setBackground(backImage);
            }

            firstCard = null;
            secondCard = null;

            if (cardsLeft == 0) {
                endGame.setVisibility(View.VISIBLE);


                endGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }
    }

}
