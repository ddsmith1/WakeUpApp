package wakeup.wakeupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    private Button alarmSet;
    private Button practiceMenu;
    private Button startActivity;
    private Button help;

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
                Intent intent = new Intent(MainMenu.this, StartActivity.class);
                startActivity(intent);
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
