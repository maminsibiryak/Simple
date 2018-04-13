package com.example.andrej.simple;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.Random;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class EagleAndTailsActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eagle_and_tails);
        imageButton = findViewById(R.id.imageButton);
        gifImageView = findViewById(R.id.gif);




    }

    public class EagleAndTalis extends Thread{
        @Override
        public void run() {
            super.run();

            GifDrawable gifDrawable;
            gifDrawable = (GifDrawable) gifImageView.getDrawable();
            gifDrawable.setLoopCount(1);
            gifDrawable.start();
        }
    }


public class Answer extends Thread{

    @Override
    public void run() {
        super.run();
        Random random = new Random();
        int chance = random.nextInt(2);

        /*MediaController mediaController = new MediaController(Answer.class);
        mediaController.setMediaPlayer((GifDrawable) gifImageView.getDrawable());
        mediaController.setAnchorView(gifImageView);*/

        try {
            Thread.sleep(3750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            //Thread.sleep(1000);
            if (chance == 1) {
                //Thread.sleep(1000);
                imageButton.setImageResource(R.drawable.eagle);
                //mediaController.show();
            }
            if (chance == 0) {
                //Thread.sleep(1000);
                imageButton.setImageResource(R.mipmap.ic_talis);
            }
        } catch (Exception e) {

        }


    }
}

    public void getAnswer(View v) throws InterruptedException {
        EagleAndTalis eagleAndTalis = new EagleAndTalis();
        Answer answer = new Answer();
        gifImageView.setImageResource(R.drawable.money);
        eagleAndTalis.start();
        eagleAndTalis.join();
        answer.start();
    }
}
