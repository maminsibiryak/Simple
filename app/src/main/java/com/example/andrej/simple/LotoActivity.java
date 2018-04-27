package com.example.andrej.simple;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LotoActivity extends AppCompatActivity {
    private TextView textViewLoto;
    private EditText editTextLoto;
    private Button buttonLoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loto);

        buttonLoto = findViewById(R.id.buttonLoto);
        textViewLoto = findViewById(R.id.textViewLoto);
        textViewLoto.setMovementMethod(new ScrollingMovementMethod());
        editTextLoto = findViewById(R.id.editTextLoto);
    }

    public void getResult(View view6) throws IOException {
        Loto loto = new Loto();
        loto.execute(editTextLoto.getText().toString());

    }

    public class Loto extends AsyncTask<String, Void, String> {


        private String name;
        private String answer;


        @Override
        protected String doInBackground(String... strings) {
            name = strings[0];
            List<String> result = new ArrayList<>();
            Document mainDiv = null;
            try {
                mainDiv = Jsoup.connect("https://www.stoloto.ru/" + name + "/archive").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements monthDiv = mainDiv.select("div[class=month]");
            Elements numberDiv = monthDiv.select("div[class=container cleared]");
            String[] elements = numberDiv.text().split(" ");
            int count = 0;

            double[] intElements = new double[elements.length];
            for (int i = 0; i < intElements.length; i++) {
                intElements[i] = Double.parseDouble(elements[i]);
            }


            //Arrays.sort(intElements);


            for (int i = 0; i < intElements.length; i++) {

                double zero = intElements[i];
                for (int j = 0; j < elements.length; j++) {


                    if (zero == intElements[j]) {
                        count++;
                    }
                }
                result.add(elements[i] + " встретился " + count + " раз");
                count = 0;
            }


            Set<String> stringSet = new HashSet(result);
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<String> stringIterator = stringSet.iterator();
            while (stringIterator.hasNext()) {
                String text = stringIterator.next();
                stringBuilder.append(text + "\n");
            }
            answer = String.valueOf(stringBuilder);
            return answer;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textViewLoto.setText(answer);
        }
    }
}
