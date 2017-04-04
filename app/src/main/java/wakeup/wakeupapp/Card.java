package wakeup.wakeupapp;

/**
 * Created by Rebekah on 2/15/2017.
 */


import android.widget.Button;
import android.widget.ImageButton;

public class Card
{
    public int x;
    public int y;
    public ImageButton button;
    public String color;

    public Card(ImageButton button, int x, int y) {
        this.x = x;
        this.y = y;
        this.button = button;
    }

    public Card(ImageButton button, int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.button = button;
        this.color = color;
    }
}
