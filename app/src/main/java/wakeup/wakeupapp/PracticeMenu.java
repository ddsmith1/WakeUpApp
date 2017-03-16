package wakeup.wakeupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PracticeMenu extends AppCompatActivity {

    private Button SampleGame1;
    private Button MemoryGame;
    private Button SortingGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice_menu);
        SampleGame1 = (Button) findViewById(R.id.samplegame1);
        MemoryGame = (Button) findViewById(R.id.memoryGame);
        SortingGame = (Button) findViewById(R.id.sortingGame);

        SampleGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, SampleGame.class);
                startActivity(intent);
            }
        });
        MemoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, MemoryGame.class);
                startActivity(intent);
            }
        });
        SortingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, SortingGame.class);
                startActivity(intent);
            }
        });

    }
}
