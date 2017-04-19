package wakeup.wakeupapp;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Random;

public class CategoriesGame extends AppCompatActivity {

    private ImageButton buttons[];
    private View.OnClickListener listeners[];
    private TextView instructions;
    private Shape shapes[];

    private Button endGame;
    private TableLayout buttonsTable;

    private int roundsLeft;
    private Random random;
    private String currentTrait;
    private int shapesLeft;

    private Drawable images[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_game);

        init();
        assignShapes();
    }

    public void init() {
        buttonsTable = (TableLayout) findViewById(R.id.buttonsTable);
        endGame = (Button) findViewById(R.id.endGame);
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        endGame.setVisibility(View.INVISIBLE);
        instructions = (TextView) findViewById(R.id.instructions);
        buttons = new ImageButton[12];
        listeners = new View.OnClickListener[12];
        shapes = new Shape[12];
        images = new Drawable[12];
        random = new Random();
        roundsLeft = 5;
        shapesLeft = 0;
        currentTrait = null;
        loadImages();
        initButtons();
        initListeners();
        attachListeners();

    }

    public void initButtons() {
        buttons[0] = (ImageButton) findViewById(R.id.button1);
        buttons[1] = (ImageButton) findViewById(R.id.button2);
        buttons[2] = (ImageButton) findViewById(R.id.button3);
        buttons[3] = (ImageButton) findViewById(R.id.button4);
        buttons[4] = (ImageButton) findViewById(R.id.button5);
        buttons[5] = (ImageButton) findViewById(R.id.button6);
        buttons[6] = (ImageButton) findViewById(R.id.button7);
        buttons[7] = (ImageButton) findViewById(R.id.button8);
        buttons[8] = (ImageButton) findViewById(R.id.button9);
        buttons[9] = (ImageButton) findViewById(R.id.button10);
        buttons[10] = (ImageButton) findViewById(R.id.button11);
        buttons[11] = (ImageButton) findViewById(R.id.button12);
    }

    public void initListeners() {
        listeners[0] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(checkShape(0)) {
                buttons[0].setVisibility(View.INVISIBLE);
                if(shapesLeft == 0) {
                    endRound();
                }
            } else buttons[0].setBackground(getGreyImage(0));
            }
        };

        listeners[1] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShape(1)) {
                    buttons[1].setVisibility(View.INVISIBLE);
                    if(shapesLeft == 0) {
                        endRound();
                    }
                } else buttons[1].setBackground(getGreyImage(1));
            }
        };

        listeners[2] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShape(2)) {
                    buttons[2].setVisibility(View.INVISIBLE);
                    if(shapesLeft == 0) {
                        endRound();
                    }
                } else buttons[2].setBackground(getGreyImage(2));
            }
        };

        listeners[3] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShape(3)) {
                    buttons[3].setVisibility(View.INVISIBLE);
                    if(shapesLeft == 0) {
                        endRound();
                    }
                } else buttons[3].setBackground(getGreyImage(3));
            }
        };

        listeners[4] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShape(4)) {
                    buttons[4].setVisibility(View.INVISIBLE);
                    if(shapesLeft == 0) {
                        endRound();
                    }
                } else buttons[4].setBackground(getGreyImage(4));
            }
        };

        listeners[5] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShape(5)) {
                    buttons[5].setVisibility(View.INVISIBLE);
                    if(shapesLeft == 0) {
                        endRound();
                    }
                } else buttons[5].setBackground(getGreyImage(5));
            }
        };

        listeners[6] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShape(6)) {
                    buttons[6].setVisibility(View.INVISIBLE);
                    if(shapesLeft == 0) {
                        endRound();
                    }
                } else buttons[6].setBackground(getGreyImage(6));
            }
        };
        listeners[7] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShape(7)) {
                    buttons[7].setVisibility(View.INVISIBLE);
                    if(shapesLeft == 0) {
                        endRound();
                    }
                } else buttons[7].setBackground(getGreyImage(7));
            }
        };
        listeners[8] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShape(8)) {
                    buttons[8].setVisibility(View.INVISIBLE);
                    if(shapesLeft == 0) {
                        endRound();
                    }
                } else buttons[8].setBackground(getGreyImage(8));
            }
        };
        listeners[9] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShape(9)) {
                    buttons[9].setVisibility(View.INVISIBLE);
                    if(shapesLeft == 0) {
                        endRound();
                    }
                } else buttons[9].setBackground(getGreyImage(9));
            }
        };
        listeners[10] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShape(10)) {
                    buttons[10].setVisibility(View.INVISIBLE);
                    if(shapesLeft == 0) {
                        endRound();
                    }
                } else buttons[10].setBackground(getGreyImage(10));
            }
        };
        listeners[11] = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkShape(11)) {
                    buttons[11].setVisibility(View.INVISIBLE);
                    if(shapesLeft == 0) {
                        endRound();
                    }
                } else buttons[11].setBackground(getGreyImage(11));
            }
        };
    }

    public void loadImages() {
        images[0] = getDrawable(R.mipmap.blue_circle);
        images[1] = getDrawable(R.mipmap.blue_rect);
        images[2] = getDrawable(R.mipmap.blue_tri);

        images[3] = getDrawable(R.mipmap.green_circle);
        images[4] = getDrawable(R.mipmap.green_rect);
        images[5] = getDrawable(R.mipmap.green_tri);

        images[6] = getDrawable(R.mipmap.red_circle);
        images[7] = getDrawable(R.mipmap.red_rect);
        images[8] = getDrawable(R.mipmap.red_tri);

        images[9] = getDrawable(R.mipmap.yellow_circle);
        images[10] = getDrawable(R.mipmap.yellow_rect);
        images[11] = getDrawable(R.mipmap.yellow_tri);
    }

    public void attachListeners() {
        for(int i = 0; i < 12; i++) {
            buttons[i].setOnClickListener(listeners[i]);
        }
    }

    public void assignShapes() {
        shapesLeft = 0;
        int trait = random.nextInt(7);
        for(int i = 0; i < 12; i++) {
            int j = random.nextInt(2);
            switch (trait) {
                case 0: //choose blue shapes
                    currentTrait = "blue";
                    instructions.setText("Press all shapes that are colored blue.");
                    switch(j) {
                        case 0:
                            chooseBlueShape(i);
                            shapesLeft++;
                            break;
                        case 1:
                            chooseOtherShape(i);
                            break;
                    }
                    break;
                case 1: //choose green shapes
                    currentTrait = "green";
                    instructions.setText("Press all shapes that are colored green.");
                    switch(j) {
                        case 0:
                            chooseGreenShape(i);
                            shapesLeft++;
                            break;
                        case 1:
                            chooseOtherShape(i);
                            break;
                    }
                    break;
                case 2: //choose red shapes
                    currentTrait = "red";
                    instructions.setText("Press all shapes that are colored red.");
                    switch(j) {
                        case 0:
                            chooseRedShape(i);
                            shapesLeft++;
                            break;
                        case 1:
                            chooseOtherShape(i);
                            break;
                    }
                    break;
                case 3: //choose yellow shapes
                    currentTrait = "yellow";
                    instructions.setText("Press all shapes that are colored yellow.");
                    switch(j) {
                        case 0:
                            chooseYellowShape(i);
                            shapesLeft++;
                            break;
                        case 1:
                            chooseOtherShape(i);
                            break;
                    }
                    break;
                case 4: //choose circles
                    currentTrait = "circle";
                    instructions.setText("Press all circles.");
                    switch(j) {
                        case 0:
                            chooseCircle(i);
                            shapesLeft++;
                            break;
                        case 1:
                            chooseOtherShape(i);
                            break;
                    }
                    break;
                case 5: //choose squares
                    currentTrait = "square";
                    instructions.setText("Press all squares.");
                    switch(j) {
                        case 0:
                            chooseSquare(i);
                            shapesLeft++;
                            break;
                        case 1:
                            chooseOtherShape(i);
                            break;
                    }
                    break;
                case 6: //choose triangles
                    currentTrait = "triangle";
                    instructions.setText("Press all triangles.");
                    switch(j) {
                        case 0:
                            chooseTriangle(i);
                            shapesLeft++;
                            break;
                        case 1:
                            chooseOtherShape(i);
                            break;
                    }
                    break;
            }
        }


    }

    public void chooseOtherShape(int shapeNum) {
        if(currentTrait == "blue") {
            int choice = random.nextInt(3);
            switch (choice) {
                case 0:
                    chooseRedShape(shapeNum);
                    break;
                case 1:
                    chooseGreenShape(shapeNum);
                    break;
                case 2:
                    chooseYellowShape(shapeNum);
                    break;
            }
        } else if(currentTrait == "green") {
            int choice = random.nextInt(3);
            switch (choice) {
                case 0:
                    chooseBlueShape(shapeNum);
                    break;
                case 1:
                    chooseRedShape(shapeNum);
                    break;
                case 2:
                    chooseYellowShape(shapeNum);
                    break;
            }
        } else if(currentTrait == "red") {
            int choice = random.nextInt(3);
            switch (choice) {
                case 0:
                    chooseBlueShape(shapeNum);
                    break;
                case 1:
                    chooseGreenShape(shapeNum);
                    break;
                case 2:
                    chooseYellowShape(shapeNum);
                    break;
            }
        } else if(currentTrait == "circle") {
            int choice = random.nextInt(2);
            switch(choice) {
                case 0:
                    chooseSquare(shapeNum);
                    break;
                case 1:
                    chooseTriangle(shapeNum);
                    break;
            }
        } else if(currentTrait == "square") {
            int choice = random.nextInt(2);
            switch(choice) {
                case 0:
                    chooseCircle(shapeNum);
                    break;
                case 1:
                    chooseTriangle(shapeNum);
                    break;
            }
        } else if(currentTrait == "triangle") {
            int choice = random.nextInt(2);
            switch(choice) {
                case 0:
                    chooseCircle(shapeNum);
                    break;
                case 1:
                    chooseSquare(shapeNum);
                    break;
            }
        }

    }

    public Drawable getGreyImage(int buttonNumber) {
        Drawable current = buttons[buttonNumber].getBackground();
        Drawable greyTri = getDrawable(R.mipmap.grey_triangle);
        Drawable greyRect = getDrawable(R.mipmap.grey_rectangle);
        Drawable greyCircle = getDrawable(R.mipmap.grey_circle);
        if(current == images[0] || current == images[3] || current == images[6] || current == images[9]) {
            return greyCircle;
        } else if (current == images[1] || current == images[4] || current == images[7] || current == images[10]) {
            return greyRect;
        } else if (current == images[2] || current == images[5] || current == images[8] || current == images[11]) {
            return greyTri;
        } else if (current == greyCircle) {
            return greyCircle;
        } else if (current == greyRect) {
            return greyRect;
        } else if (current == greyTri) {
            return greyTri;
        }
        return null;
    }

    public boolean checkShape(int shapeNum) {
        if(shapes[shapeNum].getColor() == currentTrait || shapes[shapeNum].getShape() == currentTrait) {
            shapesLeft--;
            return true;
        } else return false;
    }

    public void chooseBlueShape(int shapeNum) {
        int choice = random.nextInt(3);
        switch(choice) {
            case 0: //blue circle
                buttons[shapeNum].setBackground(images[0]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("blue");
                shapes[shapeNum].setShape("circle");
                break;
            case 1: //blue square
                buttons[shapeNum].setBackground(images[1]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("blue");
                shapes[shapeNum].setShape("square");
                break;
            case 2: //blue triangle
                buttons[shapeNum].setBackground(images[2]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("blue");
                shapes[shapeNum].setShape("triangle");
                break;
        }
    }

    public void chooseGreenShape(int shapeNum) {
        int choice = random.nextInt(3);
        switch(choice) {
            case 0: //green circle
                buttons[shapeNum].setBackground(images[3]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("green");
                shapes[shapeNum].setShape("circle");
                break;
            case 1: //green square
                buttons[shapeNum].setBackground(images[4]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("green");
                shapes[shapeNum].setShape("square");
                break;
            case 2: //green triangle
                buttons[shapeNum].setBackground(images[5]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("green");
                shapes[shapeNum].setShape("triangle");
                break;
        }
    }

    public void chooseRedShape(int shapeNum) {
        int choice = random.nextInt(3);
        switch(choice) {
            case 0: //red circle
                buttons[shapeNum].setBackground(images[6]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("red");
                shapes[shapeNum].setShape("circle");
                break;
            case 1: //red square
                buttons[shapeNum].setBackground(images[7]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("red");
                shapes[shapeNum].setShape("square");
                break;
            case 2: //red triangle
                buttons[shapeNum].setBackground(images[8]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("red");
                shapes[shapeNum].setShape("triangle");
                break;
        }
    }

    public void chooseYellowShape(int shapeNum) {
        int choice = random.nextInt(3);
        switch(choice) {
            case 0: //yellow circle
                buttons[shapeNum].setBackground(images[9]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("yellow");
                shapes[shapeNum].setShape("circle");
                break;
            case 1: //yellow square
                buttons[shapeNum].setBackground(images[10]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("yellow");
                shapes[shapeNum].setShape("square");
                break;
            case 2: //yellow triangle
                buttons[shapeNum].setBackground(images[11]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("yellow");
                shapes[shapeNum].setShape("triangle");
                break;
        }
    }

    public void chooseCircle(int shapeNum) {
        int choice = random.nextInt(4);
        switch(choice) {
            case 0: //blue circle
                buttons[shapeNum].setBackground(images[0]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("blue");
                shapes[shapeNum].setShape("circle");
                break;
            case 1: //green circle
                buttons[shapeNum].setBackground(images[3]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("green");
                shapes[shapeNum].setShape("circle");
                break;
            case 2: //red circle
                buttons[shapeNum].setBackground(images[6]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("red");
                shapes[shapeNum].setShape("circle");
                break;
            case 3: // yellow circle
                buttons[shapeNum].setBackground(images[9]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("yellow");
                shapes[shapeNum].setShape("circle");
                break;
        }
    }

    public void chooseSquare(int shapeNum) {
        int choice = random.nextInt(4);
        switch(choice) {
            case 0:
                buttons[shapeNum].setBackground(images[1]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("blue");
                shapes[shapeNum].setShape("square");
                break;
            case 1:
                buttons[shapeNum].setBackground(images[4]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("green");
                shapes[shapeNum].setShape("square");
                break;
            case 2:
                buttons[shapeNum].setBackground(images[7]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("red");
                shapes[shapeNum].setShape("square");
                break;
            case 3:
                buttons[shapeNum].setBackground(images[10]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("yellow");
                shapes[shapeNum].setShape("square");
                break;
        }
    }

    public void chooseTriangle(int shapeNum) {
        int choice = random.nextInt(4);
        switch(choice) {
            case 0:
                buttons[shapeNum].setBackground(images[2]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("blue");
                shapes[shapeNum].setShape("triangle");
                break;
            case 1:
                buttons[shapeNum].setBackground(images[5]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("green");
                shapes[shapeNum].setShape("triangle");
                break;
            case 2:
                buttons[shapeNum].setBackground(images[8]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("red");
                shapes[shapeNum].setShape("triangle");
                break;
            case 3:
                buttons[shapeNum].setBackground(images[11]);
                shapes[shapeNum] = new Shape();
                shapes[shapeNum].setColor("yellow");
                shapes[shapeNum].setShape("triangle");
                break;
        }
    }

    public void endRound() {
        roundsLeft--;
        if(roundsLeft == 0) {
            for(int i = 0; i < 12; i++) {
                buttons[i].setVisibility(View.INVISIBLE);
            }
            buttonsTable.setVisibility(View.INVISIBLE);
            instructions.setVisibility(View.INVISIBLE);
            endGame.setVisibility(View.VISIBLE);
        }

        for(int i = 0; i < 12; i++) {
            buttons[i].setVisibility(View.VISIBLE);
        }
        assignShapes();
    }

    private class Shape {

        private String color;
        private String shape;

        public Shape() {
            this.color = null;
            this.shape = null;
        }

        private void setColor(String color){
            this.color = color;
        }

        private void setShape(String shape) {
            this.shape = shape;
        }

        private String getColor() {
            return color;
        }

        private String getShape() {
            return shape;
        }

    }

}
