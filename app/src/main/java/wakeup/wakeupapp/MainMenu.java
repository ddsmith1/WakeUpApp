package wakeup.wakeupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainMenu extends AppCompatActivity {

    private Button alarmSet;
    private Button practiceMenu;
    private Button startActivity;
    private Button help;
    final int NUM_CHOICES = 1;
    final int NUM_GAMES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        alarmSet = (Button) findViewById(R.id.alarmSet);
        practiceMenu = (Button) findViewById(R.id.practiceMenu);
        startActivity = (Button) findViewById(R.id.startActivity);
        help = (Button) findViewById(R.id.help);

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

                    while(true) { //loop to check for repeated choices
                        boolean flag = true;
                        choice = random.nextInt(NUM_GAMES) + 1;
                        for (int j = i - 1; j >= 0; j--){ //look for repeated choice from previous choices
                            if (choices[j] == choice) {
                                flag = false; //if repeated choice, set flag to false
                            }
                        }

                        if(flag) { //if no repeated choice, break from while statement
                            choices[i] = choice;
                            break;
                        }
                    }

                    Intent intent = null;
                    switch(choice) { //switch for different games, currently only one
                        case 1:
                            intent = new Intent(MainMenu.this, SampleGame.class);
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
}
