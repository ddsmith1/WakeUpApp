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
    private Stack<SortingItem> items;
    private List<SortingItem> images;
    private Context context;

    private ImageView imageSpot;
    private Button endGame;
    private Button leftSort;
    private Button rightSort;

    int itemsLeft = 0;

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

    private void checkSorting(String text) {
        //Should be something real instead
        if (items.peek().color.equals(text.toLowerCase())) { //maybe also shape?
            items.pop();
            itemsLeft--;

            if (items.empty()) {
                endGame.setVisibility(View.VISIBLE);
                leftSort.setVisibility(View.INVISIBLE);
                rightSort.setVisibility(View.INVISIBLE);
                imageSpot.setVisibility(View.INVISIBLE);
                endGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            } else {
                imageSpot.setImageDrawable(items.peek().drawable);
            }
        } else {
//                items.push(createCard()); //maybe later?
        }

        ((TextView) findViewById(R.id.tv1)).setText("Items left: " + itemsLeft);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadImages();
        setContentView(R.layout.sorting_game);

        imageSpot = (ImageView) findViewById(R.id.ImageView);
        context = imageSpot.getContext();

        leftSort = (Button) findViewById(R.id.leftSort);
        rightSort = (Button) findViewById(R.id.rightSort);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                checkSorting(btn.getText().toString());
            }
        };

        leftSort.setOnClickListener(listener);
        rightSort.setOnClickListener(listener);


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
        imageSpot.setImageDrawable(items.peek().drawable);
        ((TextView) findViewById(R.id.tv1)).setText("Items left: " + itemsLeft);
    }

    private void loadImages() {
        images = new ArrayList<SortingItem>();
        Drawable image;


        image = getDrawable(R.mipmap.green_circle);
        SortingItem item1 = new SortingItem(image, "green", "circle");
        images.add(item1);

        image = getDrawable(R.mipmap.green_rect);
        SortingItem item2 = new SortingItem(image, "green", "rectangle");
        images.add(item2);

        image = getDrawable(R.mipmap.green_tri);
        SortingItem item3 = new SortingItem(image, "green", "triangle");
        images.add(item3);


        image = getDrawable(R.mipmap.yellow_circle);
        SortingItem item4 = new SortingItem(image, "yellow", "circle");
        images.add(item4);

        image = getDrawable(R.mipmap.yellow_rect);
        SortingItem item5 = new SortingItem(image, "yellow", "rectangle");
        images.add(item5);

        image = getDrawable(R.mipmap.yellow_tri);
        SortingItem item6 = new SortingItem(image, "yellow", "triangle");
        images.add(item6);


        image = getDrawable(R.mipmap.blue_circle);
        SortingItem item7 = new SortingItem(image, "blue", "circle");
        images.add(item7);

        image = getDrawable(R.mipmap.blue_rect);
        SortingItem item8 = new SortingItem(image, "blue", "rectangle");
        images.add(item8);

        image = getDrawable(R.mipmap.blue_tri);
        SortingItem item9 = new SortingItem(image, "blue", "triangle");
        images.add(item9);


        image = getDrawable(R.mipmap.red_circle);
        SortingItem item10 = new SortingItem(image, "red", "circle");
        images.add(item10);

        image = getDrawable(R.mipmap.red_rect);
        SortingItem item11 = new SortingItem(image, "red", "rectangle");
        images.add(item11);

        image = getDrawable(R.mipmap.red_tri);
        SortingItem item12 = new SortingItem(image, "red", "triangle");
        images.add(item12);
    }

    private void loadItems() {
        try {
            int size = 8;
            ArrayList<Integer> list = new ArrayList<Integer>();

            for (int i = 0; i < size; i++) {
                list.add(new Integer(i)); //creates list of 0-7
            }

            Random rand = new Random();

            for (int i = size - 1; i >= 0; i--) {
                int temp = 0;

                if (i > 0) {
                    temp = rand.nextInt(i+1); //gets random integer from [0,i)
                }

                temp = list.remove(temp).intValue(); //removes and gets value of removed item in list

                items.push(images.get(temp));
                itemsLeft++;
            }
        } catch (Exception e) {
            Log.e("loadItems()", e + "");
        }

    }

}
