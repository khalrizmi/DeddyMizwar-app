package com.example.hamz.dedymizwarapp.list;

import com.example.hamz.dedymizwarapp.model.Question;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuestionList {

    @SerializedName("data")
    ArrayList<Question> questions;

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
