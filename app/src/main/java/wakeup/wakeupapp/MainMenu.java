package wakeup.wakeupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Random;

public class MainMenu extends AppCompatActivity {

    private ImageButton alarmSet;
    private ImageButton practiceMenu;
    private ImageButton startActivity;
    private ImageButton help;
    final int NUM_CHOICES = 3;
    final int NUM_GAMES = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        alarmSet = (ImageButton) findViewById(R.id.alarmSet);
        practiceMenu = (ImageButton) findViewById(R.id.practiceMenu);
        startActivity = (ImageButton) findViewById(R.id.startActivity);
        help = (ImageButton) findViewById(R.id.help);

       alarmSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AlarmSet.class);
                startActivity(intent);
            }
        });

        practiceMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, PracticeMenu.class);
                startActivity(intent);
            }
        });

        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();

                //hold previous game selections here to avoid repeats
                int choices[] = new int[NUM_CHOICES];


                for(int i = 0; i < NUM_CHOICES; i++) { //loop to pick game choices
                    int choice;

                    while (true) { //loop to check for repeated choices
                        boolean flag = true;
                        choice = random.nextInt(NUM_GAMES) + 1;
                        for (int j = i - 1; j >= 0; j--) { //look for repeated choice from previous choices
                            if (choices[j] == choice) {
                                flag = false; //if repeated choice, set flag to false
                            }
                        }

                        if (flag) { //if no repeated choice, break from while statement
                            choices[i] = choice;
                            break;
                        }
                    }
                }

                for(int i = 0; i < NUM_CHOICES; i++) {
                    Intent intent = null;
                    switch (choices[i]) { //switch for different games, currently only one
                        case 1:
                            intent = new Intent(MainMenu.this, ColorGame.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(MainMenu.this, DistractionGame.class);
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(MainMenu.this, MatchingGame.class);
                            startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent(MainMenu.this, MathGame.class);
                            startActivity(intent);
                            break;
                        case 5:
                            intent = new Intent(MainMenu.this, MemoryGame.class);
                            startActivity(intent);
                            break;
                        case 6:
                            intent = new Intent(MainMenu.this, PatternGame.class);
                            startActivity(intent);
                            break;
                        case 7:
                            intent = new Intent(MainMenu.this, SortingGame.class);
                            startActivity(intent);
                            break;
                        case 8:
                            intent = new Intent(MainMenu.this, PatternMemoryGame.class);
                            startActivity(intent);
                            break;
                        case 9:
                            intent = new Intent(MainMenu.this, ReactionGame.class);
                            startActivity(intent);
                            break;
                        case 10:
                            intent = new Intent(MainMenu.this, ReflexGame.class);
                            startActivity(intent);
                            break;
                        case 11:
                            intent = new Intent(MainMenu.this, CategoriesGame.class);
                            startActivity(intent);
                            break;
                        case 12:
                            intent = new Intent(MainMenu.this, OperatorsGame.class);
                            startActivity(intent);
                            break;
                    }
                }
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Help.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //doNothing
    }
}
