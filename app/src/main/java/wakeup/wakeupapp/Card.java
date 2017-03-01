package wakeup.wakeupapp;

/**
 * Created by Rebekah on 2/15/2017.
 */


import android.widget.Button;

public class Card
{
    public int x;
    public int y;
    public Button button;

    public Card(Button button, int x, int y) {
        this.x = x;
        this.y = y;
        this.button = button;
    }
}
