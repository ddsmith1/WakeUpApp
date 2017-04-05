package wakeup.wakeupapp;

import android.content.Intent;
import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class PracticeMenu extends AppCompatActivity {

    private ImageButton SampleGame;
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
        SampleGame = (ImageButton) findViewById(R.id.sampleGame);
        MatchingGame = (ImageButton) findViewById(R.id.matchingGame);
        ColorGame = (ImageButton) findViewById(R.id.colorGame);
        DistractionGame = (ImageButton) findViewById(R.id.distractionGame);
        MemoryGame = (ImageButton) findViewById(R.id.memoryGame);
        PatternGame = (ImageButton) findViewById(R.id.patternGame);
        SortingGame = (ImageButton) findViewById(R.id.sortingGame);
        MathGame = (ImageButton) findViewById(R.id.mathGame);
        PatternMemoryGame = (ImageButton) findViewById(R.id.patternMemoryGame);

        SampleGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, SampleGame.class);
                startActivity(intent);
            }
        });

        MatchingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, MatchingGame.class);
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

        ColorGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, ColorGame.class);
                startActivity(intent);
            }
        });

        DistractionGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, DistractionGame.class);
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
        PatternMemoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, PatternMemoryGame.class);
                startActivity(intent);
            }
        });



    }
}
