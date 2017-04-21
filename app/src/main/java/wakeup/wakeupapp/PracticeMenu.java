package wakeup.wakeupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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
    private ImageButton OperatorsGame;
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
        OperatorsGame = (ImageButton) findViewById(R.id.operatorsGame);
        CategoriesGame = (ImageButton) findViewById(R.id.categoriesGame);
        ReflexGame = (ImageButton) findViewById(R.id.reflexGame);


        MatchingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, MatchingGame.class);
                wakeup.wakeupapp.MatchingGame.clearActivityFlag();
                startActivity(intent);
            }
        });

        MemoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, MemoryGame.class);
                wakeup.wakeupapp.MemoryGame.clearActivityFlag();
                startActivity(intent);
            }
        });

        ColorGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, ColorGame.class);
                wakeup.wakeupapp.ColorGame.clearActivityFlag();
                startActivity(intent);
            }
        });

        DistractionGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, DistractionGame.class);
                wakeup.wakeupapp.DistractionGame.clearActivityFlag();
                startActivity(intent);
            }
        });

        MathGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, MathGame.class);
                wakeup.wakeupapp.MathGame.clearActivityFlag();
                startActivity(intent);
            }
        });
        PatternGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, PatternGame.class);
                wakeup.wakeupapp.PatternGame.clearActivityFlag();
                startActivity(intent);
            }
        });
        SortingGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, SortingGame.class);
                wakeup.wakeupapp.SortingGame.clearActivityFlag();
                startActivity(intent);
            }
        });
        PatternMemoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, PatternMemoryGame.class);
                wakeup.wakeupapp.PatternMemoryGame.clearActivityFlag();
                startActivity(intent);
            }
        });
        ReactionGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, ReactionGame.class);
                wakeup.wakeupapp.ReactionGame.clearActivityFlag();
                startActivity(intent);
            }
        });
        OperatorsGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, OperatorsGame.class);
                wakeup.wakeupapp.OperatorsGame.clearActivityFlag();
                startActivity(intent);
            }
        });
        CategoriesGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, CategoriesGame.class);
                wakeup.wakeupapp.CategoriesGame.clearActivityFlag();
                startActivity(intent);
            }
        });
        ReflexGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, wakeup.wakeupapp.ReflexGame.class);
                wakeup.wakeupapp.ReflexGame.clearActivityFlag();
                startActivity(intent);
            }
        });



    }
}
