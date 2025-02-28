package com.example.personal_dictionary_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.personal_dictionary_app.data.Word;
import com.example.personal_dictionary_app.helpers.DatabaseHelper;
import com.example.personal_dictionary_app.helpers.Utils;
import com.example.personal_dictionary_app.services.WordService;

public class ViewWord extends AppCompatActivity {

    private TextView wordTxt, definitionTxt, usageTxt, categoryTxt, dateTxt;
    private Button editBtn, deleteBtn;

    private int currentWordId;

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }

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
            wordTxt = findViewById(R.id.textView6);
            definitionTxt = findViewById(R.id.usageText);
            usageTxt = findViewById(R.id.usageText);
            categoryTxt = findViewById(R.id.textView16);
            dateTxt = findViewById(R.id.dateAddedText);
            editBtn = findViewById(R.id.editBtn);
            deleteBtn = findViewById(R.id.deleteBtn);

            currentWordId = getIntent().getIntExtra("wordId", -1);

            setData();
            setButtons();
        } catch (Exception err) {
            err.printStackTrace();
            Utils.longToast(err.getMessage(), this);
        }
    }

    private void setData() {
        Word word = DatabaseHelper.getWordBank().get(currentWordId);

        wordTxt.setText(word.getWord());
        definitionTxt.setText(word.getDefinition());
        usageTxt.setText(word.getUsage().isEmpty() ? "None" : word.getUsage());
        categoryTxt.setText(word.getCategory());
        dateTxt.setText(word.getDate());
    }

    private void setButtons() {
        editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddView.class);
            intent.putExtra("wordId", currentWordId);
            startActivity(intent);
        });
        deleteBtn.setOnClickListener(v -> {
            WordService.deleteWord(currentWordId);
            Utils.longToast("Word has been deleted!", ViewWord.this);
            finish();
        });
    }
}