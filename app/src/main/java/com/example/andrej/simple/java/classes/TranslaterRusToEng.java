package com.example.andrej.simple.java.classes;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by andrej on 08.03.2018.
 */

public class TranslaterRusToEng extends Application {

    private String word;
    private ArrayList<String> stringArrayListRus;
    private ArrayList<String> stringArrayListEng;

    public TranslaterRusToEng(String word) {
        this.word = word;
    }

    public TranslaterRusToEng() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslatedWord(String w, ArrayList en, ArrayList ru) {
        this.word = w.toLowerCase();
        this.stringArrayListEng = en;
        this.stringArrayListRus = ru;

        String answer = null;


        for (int i = 0; i < stringArrayListRus.size(); i++) {
            if (stringArrayListRus.get(i).toLowerCase().equals(word)) {
                answer = stringArrayListEng.get(i).toLowerCase();
            }
        }

        return answer;
    }
}
