package com.example.personal_dictionary_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.personal_dictionary_app.R;
import com.example.personal_dictionary_app.data.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private List<Word> localDataSet;
    private Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private final TextView wordText;
//        private final TextView dateAddedText;
//        private final TextView categoryText;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
//            wordText = view.findViewById(R.id.textView);
//            dataAddedText = view.findViewById(R.id.);
//            categoryText = view.findViewById(R.id.);
        }

//        public TextView getWordText() {
//            return wordText;
//        }
//
//        public TextView getDateAddedText() {
//            return dateAddedText;
//        }
//
//        public TextView getCategoryText() {
//            return categoryText;
//        }
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
//        View view = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.text_row_item, viewGroup, false);

//        return new ViewHolder(view);
        return null;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        // viewHolder.getTextView().setText(localDataSet[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
