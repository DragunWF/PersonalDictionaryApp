package com.example.personal_dictionary_app.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.personal_dictionary_app.data.Word;

import java.time.LocalDate;
import java.util.Date;

public class DatabaseHelper {
    private static final String FILE_KEY = "db";

    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;

    private static ModelBank<Word> wordBank;

    public static void initialize(Context context) {
        sharedPref = context.getSharedPreferences(FILE_KEY, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        wordBank = new ModelBank<>(sharedPref, editor, "words", Word.class);
    }

    public static ModelBank<Word> getWordBank() {
        return wordBank;
    }

    public static void addDummydata(){
        Date date = new Date();
        Word word = new Word("Effect", date.toString(), "","" ,"");
        Word word2 = new Word("Triple", date.toString(), "Numbering","Triple Digit" ,"The count of three");
        Word word3 = new Word("Service", date.toString(), "",
                "The soldier are doing service to their country but fighting in wars" ,
                "the action of helping or doing work for someone.");
        Word word4 = new Word("Overview", date.toString(), "Viewpoint",
                "This is the overview of the design" ,"a general review or summary of a subject");
        wordBank.add(word);
        wordBank.add(word2);
        wordBank.add(word3);
        wordBank.add(word4);
    }
}
