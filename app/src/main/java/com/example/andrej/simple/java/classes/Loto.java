package com.example.andrej.simple.java.classes;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Loto extends AsyncTask<String, Void, String> {

    private String name;


    @Override
    protected String doInBackground(String... strings) {
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
            stringBuilder.append(text+"\n");
        }
            String answer = String.valueOf(stringBuilder);
        return answer;
    }

    /*public static void main(String[] args) throws IOException {
        Loto loto = new Loto();
        Iterator<String> stringIterator = loto.getFourOnTwenty("4x20").iterator();
        while (stringIterator.hasNext()) {
            String text = stringIterator.next();
            System.out.println(text);
        }

        Iterator<String> stringIterator6 = loto.getFourOnTwenty("6x45").iterator();
        while (stringIterator6.hasNext()) {
            String text = stringIterator6.next();
            System.out.println(text);
        }
    }*/
}
