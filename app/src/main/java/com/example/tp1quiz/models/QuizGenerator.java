package com.example.tp1quiz.models;

import java.util.ArrayList;
import java.util.List;

public class QuizGenerator {
    public List<Question> GetQuestions(int numQuestions)
    {
        List<Question> questions = new ArrayList<>();

        for (int i = 1; i <= numQuestions; i++){
            questions.add(new Question(
               "Question " + i,
                "Correct",
                "Wrong",
                "Wrong",
                "Wrong"
            ));
        }

        return questions;
    }
}
