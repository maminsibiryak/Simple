package com.example.andrej.simple;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;

import com.example.andrej.simple.R;

import java.io.IOException;
import java.io.InputStream;
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


    public void getAnswer(View v) throws InterruptedException, IOException {

        Random random = new Random();
        int chance = random.nextInt(2);
        gifImageView.setImageResource(R.drawable.money);
        GifDrawable gifDrawable;
        gifDrawable = (GifDrawable) gifImageView.getDrawable();
        gifDrawable.setLoopCount(1);
        gifDrawable.start();
        MediaController mediaController = new MediaController(this);
        mediaController.setMediaPlayer((GifDrawable) gifImageView.getDrawable());
        mediaController.setAnchorView(gifImageView);

        try {
Thread.sleep(1000);
            if (chance == 1) {
                Thread.sleep(1000);
                imageButton.setImageResource(R.drawable.eagle);
                //mediaController.show();
            }
            if (chance == 0) {
                Thread.sleep(1000);
                imageButton.setImageResource(R.mipmap.ic_talis);
            }
        } catch (Exception e) {

        }

    }
}
