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
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryGame extends Activity {
    final private int ROWS = 2;
    final private int COLS = 3;
    final private int TOTAL_NR_SHAPES = 12;
    final private static Object LOCK = new Object();

    private int row = -1;
    private int col = -1;

    private int[][] cards;
    private Drawable removedCardImage;
    private int removalIndex;
    private List<Drawable> images;
    Set<Drawable> triangles;
    Set<Drawable> squares;
    Set<Drawable> circles;

    private LinkedList<Card> cardQueue;
    private LinkedList<Drawable> fakeAnswerPool = new LinkedList<Drawable>();
    private Context context;
    private Drawable backImage;

    private TableLayout mainTable;
    private ShowCardsHandler showCardsHandler;
    private ShowModifiedHandler showModifiedHandler;
    private Handler handler = new Handler();

    private Button endGame;

    private ImageButton [] answerButtons = new ImageButton[4];
    private int [] greyShapes = {R.mipmap.grey_circle, R.mipmap.grey_triangle, R.mipmap.grey_rectangle};

    int roundsLeft = 3;

    private void checkSelection(ImageButton button){
        if (removedCardImage == button.getBackground()) {
            cardQueue.get(removalIndex).button.setBackground(removedCardImage);
            mainTable.setVisibility(View.INVISIBLE);
            roundsLeft--;

            for (ImageButton btn: answerButtons) {
                btn.setEnabled(true);
                btn.setVisibility(View.INVISIBLE);
            }

            if (roundsLeft == 0) {
                endGame.setVisibility(View.VISIBLE);
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
                ((TextView)findViewById(R.id.tv2)).setText("Correct! Another round coming!");
                handler.postDelayed(new Runnable() {
                    public void run() {
                        startGame();
                    }
                }, 3000);
            }
        } else {
            button.setEnabled(false);

            if (triangles.contains(button.getBackground())) {
                button.setBackground(getDrawable(greyShapes[1]));
            }
            else if (circles.contains(button.getBackground())) {
                button.setBackground(getDrawable(greyShapes[0]));
            }
            else {
                button.setBackground(getDrawable(greyShapes[2]));
            }


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

        backImage = getDrawable(R.mipmap.back_image);

        mainTable = (TableLayout) findViewById(R.id.TableLayout03);
        context = mainTable.getContext();

        endGame = (Button) findViewById(R.id.endbutton);
        endGame.setVisibility(View.INVISIBLE);

        answerButtons[0] = (ImageButton) findViewById(R.id.answer1);
        answerButtons[1] = (ImageButton) findViewById(R.id.answer2);
        answerButtons[2] = (ImageButton) findViewById(R.id.answer3);
        answerButtons[3] = (ImageButton) findViewById(R.id.answer4);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btn = (ImageButton) view;
                checkSelection(btn);
            }
        };

        for (ImageButton btn: answerButtons) {
            btn.setOnClickListener(listener);
            btn.setVisibility(View.INVISIBLE);
        }

        startGame();
    }

    public void startGame() {
        cardQueue = new LinkedList<Card>();

        mainTable.setVisibility(View.VISIBLE);

        cards = new int[COLS][ROWS];
        TableRow tr = ((TableRow) findViewById(R.id.TableRow03));
        tr.removeAllViews();
        mainTable = new TableLayout(context);
        tr.addView(mainTable);

        for (int y = 0; y < ROWS; y++) {
            mainTable.addView(createCardRow(y));
        }

        findViewById(R.id.tv2).setVisibility(View.INVISIBLE);
        loadCards();
        ((TextView) findViewById(R.id.tv1)).setText("Rounds left: " + roundsLeft);
        showCards();
    }

    private void loadImages() {
        images = new ArrayList<Drawable>();

        triangles = new HashSet<Drawable>();
        triangles.add(getDrawable(R.mipmap.yellow_tri));
        triangles.add(getDrawable(R.mipmap.green_tri));
        triangles.add(getDrawable(R.mipmap.red_tri));
        triangles.add(getDrawable(R.mipmap.blue_tri));

        squares = new HashSet<Drawable>();
        squares.add(getDrawable(R.mipmap.yellow_rect));
        squares.add(getDrawable(R.mipmap.green_rect));
        squares.add(getDrawable(R.mipmap.red_rect));
        squares.add(getDrawable(R.mipmap.blue_rect));

        circles = new HashSet<Drawable>();
        circles.add(getDrawable(R.mipmap.yellow_circle));
        circles.add(getDrawable(R.mipmap.green_circle));
        circles.add(getDrawable(R.mipmap.red_circle));
        circles.add(getDrawable(R.mipmap.blue_circle));

        for (Drawable circle: circles) {
            images.add(circle);
        }
        for (Drawable square: squares) {
            images.add(square);
        }
        for (Drawable tri: triangles) {
            images.add(tri);
        }
    }

    private void showCards() {
        for (int i=0; i<cardQueue.size(); i++) {
            Card card = cardQueue.remove();
            ImageButton button = card.button;
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

        timer.schedule(showModifiedTask, 10000);
    }

    private void loadCards() {
        try {
            int size = ROWS*COLS;
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

        for (int x = 0; x < COLS; x++) {
            row.addView(createImageButton(x, y));
        }
        return row;
    }

    private View createImageButton(int x, int y) {
        ImageButton button = new ImageButton(context);
        button.setId(100 * x + y);

        int pixelDimension = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());

        TableRow.LayoutParams params = new TableRow.LayoutParams(pixelDimension, pixelDimension);
        params.setMargins(8, 8, 8, 8);
        button.setLayoutParams(params);

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
                ImageButton button = card.button;
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

            for (int i=0; i<answerButtons.length; i++) {
                answerButtons[i].setBackground(answers.get(i));
                answerButtons[i].setVisibility(View.VISIBLE);
            }
            findViewById(R.id.tv2).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.tv2)).setText("The missing item is... ");

        }
    }

}
