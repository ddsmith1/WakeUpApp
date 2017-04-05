package wakeup.wakeupapp;

/**
 * Created by Rebekah on 2/15/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class SortingGame extends Activity {
    private final int NR_IMAGES = 12;
    private final int NR_ITEMS_TO_SORT = 20;

    private Stack<SortingItem> items;
    private List<SortingItem> images;
    private Context context;

    private ImageView imageSpot;
    private Button endGame;

    private ArrayList<Button> buttons;

    int itemsLeft = NR_ITEMS_TO_SORT;
    int nrItemsSinceSwitch = 0;
    String currentSortType = "shape";

    private class SortingItem
    {
        public String shape;
        public String color;
        public Drawable drawable;

        public SortingItem(Drawable drawable, String color, String shape) {
            this.drawable = drawable;
            this.color = color;
            this.shape = shape;
        }
    }

    public void checkSorting(View view) {
        String text = ((Button) view).getText().toString();
        if (items.peek().color.equals(text.toLowerCase()) || items.peek().shape.equals(text.toLowerCase())) { //maybe also shape?
            items.pop();
            itemsLeft--;

            for (Button btn: buttons) {
                btn.setEnabled(true);
            }

            if (items.empty()) {
                for (Button btn: buttons) {
                    btn.setVisibility(View.GONE);
                }
                endGame.setVisibility(View.VISIBLE);
                imageSpot.setVisibility(View.INVISIBLE);
                endGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            } else {
                setNextDrawable();
            }
        } else {
            ((Button) view).setEnabled(false);
//            if (items.size() < 10) {
//                items.push(images.get(new Random().nextInt(images.size())));
//                itemsLeft++;
//            }
        }

        ((TextView) findViewById(R.id.tv1)).setText("Items left: " + itemsLeft);
    }

    private void setNextDrawable() {
        imageSpot.setImageDrawable(items.peek().drawable);

        buttons = new ArrayList<Button>();
        Boolean nextIsShapeSort;

        if (nrItemsSinceSwitch >= 3) {
            if (currentSortType.equals("shape")) {
                nextIsShapeSort = false;
            }
            else {
                nextIsShapeSort = true;
            }
        }
        else {
            nextIsShapeSort = new Random().nextBoolean();
        }

        if (nextIsShapeSort) {
            if (!currentSortType.equals("shape")) {
                buttons.add((Button) findViewById(R.id.redSort));
                buttons.add((Button) findViewById(R.id.yellowSort));
                buttons.add((Button) findViewById(R.id.greenSort));
                buttons.add((Button) findViewById(R.id.blueSort));

                currentSortType = "shape";
            }
            else {
                nrItemsSinceSwitch++;
            }

            findViewById(R.id.shapeButtons1).setVisibility(View.GONE);
            findViewById(R.id.shapeButtons2).setVisibility(View.GONE);

            findViewById(R.id.colorButtons1).setVisibility(View.VISIBLE);
            findViewById(R.id.colorButtons2).setVisibility(View.VISIBLE);
        }
        else {
            if (!currentSortType.equals("color")) {
                buttons.add((Button) findViewById(R.id.triangleSort));
                buttons.add((Button) findViewById(R.id.circleSort));
                buttons.add((Button) findViewById(R.id.squareSort));

                currentSortType = "color";
            }
            else {
                nrItemsSinceSwitch++;
            }

            findViewById(R.id.shapeButtons1).setVisibility(View.VISIBLE);
            findViewById(R.id.shapeButtons2).setVisibility(View.VISIBLE);

            findViewById(R.id.colorButtons1).setVisibility(View.GONE);
            findViewById(R.id.colorButtons2).setVisibility(View.GONE);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadImages();
        setContentView(R.layout.sorting_game);

        imageSpot = (ImageView) findViewById(R.id.ImageView);
        context = imageSpot.getContext();

        endGame = (Button) findViewById(R.id.endbutton);
        endGame.setVisibility(View.INVISIBLE);

        startGame();
    }

    public void startGame() {
        items = new Stack<SortingItem>();
        TableRow tr = ((TableRow) findViewById(R.id.TableRow03));
        tr.removeAllViews();
        imageSpot = new ImageView(context);
        tr.addView(imageSpot);

        loadItems();
        setNextDrawable();
        ((TextView) findViewById(R.id.tv1)).setText("Items left: " + itemsLeft);
    }

    private void loadImages() {
        images = new ArrayList<SortingItem>();
        Drawable image;


        image = getDrawable(R.mipmap.green_circle);
        SortingItem item1 = new SortingItem(image, "green", "circle");
        images.add(item1);

        image = getDrawable(R.mipmap.green_rect);
        SortingItem item2 = new SortingItem(image, "green", "square");
        images.add(item2);

        image = getDrawable(R.mipmap.green_tri);
        SortingItem item3 = new SortingItem(image, "green", "triangle");
        images.add(item3);


        image = getDrawable(R.mipmap.yellow_circle);
        SortingItem item4 = new SortingItem(image, "yellow", "circle");
        images.add(item4);

        image = getDrawable(R.mipmap.yellow_rect);
        SortingItem item5 = new SortingItem(image, "yellow", "square");
        images.add(item5);

        image = getDrawable(R.mipmap.yellow_tri);
        SortingItem item6 = new SortingItem(image, "yellow", "triangle");
        images.add(item6);


        image = getDrawable(R.mipmap.blue_circle);
        SortingItem item7 = new SortingItem(image, "blue", "circle");
        images.add(item7);

        image = getDrawable(R.mipmap.blue_rect);
        SortingItem item8 = new SortingItem(image, "blue", "square");
        images.add(item8);

        image = getDrawable(R.mipmap.blue_tri);
        SortingItem item9 = new SortingItem(image, "blue", "triangle");
        images.add(item9);


        image = getDrawable(R.mipmap.red_circle);
        SortingItem item10 = new SortingItem(image, "red", "circle");
        images.add(item10);

        image = getDrawable(R.mipmap.red_rect);
        SortingItem item11 = new SortingItem(image, "red", "square");
        images.add(item11);

        image = getDrawable(R.mipmap.red_tri);
        SortingItem item12 = new SortingItem(image, "red", "triangle");
        images.add(item12);
    }

    private void loadItems() {
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();

            for (int i = 0; i < NR_IMAGES; i++) {
                list.add(new Integer(i));
            }

            Random rand = new Random();

            for (int i = NR_IMAGES - 1; i >= 0; i--) {
                int temp = 0;

                if (i > 0) {
                    temp = rand.nextInt(i+1);
                }

                temp = list.remove(temp).intValue();

                items.push(images.get(temp));
            }

            for (int i=items.size(); i<NR_ITEMS_TO_SORT; i++) {
                SortingItem itemToAddAgain = items.get(rand.nextInt(items.size()));
                items.push(itemToAddAgain);
            }
        } catch (Exception e) {
            Log.e("loadItems()", e + "");
        }

    }

}
