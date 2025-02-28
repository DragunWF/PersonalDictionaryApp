package com.example.personal_dictionary_app.services;

import com.example.personal_dictionary_app.data.Word;
import com.example.personal_dictionary_app.helpers.DatabaseHelper;
import com.example.personal_dictionary_app.helpers.ModelBank;

public class WordService {
    public static void addWord(Word word) {
        ModelBank<Word> bank = DatabaseHelper.getWordBank();
        bank.add(word);
    }

    public static void editWord(Word word) {
        ModelBank<Word> bank = DatabaseHelper.getWordBank();
        bank.update(word);
    }

    public static void deleteWord(int id) {
        ModelBank<Word> bank = DatabaseHelper.getWordBank();
        bank.delete(id);
    }
}
