package com.example.andrej.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class EasyGameActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private EditText editText;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);



    }
    public void getRestart(View v){
        Random random = new Random();
        number = random.nextInt(100) + 1;
    }

    public void getAnswer(View v) {
        for (int i = 1; i < 6; i++) {
            textView.setText("Игрок, угадай какое число я загадал! У тебя есть" + i + " попыток");
            int n = Integer.parseInt(String.valueOf(editText.getText()));
            if (number == n) {
                textView.setText("You're winner!");
            } else if (n>number)
                textView.setText("n>number");
            else if (n<number)
                textView.setText("n<number");
        }
    }
}
