package com.example.personal_dictionary_app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.personal_dictionary_app.data.Word;
import com.example.personal_dictionary_app.helpers.DatabaseHelper;
import com.example.personal_dictionary_app.helpers.Utils;
import com.example.personal_dictionary_app.services.WordService;

import java.util.Date;

public class AddView extends AppCompatActivity {
    private final int CHAR_LIMIT = 500;

    private EditText wordText, descriptionText, usageText;
    private Button createBtn;
    private Spinner categorySpinner;

    private int currentWordId; // -1 if the user is adding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {
            DatabaseHelper.initialize(this);

            createBtn = findViewById(R.id.createBtn);

            wordText = findViewById(R.id.wordText);
            descriptionText = findViewById(R.id.descriptionText);
            usageText = findViewById(R.id.descriptionText);

            categorySpinner = findViewById(R.id.categorySpinner);

            currentWordId = getIntent().getIntExtra("wordId", -1);
            boolean isEditForm = currentWordId != -1;
            if (isEditForm) {
                createBtn.setText("Edit");
                autoFillFields();
            }

            setButtons();
            setSpinner();
        } catch (Exception err) {
            err.printStackTrace();
            Utils.longToast(err.getMessage(), this);
        }
    }

    private void autoFillFields() {
        Word currentWord = DatabaseHelper.getWordBank().get(currentWordId);
        wordText.setText(currentWord.getWord());
        descriptionText.setText(currentWord.getDefinition());
        usageText.setText(currentWord.getUsage());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories_array,
                android.R.layout.simple_spinner_item
        );
        categorySpinner.setSelection(adapter.getPosition(currentWord.getCategory()));
    }

    private void setButtons() {
        createBtn.setOnClickListener(v -> {
            String word = Utils.getText(wordText);
            String description = Utils.getText(descriptionText);
            String usage = Utils.getText(usageText);
            String category = categorySpinner.getSelectedItem().toString();

            if (word.isEmpty() || description.isEmpty()) {
                Utils.longToast("Do not leave any of the fields empty!", AddView.this);
                return;
            }

            int[] lengths = { word.length(), description.length(), usage.length() };
            for (int dataLength : lengths) {
                if (dataLength > CHAR_LIMIT) {
                    Utils.longToast("The number of characters on the input fields should not exceed " + CHAR_LIMIT + " characters!", AddView.this);
                    return;
                }
            }

            WordService.addWord(new Word(word, new Date().toString(), category, usage, description));
            Utils.longToast(word + " has been added to your personal dictionary!", AddView.this);
            finish();
        });
    }

    private void setSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories_array,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        categorySpinner.setAdapter(adapter);
    }
}