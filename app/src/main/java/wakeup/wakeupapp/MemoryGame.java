package wakeup.wakeupapp;

/**
 * Created by Rebekah on 2/15/2017.
 */

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryGame extends Activity {
    final private int SQUARE_SIZE = 4;
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

    int turns;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new UpdateCardsHandler();
        loadImages();
        setContentView(R.layout.memory_game);

        backImage = getDrawable(R.drawable.back);

        buttonListener = new ButtonListener();
        mainTable = (TableLayout) findViewById(R.id.TableLayout03); //wtf is TableLayout03
        context = mainTable.getContext();

        startGame();
    }

    public void startGame() {
        cards = new int[SQUARE_SIZE][SQUARE_SIZE];
        TableRow tr = ((TableRow) findViewById(R.id.TableRow03));
        tr.removeAllViews();
        mainTable = new TableLayout(context);
        tr.addView(mainTable);

        for (int y = 0; y < SQUARE_SIZE; y++) {
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

        images.add(getDrawable(R.drawable.card1));
        images.add(getDrawable(R.drawable.card2));
        images.add(getDrawable(R.drawable.card3));
        images.add(getDrawable(R.drawable.card4));
        images.add(getDrawable(R.drawable.card5));
        images.add(getDrawable(R.drawable.card6));
        images.add(getDrawable(R.drawable.card7));
        images.add(getDrawable(R.drawable.card8));
    }

    private void loadCards() {
        try {
            int size = SQUARE_SIZE * SQUARE_SIZE;
            ArrayList<Integer> list = new ArrayList<Integer>();

            for (int i = 0; i < size; i++) {
                list.add(new Integer(i)); //creates list of 0-15
            }

            Random rand = new Random();

            for (int i = size - 1; i >= 0; i--) {
                int temp = 0;

                if (i > 0) {
                    temp = rand.nextInt(i); //gets random integer from [0,j)
                }

                temp = list.remove(temp).intValue(); //removes and gets value of removed item in list
                cards[i % SQUARE_SIZE][i / SQUARE_SIZE] = temp % (size / 2);
            }
        } catch (Exception e) {
            Log.e("loadCards()", e + "");
        }

    }

    private TableRow createCardRow(int y) {
        TableRow row = new TableRow(context);
        row.setHorizontalGravity(Gravity.CENTER);

        for (int x = 0; x < 3; x++) {
            row.addView(createImageButton(x, y));
        }
        return row;
    }

    private View createImageButton(int x, int y) {
        Button button = new Button(context);
        button.setBackground(backImage);
        button.setId(100 * x + y);
        button.setOnClickListener(buttonListener);

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

                turnCard((Button) view, x, y);
            }
        }

        private void turnCard(Button button, int x, int y) {
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
            } else {
                firstCard.button.setBackground(backImage);
                secondCard.button.setBackground(backImage);
            }

            firstCard = null;
            secondCard = null;
        }
    }

}
