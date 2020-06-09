package com.example.tp1quiz.models;

import android.content.Context;

import com.example.tp1quiz.datamodels.QuestionDAO;
import com.example.tp1quiz.datamodels.QuestionDTO;

import java.util.ArrayList;
import java.util.List;

public class QuizGenerator {
    private QuestionDAO dao;

    public List<Question> getQuestions(int numQuestions)
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


    public List<Question> getQuestions(int numQuestions, Context context){

        dao = new QuestionDAO(context);

        dao.open();

        /*
        for (int i = 1; i <= numQuestions; i++){
            dao.insert(new QuestionDTO("Question" + i, "Correct", "Wrong", "Wrong", "Wrong"));
        }*/

        List<Question> questions = dao.readQuestions(numQuestions);

        dao.close();

        return questions;
    }
}
