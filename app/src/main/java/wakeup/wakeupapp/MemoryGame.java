package wakeup.wakeupapp;

/**
 * Created by Rebekah on 2/15/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryGame extends Activity {
    final private int SQUARE_SIZE = 3;
    final private int TOTAL_NR_SHAPES = 12;
    final private static Object LOCK = new Object();

    private int row = -1;
    private int col = -1;

    private int[][] cards;
    private Drawable removedCardImage;
    private int removalIndex;
    private List<Drawable> images;
    private LinkedList<Card> cardQueue;
    private LinkedList<Drawable> fakeAnswerPool = new LinkedList<Drawable>();
    private Context context;
    private Drawable backImage;

    private TableLayout mainTable;
    private ShowCardsHandler showCardsHandler;
    private ShowModifiedHandler showModifiedHandler;

    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private Button endGame;

    int roundsLeft = 3;

    private void checkSelection(Button button){
        if (removedCardImage == button.getBackground()) { //maybe also shape?
            cardQueue.get(removalIndex).button.setBackground(removedCardImage);
            mainTable.setVisibility(View.INVISIBLE);
            roundsLeft--;
            answer1.setEnabled(true);
            answer2.setEnabled(true);
            answer3.setEnabled(true);
            answer4.setEnabled(true);
            Toast toast = Toast.makeText(context, "Correct", Toast.LENGTH_LONG);
            toast.show();

            if (roundsLeft == 0) {
                endGame.setVisibility(View.VISIBLE);
                answer1.setVisibility(View.INVISIBLE);
                answer2.setVisibility(View.INVISIBLE);
                answer3.setVisibility(View.INVISIBLE);
                answer4.setVisibility(View.INVISIBLE);
                mainTable.setVisibility(View.GONE);
                findViewById(R.id.tv2).setVisibility(View.GONE);
                findViewById(R.id.tv1).setVisibility(View.GONE);
                endGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            } else {
                startGame();
            }
        } else {
            button.setEnabled(false);
            if (roundsLeft < 3) {
                roundsLeft++;
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showCardsHandler = new ShowCardsHandler();
        showModifiedHandler = new ShowModifiedHandler();
        loadImages();
        setContentView(R.layout.memory_game);

        backImage = getDrawable(R.drawable.back);

        mainTable = (TableLayout) findViewById(R.id.TableLayout03);
        context = mainTable.getContext();

        endGame = (Button) findViewById(R.id.endbutton);
        endGame.setVisibility(View.INVISIBLE);

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                checkSelection(btn);
            }
        };

        answer1.setOnClickListener(listener);
        answer2.setOnClickListener(listener);
        answer3.setOnClickListener(listener);
        answer4.setOnClickListener(listener);

        startGame();
    }

    public void startGame() {
        cardQueue = new LinkedList<Card>();
        mainTable.setVisibility(View.VISIBLE);
        answer1.setVisibility(View.INVISIBLE);
        answer2.setVisibility(View.INVISIBLE);
        answer3.setVisibility(View.INVISIBLE);
        answer4.setVisibility(View.INVISIBLE);

        cards = new int[SQUARE_SIZE][SQUARE_SIZE];
        TableRow tr = ((TableRow) findViewById(R.id.TableRow03));
        tr.removeAllViews();
        mainTable = new TableLayout(context);
        tr.addView(mainTable);

        for (int y = 0; y < SQUARE_SIZE; y++) {
            mainTable.addView(createCardRow(y));
        }

        findViewById(R.id.tv2).setVisibility(View.INVISIBLE);
        loadCards();
        ((TextView) findViewById(R.id.tv1)).setText("Rounds left: " + roundsLeft);
        showCards();
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
        //TODO: should be distinct images, and should have at least 12 so 3 can be the decoy answers
        images.add(getDrawable(R.drawable.card8));

        images.add(getDrawable(R.drawable.card1));
        images.add(getDrawable(R.drawable.card2));
        images.add(getDrawable(R.drawable.card3));
    }

    private void showCards() {
        for (int i=0; i<cardQueue.size(); i++) {
            Card card = cardQueue.remove();
            Button button = card.button;
            int id = button.getId();
            int x = id / 100;
            int y = id % 100;

            button.setBackground(images.get(cards[x][y]));

            cardQueue.add(card);
        }

        TimerTask showCardsTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    synchronized (LOCK) {
                        showCardsHandler.sendEmptyMessage(0);
                    }
                } catch (Exception e) {
                    Log.e("E1", e.getMessage());
                }
            }
        };

        Timer timer = new Timer(false);
        timer.schedule(showCardsTask, 5000);

        TimerTask showModifiedTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    synchronized (LOCK) {
                        showModifiedHandler.sendEmptyMessage(0);
                    }
                } catch (Exception e) {
                    Log.e("E1", e.getMessage());
                }
            }
        };

        timer.schedule(showModifiedTask, 7500);
    }

    private void loadCards() {
        try {
            int size = SQUARE_SIZE * SQUARE_SIZE;
            ArrayList<Integer> list = new ArrayList<Integer>();

            for (int i = 0; i < TOTAL_NR_SHAPES; i++) {
                list.add(new Integer(i));
            }

            Collections.shuffle(list);
            Random rand = new Random();

            for (int i = size - 1; i >= 0; i--) {
                int temp = 0;

                if (i > 0) {
                    temp = rand.nextInt(i+1);
                }

                temp = list.remove(temp).intValue();
                cards[i % 3][i / 3] = temp % TOTAL_NR_SHAPES;
            }

            for (int i = 0; i<list.size(); i++) {
                int value = list.get(i).intValue();

                fakeAnswerPool.add(images.get(value));
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
        button.setId(100 * x + y);
        Card card = new Card(button, x, y);
        cardQueue.add(card);

        return button;
    }

    class ShowCardsHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            synchronized (LOCK) {
                hideCards();
            }
        }

        private void hideCards() {
            for (int i=0; i<cardQueue.size(); i++) {
                cardQueue.get(i).button.setBackground(backImage);
            }

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
            Random rand = new Random();
            removalIndex = rand.nextInt(cardQueue.size());

            for (int i=0; i<cardQueue.size(); i++) {
                Card card = cardQueue.get(i);
                Button button = card.button;
                int id = button.getId();
                int x = id / 100;
                int y = id % 100;

                if (i == removalIndex) {
                    removedCardImage = images.get(cards[x][y]);
                    button.setBackground(backImage);
                } else {
                    button.setBackground(images.get(cards[x][y]));
                }
            }

            generateFakeAnswers();

        }

        private void generateFakeAnswers() {
            Random rand = new Random();
            int picker;

            LinkedList<Drawable> answers = new LinkedList<Drawable>();
            answers.add(removedCardImage);

            for (int i=0; i<3; i++) {
                picker = rand.nextInt(fakeAnswerPool.size());
                answers.add(fakeAnswerPool.remove(picker));
            }


            Collections.shuffle(answers);

            Button[] buttons = {answer1, answer2, answer3, answer4};

            for (int i=0; i<4; i++) {

                buttons[i].setBackground(answers.get(i));
                buttons[i].setVisibility(View.VISIBLE);
            }
            findViewById(R.id.tv2).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.tv2)).setText("The missing item is... ");

        }
    }

}
