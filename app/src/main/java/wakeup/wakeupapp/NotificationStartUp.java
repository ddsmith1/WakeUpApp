package wakeup.wakeupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class NotificationStartUp extends AppCompatActivity {

    final int NUM_CHOICES = 3;
    final int NUM_GAMES = 12;
    int flag;

    private Button endGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_start_up);

        endGame = (Button) findViewById(R.id.endGame);
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationStartUp.this, MainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        AlarmReceiver.stopMedia();
        flag = 0;
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
                    intent = new Intent(NotificationStartUp.this, ColorGame.class);
                    ColorGame.setActivityFlag();
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(NotificationStartUp.this, DistractionGame.class);
                    DistractionGame.setActivityFlag();
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(NotificationStartUp.this, MatchingGame.class);
                    MatchingGame.setActivityFlag();
                    startActivity(intent);
                    break;
                case 4:
                    intent = new Intent(NotificationStartUp.this, MathGame.class);
                    MathGame.setActivityFlag();
                    startActivity(intent);
                    break;
                case 5:
                    intent = new Intent(NotificationStartUp.this, MemoryGame.class);
                    MemoryGame.setActivityFlag();
                    startActivity(intent);
                    break;
                case 6:
                    intent = new Intent(NotificationStartUp.this, PatternGame.class);
                    PatternGame.setActivityFlag();
                    startActivity(intent);
                    break;
                case 7:
                    intent = new Intent(NotificationStartUp.this, SortingGame.class);
                    SortingGame.setActivityFlag();
                    startActivity(intent);
                    break;
                case 8:
                    intent = new Intent(NotificationStartUp.this, PatternMemoryGame.class);
                    PatternMemoryGame.setActivityFlag();
                    startActivity(intent);
                    break;
                case 9:
                    intent = new Intent(NotificationStartUp.this, ReactionGame.class);
                    ReactionGame.setActivityFlag();
                    startActivity(intent);
                    break;
                case 10:
                    intent = new Intent(NotificationStartUp.this, ReflexGame.class);
                    ReflexGame.setActivityFlag();
                    startActivity(intent);
                    break;
                case 11:
                    intent = new Intent(NotificationStartUp.this, CategoriesGame.class);
                    CategoriesGame.setActivityFlag();
                    startActivity(intent);
                    break;
                case 12:
                    intent = new Intent(NotificationStartUp.this, OperatorsGame.class);
                    OperatorsGame.setActivityFlag();
                    startActivity(intent);
                    break;
            }
        }
    }
}
