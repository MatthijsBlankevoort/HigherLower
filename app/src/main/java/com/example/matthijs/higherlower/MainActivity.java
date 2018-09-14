package com.example.matthijs.higherlower;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();

    private ImageView imageView;
    private FloatingActionButton higherButton;
    private FloatingActionButton lowerButton;

    private TextView scoreText;
    private TextView highScoreText;

    private int score;
    private int highScore;

    private int lastDice = 1;
    private int newDice = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.dice_view);
        higherButton = findViewById(R.id.higher_button);
        lowerButton = findViewById(R.id.lower_button);

        scoreText = findViewById(R.id.score_text);
        highScoreText = findViewById(R.id.highscore_text);

        score = 0;
        highScore = 0;

        lowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
                lowerGuess(view);
            }
        });

        higherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
                higherGuess(view);
            }
        });
    }

    private void updateUI() {
        scoreText.setText("Score: " + score);
        highScoreText.setText("Highscore: " + highScore);
    }

    private void updateHighScore() {
        if (score > highScore) {
            highScore = score;
        }
    }

    private void higherGuess(View view) {
        if (newDice > lastDice) {
            score++;
            Snackbar.make(view, "You win!", Snackbar.LENGTH_SHORT).show();

        } else if (newDice == lastDice) {
            Snackbar.make(view, "Equals!", Snackbar.LENGTH_SHORT).show();
        } else {
            score = 0;
            Snackbar.make(view, "You lose!", Snackbar.LENGTH_SHORT).show();
        }
        updateHighScore();
        updateUI();
    }

    private void lowerGuess(View view) {
        if (newDice < lastDice) {
            score++;
            Snackbar.make(view, "You win!", Snackbar.LENGTH_SHORT).show();
        } else if (newDice == lastDice) {
            Snackbar.make(view, "Equals!", Snackbar.LENGTH_SHORT).show();
        } else {
            score = 0;
            Snackbar.make(view, "You lose!", Snackbar.LENGTH_SHORT).show();
        }

        updateHighScore();
        updateUI();
    }

    private void rollDice() {
        lastDice = newDice;
        int number = random.nextInt(6) + 1;
        newDice = number;

        switch(number) {
            case 1:
                imageView.setImageResource(R.drawable.d1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.d2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.d3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.d4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.d5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.d6);
                break;
            default:
                imageView.setImageResource(R.drawable.d1);
                break;
        }
    }
}
