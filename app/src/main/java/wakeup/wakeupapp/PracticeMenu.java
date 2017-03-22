package wakeup.wakeupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PracticeMenu extends AppCompatActivity {

    private Button SampleGame1;
    private Button MemoryGame;
    private Button MathGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice_menu);
        SampleGame1 = (Button) findViewById(R.id.sampleGame1);
        MemoryGame = (Button) findViewById(R.id.memoryGame);
        MathGame = (Button) findViewById(R.id.mathGame);

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

        MathGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeMenu.this, MathGame.class);
                startActivity(intent);
            }
        });

    }
}
