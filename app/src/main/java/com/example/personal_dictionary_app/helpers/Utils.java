package com.example.personal_dictionary_app.helpers;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personal_dictionary_app.data.Model;

import java.util.List;

public class Utils {
    public static void longToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void toast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String getText(EditText text) {
        return String.valueOf(text.getText());
    }

    public static String getText(TextView text) {
        return String.valueOf(text.getText());
    }

    public static int getLatestId(List<? extends Model> models) {
        int maxId = 0;
        for (Model model : models) {
            if (model.getId() > maxId) {
                maxId = model.getId();
            }
        }
        return maxId;
    }
}
