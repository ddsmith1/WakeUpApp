package wakeup.wakeupapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class Help extends AppCompatActivity {
    private HashMap<String, String> namesToDescriptions = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        namesToDescriptions.put("Categories", "A grid of shapes of various colors will be displayed, "
                + "and instructions will be given on a category of shapes to tap, either based on color or shape."
                + " Try to go quickly, but be careful not to tap shapes that don’t fall into the given category!\n" +
                "\n" + "This game focuses on reaction speed, visual discrimination, and categorization.");
        namesToDescriptions.put("Color", "A color word will be displayed but shown in another color. Press the button displaying"
                + " the color the word is shown in, not the color the text says. For example, the word “BLUE\" may be written"
                + " in red. The correct button choice would be red.\n" +
                "\n" + "This game focuses on visual discrimination.");
        namesToDescriptions.put("Distract", "A grid of shapes will be displayed. Directions will be displayed telling which color"
                + " change to look for. If the directions say to \"count the number of shapes that change colors to GREEN,” pay"
                + " attention to only those changes, not any other shape changes. When time is up, you will be asked how many shapes"
                + " changed color to the previously specified color.\n" +
                "\n" + "This game focuses on selective attention (concentrating on one thing while many things are happening).");
        namesToDescriptions.put("Match", "A grid of items will be displayed. Click an item to reveal an image. Then click "
                + "another. If the two images match, those two cards disappear. If not, the images are hidden, and you have "
                + "to try again. Match pairs of images until they’re all gone. Try to take as few turns as possible!\n" +
                "\n" + "This game focuses on memory.");
        namesToDescriptions.put("Math", "Four buttons are displayed, each with either a single number, or a simple"
                + " arithmetic operation. Choose the button with the highest numerical value.\n" +
                "\n" + "This game focuses on arithmetic relations.");
        namesToDescriptions.put("Memory", "A grid of shapes will be displayed. Pay attention and remember what and where"
                + " they are because after a few seconds, all shapes are hidden. Then a couple seconds later, all shapes"
                + " but one are displayed. You will have to click the button corresponding to the shape that’s missing"
                + " from its spot.\n" + "\n" + "This game focuses on memory.");
        namesToDescriptions.put("Operators", "A mathematical expression is shown with the operator removed. Choose the operator"
                + " that makes the expression true. For example, if the expression were 4 ? 5 = 20, press the X button to indicate"
                + " multiplication.\n" + "\n" + "This game focuses on arithmetic relations.");
        namesToDescriptions.put("Pattern", "A series of numbers will be displayed. These numbers are related by some simple"
                + " mathematical operation. Identify the pattern and select the next number in the sequence.\n" +
                "\n" + "This game focuses on patterns and arithmetic relations.");
        namesToDescriptions.put("React", "One by one, various shapes will be displayed. Quickly, indicate whether the shape "
                + "is the same as the previous one or different.\n" +
                "\n" + "This game focuses on reaction speed and visual discrimination.");
        namesToDescriptions.put("ReflexGame", "A grid of shapes will be displayed. The items will change, and you have to tap them"
                + " quickly when they do, before the next item changes. Be quick, but don’t tap items that didn’t just change.\n" +
                "\n" + "This game focuses on reaction speed.");
        namesToDescriptions.put("Recreate", "A grid of squares will be displayed, some red and some blue."
                + " Take note of the location of the blue squares. When you’re ready, press the button to continue to a "
                + "grid of all red squares. Select a square to change it to blue until the grid’s pattern matches the previous"
                + " red and blue grid.\n" + "\n" + "This game focuses on pattern-matching and memory.");
        namesToDescriptions.put("Sort", "One by one, shapes of various colors will be displayed. You have to sort each image by "
                + "selecting either what shape the current image is or what color. The game ends when all items have been sorted.\n" +
                "\n" + "This game focuses on visual discrimination and categorization.");
    }

    public void displayHelpInfo(View view) {
        LinearLayout parent;
        String viewType = view.getClass().getName();
        if (viewType.equalsIgnoreCase("android.support.v7.widget.AppCompatImageButton")) {
            parent = (LinearLayout) view.getParent();
        }
        else {
            parent = (LinearLayout) view;
        }
        String gameTitle = ((TextView) parent.getChildAt(1)).getText().toString();
        AlertDialog alertDialog = new AlertDialog.Builder(Help.this).create();
        alertDialog.setTitle(gameTitle);
        alertDialog.setMessage(namesToDescriptions.get(gameTitle));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
        
    }
}
