package com.example.tp1quiz.viewmodels;

import com.example.tp1quiz.models.Question;
import com.example.tp1quiz.models.QuizGenerator;

import java.util.List;

public class QuizViewModel {
    private final String name;
    private int score;
    private List<Question> questions;
    private int currentQuestionNumber;

    public QuizViewModel(String name, int nbQuestions) {
        this.name = name;
        score = 0;
        QuizGenerator qg = new QuizGenerator();
        this.questions = qg.GetQuestions(nbQuestions);
        this.currentQuestionNumber = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public void setCurrentQuestionNumber(int currentQuestionNumber) {
        this.currentQuestionNumber = currentQuestionNumber;
    }

    public Question getCurrentQuestion(){
        return questions.get(currentQuestionNumber);
    }
}
