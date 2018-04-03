package com.example.andrej.simple;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class CinemaActivity extends AppCompatActivity {

    private TextView textView2;
    private ProgressDialog progressDialog;
    private Button button;
    private ListView listView;
    private String[] values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);

        // button = (Button) findViewById(R.id.button3);

        new CinemaPremier().execute();

    }

    public void getPremList(View v) {
        new CinemaPremier().execute();
    }

    private class CinemaPremier extends AsyncTask<Void, Void, Void> {
        private String result;
        private ArrayList<String> list = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CinemaActivity.this);
            progressDialog.setTitle("Получаем премьеры недели");
            progressDialog.setMessage("Загружаем...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document document = Jsoup.connect("https://www.kinopoisk.ru/premiere/").get();
                Elements divPremList = document.select("div[class=prem_list]");
                Elements divWeek = divPremList.select("div[style]");
                Elements divPremItem = divWeek.select("div[class=premier_item]");
                // System.out.println(divPremItem.get(10));
                for (int i = 0; i < divPremItem.size(); i++) {


                    list.add(divPremItem.get(i).text());
                }
                result = divPremItem.text();


                values = new String[list.size()];
                for (int i = 0; i < values.length + 1; i++) {
                    values[i] = list.get(i);
                }
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // textView2 = (TextView) findViewById(R.id.textView2);
            //textView2.setText(result);
            listView = (ListView) findViewById(R.id.cinemaList);


            /*String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
                    "Костя", "Игорь", "Анна", "Денис", "Андрей" };*/

            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(CinemaActivity.this, R.layout.listview, values);

            listView.setAdapter(stringArrayAdapter);

            //textView2.setMovementMethod(new ScrollingMovementMethod());
        }
    }
}
