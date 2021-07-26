package com.example.grawkosci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import java.util.Random;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private Button rollDices, endTurn, restart;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6;
    private TextView playerNumber, throwNumber, score1, score2;
    private ToggleButton toggleButton1, toggleButton2, toggleButton3, toggleButton4, toggleButton5;
    private static int player = 1;
    private static int throwN = 3;
    private static int score, value1, value2, value3, value4, value5, round = 0;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private ConstraintLayout constraintLayout;

    public class Player
    {
        public int number;
        public int score = 0;
        Player(int number)
        {
            this.number = number;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraint);
        rollDices = (Button) findViewById(R.id.rollDices);
        endTurn = (Button) findViewById(R.id.endTurn);
        restart = (Button) findViewById(R.id.restart);
        playerNumber = (TextView) findViewById(R.id.playerNumber);
        throwNumber = (TextView) findViewById(R.id.throwNumber);
        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        toggleButton3 = (ToggleButton) findViewById(R.id.toggleButton3);
        toggleButton4 = (ToggleButton) findViewById(R.id.toggleButton4);
        toggleButton5 = (ToggleButton) findViewById(R.id.toggleButton5);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        radioButton5 = (RadioButton) findViewById(R.id.radioButton5);
        radioButton6 = (RadioButton) findViewById(R.id.radioButton6);
        toggleButton1.setClickable(false);
        toggleButton2.setClickable(false);
        toggleButton3.setClickable(false);
        toggleButton4.setClickable(false);
        toggleButton5.setClickable(false);
        final Toast noThrows = Toast.makeText(getApplicationContext(), "Zakończ turę!", Toast.LENGTH_SHORT);
        final Toast throwOnce = Toast.makeText(getApplicationContext(), "Rzuć kośćmi przynajmniej raz!", Toast.LENGTH_SHORT);
        final Player player1 = new Player(1);
        final Player player2 = new Player(2);

        rollDices.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (throwN == 3) {
                    value1 = randomDiceValue();
                    value2 = randomDiceValue();
                    value3 = randomDiceValue();
                    value4 = randomDiceValue();
                    value5 = randomDiceValue();

                    int res1 = getResources().getIdentifier("dice_" + value1, "drawable", "com.example.grawkosci");
                    int res2 = getResources().getIdentifier("dice_" + value2, "drawable", "com.example.grawkosci");
                    int res3 = getResources().getIdentifier("dice_" + value3, "drawable", "com.example.grawkosci");
                    int res4 = getResources().getIdentifier("dice_" + value4, "drawable", "com.example.grawkosci");
                    int res5 = getResources().getIdentifier("dice_" + value5, "drawable", "com.example.grawkosci");

                    toggleButton1.setBackgroundResource(res1);
                    toggleButton2.setBackgroundResource(res2);
                    toggleButton3.setBackgroundResource(res3);
                    toggleButton4.setBackgroundResource(res4);
                    toggleButton5.setBackgroundResource(res5);
                    throwN--;
                    throwNumber.setText(Integer.toString(throwN));
                    radioButton1.setClickable(false);
                    radioButton2.setClickable(false);
                    radioButton3.setClickable(false);
                    radioButton4.setClickable(false);
                    radioButton5.setClickable(false);
                    radioButton6.setClickable(false);
                    toggleButton1.setClickable(true);
                    toggleButton2.setClickable(true);
                    toggleButton3.setClickable(true);
                    toggleButton4.setClickable(true);
                    toggleButton5.setClickable(true);
                } else if (throwN == 2 || throwN == 1) {
                    if (toggleButton1.isChecked()) {
                        value1 = randomDiceValue();
                        int res1 = getResources().getIdentifier("dice_" + value1, "drawable", "com.example.grawkosci");
                        toggleButton1.setBackgroundResource(res1);
                    }
                    if (toggleButton2.isChecked()) {
                        value2 = randomDiceValue();
                        int res2 = getResources().getIdentifier("dice_" + value2, "drawable", "com.example.grawkosci");
                        toggleButton2.setBackgroundResource(res2);
                    }
                    if (toggleButton3.isChecked()) {
                        value3 = randomDiceValue();
                        int res3 = getResources().getIdentifier("dice_" + value3, "drawable", "com.example.grawkosci");
                        toggleButton3.setBackgroundResource(res3);
                    }
                    if (toggleButton4.isChecked()) {
                        value4 = randomDiceValue();
                        int res4 = getResources().getIdentifier("dice_" + value4, "drawable", "com.example.grawkosci");
                        toggleButton4.setBackgroundResource(res4);
                    }
                    if (toggleButton5.isChecked()) {
                        value5 = randomDiceValue();
                        int res5 = getResources().getIdentifier("dice_" + value5, "drawable", "com.example.grawkosci");
                        toggleButton5.setBackgroundResource(res5);
                    }
                    throwN--;
                    throwNumber.setText(Integer.toString(throwN));
                } else noThrows.show();
            }
        });

        endTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (round > 25) {
                    layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.popuplayout,null);
                    TextView playerText = (TextView) container.findViewById(R.id.playerText);
                    TextView scoreText = (TextView) container.findViewById(R.id.scoreText);
                    if(player1.score > player2.score){
                        playerText.setText("1");
                        scoreText.setText(Integer.toString(player1.score));
                    }
                    else{
                        playerText.setText("2");
                        scoreText.setText(Integer.toString(player2.score));
                    }
                    popupWindow = new PopupWindow(container,400,400,true);
                    popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, 340, 850);
                    container.setOnTouchListener(new View.OnTouchListener(){
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent){
                            popupWindow.dismiss();
                            return true;
                        }
                    });
                    rollDices.setVisibility(View.GONE);
                    endTurn.setVisibility(View.GONE);
                    restart.setVisibility(View.VISIBLE);

                } else {
                    if (throwN == 3) {
                        throwOnce.show();
                    } else {
                        if (radioButton1.isChecked()) {
                            if (value1 == 1) score += 1;
                            if (value2 == 1) score += 1;
                            if (value3 == 1) score += 1;
                            if (value4 == 1) score += 1;
                            if (value5 == 1) score += 1;
                        } else if (radioButton2.isChecked()) {
                            if (value1 == 2) score += 2;
                            if (value2 == 2) score += 2;
                            if (value3 == 2) score += 2;
                            if (value4 == 2) score += 2;
                            if (value5 == 2) score += 2;
                        } else if (radioButton3.isChecked()) {
                            if (value1 == 3) score += 3;
                            if (value2 == 3) score += 3;
                            if (value3 == 3) score += 3;
                            if (value4 == 3) score += 3;
                            if (value5 == 3) score += 3;
                        } else if (radioButton4.isChecked()) {
                            if (value1 == 4) score += 4;
                            if (value2 == 4) score += 4;
                            if (value3 == 4) score += 4;
                            if (value4 == 4) score += 4;
                            if (value5 == 4) score += 4;
                        } else if (radioButton5.isChecked()) {
                            if (value1 == 5) score += 5;
                            if (value2 == 5) score += 5;
                            if (value3 == 5) score += 5;
                            if (value4 == 5) score += 5;
                            if (value5 == 5) score += 5;
                        } else if (radioButton6.isChecked()) {
                            if (value1 == 6) score += 6;
                            if (value2 == 6) score += 6;
                            if (value3 == 6) score += 6;
                            if (value4 == 6) score += 6;
                            if (value5 == 6) score += 6;
                        }
                        if (player == 1) {
                            player = 2;
                            player1.score += score;
                            score1.setText(Integer.toString(player1.score));
                        } else {
                            player = 1;
                            player2.score += score;
                            score2.setText(Integer.toString(player2.score));
                        }
                        playerNumber.setText(Integer.toString(player));
                        throwN = 3;
                        throwNumber.setText(Integer.toString(throwN));
                        toggleButton1.setChecked(false);
                        toggleButton2.setChecked(false);
                        toggleButton3.setChecked(false);
                        toggleButton4.setChecked(false);
                        toggleButton5.setChecked(false);
                        value1 = 0;
                        value2 = 0;
                        value3 = 0;
                        value4 = 0;
                        value5 = 0;
                        score = 0;
                        radioButton1.setClickable(true);
                        radioButton2.setClickable(true);
                        radioButton3.setClickable(true);
                        radioButton4.setClickable(true);
                        radioButton5.setClickable(true);
                        radioButton6.setClickable(true);
                        radioButton2.setChecked(false);
                        radioButton3.setChecked(false);
                        radioButton4.setChecked(false);
                        radioButton5.setChecked(false);
                        radioButton6.setChecked(false);
                        radioButton1.setChecked(true);
                        toggleButton1.setClickable(false);
                        toggleButton2.setClickable(false);
                        toggleButton3.setClickable(false);
                        toggleButton4.setClickable(false);
                        toggleButton5.setClickable(false);
                        round++;
                    }
                }
            }
        });
        restart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent restartIntent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(restartIntent);
            }
        });
    }
    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }


}
