package wakeup.wakeupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PracticeMenu extends AppCompatActivity {

    private Button SampleGame;
    private Button MemoryGame;
    private Button PatternGame;
    private Button SortingGame;
    private Button MathGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice_menu);
        SampleGame = (Button) findViewById(R.id.sampleGame);
        MemoryGame = (Button) findViewById(R.id.memoryGame);
        PatternGame = (Button) findViewById(R.id.patternGame);
        SortingGame = (Button) findViewById(R.id.sortingGame);
        MathGame = (Button) findViewById(R.id.mathGame);

        SampleGame.setOnClickListener(new View.OnClickListener() {
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

        MathGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, MathGame.class);
                startActivity(intent);
            }
        });
        PatternGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, PatternGame.class);
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
