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

    private ImageButton MatchingGame;
    private ImageButton ColorGame;
    private ImageButton DistractionGame;
    private ImageButton MemoryGame;
    private ImageButton PatternGame;
    private ImageButton SortingGame;
    private ImageButton MathGame;
    private ImageButton PatternMemoryGame;
    private ImageButton ReactionGame;
    private ImageButton EqualityGame;
    private ImageButton CategoriesGame;
    private ImageButton ReflexGame;

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
        ReactionGame = (ImageButton) findViewById(R.id.reactionGame);
        EqualityGame = (ImageButton) findViewById(R.id.equalityGame);
        CategoriesGame = (ImageButton) findViewById(R.id.categoriesGame);
        ReflexGame = (ImageButton) findViewById(R.id.reflexGame);


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
        ReactionGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, ReactionGame.class);
                startActivity(intent);
            }
        });
        EqualityGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, EqualityGame.class);
                startActivity(intent);
            }
        });
        CategoriesGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, CategoriesGame.class);
                startActivity(intent);
            }
        });
        ReflexGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, ReactChangeGame.class);
                startActivity(intent);
            }
        });



    }
}
