package com.example.andrej.simple;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andrej.simple.java.classes.TranslaterRusToEng;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class WeatherActivity extends Activity {

    private Button button;
    private TextView textView;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        button =  findViewById(R.id.knowtheweather);
        textView =  findViewById(R.id.aboutTheWeather);
        editText = findViewById(R.id.enteringTheCity);
        textView.setMovementMethod(new ScrollingMovementMethod());


    }



    public void ClickMe(View v) {
        MyTask mt = new MyTask();

        mt.execute(editText.getText().toString());
    }



    class MyTask extends AsyncTask<String, Void, Void> {

        String title;//Тут храним значение заголовка сайта
        String tempCity;


        @Override
        protected Void doInBackground(String... params) {

            ArrayList<String> stringArrayListEng = new ArrayList<>();
            ArrayList<String> stringArrayListRus = new ArrayList<>();

            Scanner scannerEng = new Scanner(getResources().openRawResource(R.raw.dictionaryeng));
            Scanner scannerRus = new Scanner(getResources().openRawResource(R.raw.dictionaryru));

            try {
                while(scannerEng.hasNext()){
                    stringArrayListEng.add(scannerEng.next());
                }

                while (scannerRus.hasNext()){
                    stringArrayListRus.add(scannerRus.next());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                scannerEng.close();
                scannerRus.close();
            }

            for (int i = 0; i < stringArrayListEng.size(); i++) {
                Log.d("TEstList", stringArrayListEng.get(i));
            }

            String city = params[0];
            TranslaterRusToEng rusToEng = new TranslaterRusToEng();
            tempCity = rusToEng.getTranslatedWord(city,stringArrayListEng,stringArrayListRus);
            Log.d("TestCity", tempCity);
            Document doc = null;//Здесь хранится будет разобранный html документ
            Elements main;
            Elements divfact;
            Elements timeNow;//время
            Elements spanNow;//температура сейчас
            Elements divSunny;//ясно ли?
            Elements dlNow;//ощущается
            Elements dlYesterday;//вчера в это время
            Elements divAnother;//ветер давление влажность
            Elements divDay;
            Elements divDayFirst;
            Elements divDaySecond;
            Elements divNow;
            ArrayList<String> result = new ArrayList<>();

            try {

                doc = Jsoup.connect("https://yandex.ru/pogoda/"+tempCity).get();
                //doc = Jsoup.parse(new URL("https://yandex.ru/pogoda/"+tempCity), 3000);

                main = doc.select("div[class=content__row]");
                divfact = main.select("div[class=fact]");
                timeNow = divfact.select("time[class=time fact__time]");//время
                spanNow = divfact.select("div[class=temp fact__temp]");//температура сейчас
                divSunny = divfact.select("div[class=fact__condition day-anchor i-bem]");//ясно ли?
                dlNow = divfact.select("dl[class=term term_orient_h fact__feels-like]");//ощущается
                dlYesterday = divfact.select("dl[class=term term_orient_h fact__yesterday]");//вчера в это время
                divAnother = divfact.select("div[class=fact__props]");//ветер давление влажность
                divDay = main.select("div[class=content__brief]");
                divDayFirst = divDay.select("div[class=day-parts-next i-bem]");
                divDaySecond = divDay.select("div[class=details-celestial-bodies]");

                divNow = divfact.select("div[class=nowcast-alert__text nowcast-alert__text_small]");//на данный момент


                result.add(timeNow.text() + " " + spanNow.text());
                result.add(divSunny.text());
                result.add(dlNow.text());
                result.add(dlYesterday.text());
                result.add(divAnother.text());
                result.add(divNow.text());
                result.add(divDayFirst.text());
                result.add(divDaySecond.text());




            } catch (IOException e) {
                //Если не получилось считать
                e.printStackTrace();
                title = "Ошибка, проверьте Интернет соединение";
            }

            //Если всё считалось, что вытаскиваем из считанного html документа заголовок
            if (doc != null)
                title = result.get(0) +"\n"+ result.get(1) +"\n"+ result.get(2) +"\n"+ result.get(3) +"\n"+ result.get(4)
                        +"\n"+ result.get(5) +"\n"+ result.get(6) +"\n"+ result.get(7);
            else
                title = "Ошибка, проверьте Интернет соединение";

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            textView.setText(title);
        }
    }


}
