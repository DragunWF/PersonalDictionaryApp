package com.example.personal_dictionary_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.personal_dictionary_app.R;
import com.example.personal_dictionary_app.data.Word;
import com.example.personal_dictionary_app.helpers.DatabaseHelper;
import com.example.personal_dictionary_app.helpers.ModelBank;
import com.example.personal_dictionary_app.helpers.Utils;
import com.example.personal_dictionary_app.services.WordService;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private List<Word> localDataSet;
    private Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordText, dateAddedText, categoryText;
        private final Button viewBtn, deleteBtn;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            wordText = view.findViewById(R.id.wordText);
            dateAddedText = view.findViewById(R.id.dateAddedText);
            categoryText = view.findViewById(R.id.categoryText);
            viewBtn = view.findViewById(R.id.viewBtn);
            deleteBtn = view.findViewById(R.id.deleteBtn);
        }

        public TextView getWordText() {
            return wordText;
        }

        public TextView getDateAddedText() {
            return dateAddedText;
        }

        public TextView getCategoryText() {
            return categoryText;
        }

        public Button getViewBtn() {
            return viewBtn;
        }

        public Button getDeleteBtn() {
            return deleteBtn;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public WordAdapter(List<Word> dataSet, Context context) {
        localDataSet = dataSet;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycle_view_card, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//         viewHolder.getTextView().setText(localDataSet[position]);
        Word word = localDataSet.get(position);
        viewHolder.getWordText().setText(word.getWord());
        viewHolder.getDateAddedText().setText(word.getDate().toString());
        viewHolder.getCategoryText().setText(word.getCategory());
        viewHolder.getViewBtn().setOnClickListener(v -> {

        });
        viewHolder.getDeleteBtn().setOnClickListener(v -> {
            WordService.deleteWord(word.getId());
            Utils.toast(word.getWord() + " has been deleted!", context);
            updateData();
        });
    }

    public void updateData() {
        ModelBank<Word> wordBank = DatabaseHelper.getWordBank();
        updateData(wordBank.getAll());
    }

    public void updateData(List<Word> dataSet) {
        localDataSet.clear();
        for (Word word : dataSet) {
            localDataSet.add(word);
        }
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
