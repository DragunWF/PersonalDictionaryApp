package com.example.personal_dictionary_app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personal_dictionary_app.adapters.WordAdapter;
import com.example.personal_dictionary_app.data.Word;
import com.example.personal_dictionary_app.helpers.DatabaseHelper;
import com.example.personal_dictionary_app.helpers.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addBtn;
    private SearchView searchBar;
    private Spinner categorySpinner;

    private RecyclerView wordRecycler;
    private WordAdapter wordAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onResume() {
        super.onResume();
        wordAdapter.updateData(DatabaseHelper.getWordBank().getAll());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {
            DatabaseHelper.initialize(this);
            DatabaseHelper.addDummydata();

            wordRecycler = findViewById(R.id.wordRecycler);

            addBtn = findViewById(R.id.button);
            searchBar = findViewById(R.id.searchView);
            categorySpinner = findViewById(R.id.spinner);

            setButtons();
            setRecycler();
            setSearch();
            setSpinner();
        } catch (Exception err) {
            err.printStackTrace();
            Utils.longToast(err.getMessage(), this);
        }
    }

    private void setButtons() {
        addBtn.setOnClickListener(view -> {

        });
    }

    private void setRecycler() {
        wordRecycler.setHasFixedSize(false);

        wordAdapter = new WordAdapter(DatabaseHelper.getWordBank().getAll(), this);
        wordRecycler.setAdapter(wordAdapter);

        layoutManager = new LinearLayoutManager(this);
        wordRecycler.setLayoutManager(layoutManager);
    }

    private void setSearch() {
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                update(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                update(newText);
                return false;
            }

            public void update(String query) {
                List<Word> words = DatabaseHelper.getWordBank().getAll();
                List<Word> results = new ArrayList<>();

                query = query.toLowerCase(); // case insensitivity
                for (Word word : words) {
                    String lowercaseWord = word.getWord().toLowerCase();
                    if (lowercaseWord.contains(query)) {
                        results.add(word);
                    }
                }

                wordAdapter.updateData(results);
            }
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