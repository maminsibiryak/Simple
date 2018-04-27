package com.example.andrej.simple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button weather;
    private Button cinema;
    private Button eagle;
    private Button game;
    private Button loto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weather = findViewById(R.id.knowtheweather);
        cinema = findViewById(R.id.knowthecinema);
        eagle = findViewById(R.id.eagle);
        game = findViewById(R.id.button2);
        loto = findViewById(R.id.loto);



    }


    public void getPremier(View view) {
        Intent intent = new Intent(this, CinemaActivity.class);
        startActivity(intent);
    }

    public void getWeather(View view2) {
        Intent intent2 = new Intent(this, WeatherActivity.class);
        startActivity(intent2);
    }
    public void getEagle(View view3) {
        Intent intent3 = new Intent(this, EagleAndTailsActivity.class);
        startActivity(intent3);
    }

    public void getGame(View view4) {
        Intent intent4 = new Intent(this, EasyGameActivity.class);
        startActivity(intent4);
    }
    public void getLoto(View view5) {
        Intent intent5 = new Intent(this, LotoActivity.class);
        startActivity(intent5);
    }



}
