package wakeup.wakeupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HelpMenu extends AppCompatActivity {

    private ImageButton MatchingGame;
    private ImageButton ColorGame;
    private ImageButton DistractionGame;
    private ImageButton MemoryGame;
    private ImageButton PatternGame;
    private ImageButton SortingGame;
    private ImageButton MathGame;
    private ImageButton PatternMemoryGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice_menu);
        MatchingGame = (ImageButton) findViewById(R.id.matchingGame);
        ColorGame = (ImageButton) findViewById(R.id.colorGame);
        DistractionGame = (ImageButton) findViewById(R.id.distractionGame);
        MemoryGame = (ImageButton) findViewById(R.id.memoryGame);
        PatternGame = (ImageButton) findViewById(R.id.patternGame);
        SortingGame = (ImageButton) findViewById(R.id.sortingGame);
        MathGame = (ImageButton) findViewById(R.id.mathGame);
        PatternMemoryGame = (ImageButton) findViewById(R.id.patternMemoryGame);

        MatchingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpMenu.this, MatchingGame.class);
                startActivity(intent);
            }
        });

        MemoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpMenu.this, MemoryGame.class);
                startActivity(intent);
            }
        });

        ColorGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpMenu.this, ColorGame.class);
                startActivity(intent);
            }
        });

        DistractionGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpMenu.this, DistractionGame.class);
                startActivity(intent);
            }
        });

        MathGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpMenu.this, MathGame.class);
                startActivity(intent);
            }
        });
        PatternGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpMenu.this, PatternGame.class);
                startActivity(intent);
            }
        });
        SortingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpMenu.this, SortingGame.class);
                startActivity(intent);
            }
        });
        PatternMemoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpMenu.this, PatternMemoryGame.class);
                startActivity(intent);
            }
        });



    }
}
