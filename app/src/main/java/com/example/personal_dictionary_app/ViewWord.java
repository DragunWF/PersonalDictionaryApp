package com.example.personal_dictionary_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.personal_dictionary_app.data.Word;
import com.example.personal_dictionary_app.helpers.DatabaseHelper;
import com.example.personal_dictionary_app.helpers.Utils;

public class ViewWord extends AppCompatActivity {
    private int currentWordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_word);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {
            currentWordId = getIntent().getIntExtra("wordId", -1);

            setData();
            setButtons();
        } catch (Exception err) {
            err.printStackTrace();
            Utils.longToast(err.getMessage(), this);
        }
    }

    private void setData() {
        // Pa set lahat ng textview dito, Isaac
        Word word = DatabaseHelper.getWordBank().get(currentWordId);
    }

    private void setButtons() {

    }
}