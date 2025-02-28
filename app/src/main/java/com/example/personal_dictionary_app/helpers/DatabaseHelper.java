package com.example.personal_dictionary_app.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.personal_dictionary_app.data.Word;

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
}
