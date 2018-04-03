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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weather = findViewById(R.id.knowtheweather);
        cinema = findViewById(R.id.knowthecinema);
        eagle = findViewById(R.id.eagle);


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


}
