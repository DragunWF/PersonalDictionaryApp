package com.example.personal_dictionary_app.data;

import java.time.LocalDate;

public class Word extends Model{

    String word, definition, usage, category, date;

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", definition='" + definition + '\'' +
                ", usage='" + usage + '\'' +
                ", category='" + category + '\'' +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
    public Word(String word, String date, String category, String usage, String definition) {
        this.word = word;
        this.date = date;
        this.category = category;
        this.usage = usage;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
