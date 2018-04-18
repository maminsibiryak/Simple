package com.example.andrej.simple.java.classes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;



public class Loto {

    public Set<String> getFourOnTwenty(String name) throws IOException {
        List<String> result = new ArrayList<>();
        Document mainDiv = Jsoup.connect("https://www.stoloto.ru/"+name+"/archive").get();
        Elements monthDiv = mainDiv.select("div[class=month]");
        Elements numberDiv = monthDiv.select("div[class=container cleared]");
        String[] elements = numberDiv.text().split(" ");
        int count = 0;

        double [] intElements = new double[elements.length];
        for (int i = 0; i <intElements.length ; i++) {
            intElements[i]=Double.parseDouble(elements[i]);
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


        return stringSet;
    }

    public static void main(String[] args) throws IOException {
        Loto loto = new Loto();
        Iterator<String> stringIterator = loto.getFourOnTwenty("4x20").iterator();
        while (stringIterator.hasNext()) {
            String text = stringIterator.next();
            System.out.println(text);
        }
    }
}
