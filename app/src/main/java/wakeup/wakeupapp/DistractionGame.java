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
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DistractionGame extends Activity {
    final private int SQUARE_SIZE = 3;
    final private static Object LOCK = new Object();

    private int row = -1;
    private int col = -1;

    private int[][] cards;
    private Drawable removedCardImage;
    private int removalIndex;
    private List<Drawable> blueImages;
    private List<Drawable> redImages;
    private List<Drawable> greenImages;
    private List<Drawable> yellowImages;
    private Context context;
    private Drawable backImage;

    private TableLayout mainTable;

    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private Button endGame;

    private void checkSelection(Button button) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    }

    public void startGame() {
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
    }

}

