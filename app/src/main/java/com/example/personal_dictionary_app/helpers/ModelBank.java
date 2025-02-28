package com.example.personal_dictionary_app.helpers;

import android.content.SharedPreferences;

import com.example.personal_dictionary_app.data.Model;
import com.google.gson.Gson;

public class ModelBank<T extends Model> {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private String modelKey;
    private Class<T> modelClass;

    private static Gson gson = new Gson();

    public ModelBank(SharedPreferences sharedPref, SharedPreferences.Editor editor, String modelKey, Class<T> modelClass) {
        this.sharedPref = sharedPref;
        this.editor = editor;
        this.modelKey = modelKey;
        this.modelClass = modelClass;
    }
}
