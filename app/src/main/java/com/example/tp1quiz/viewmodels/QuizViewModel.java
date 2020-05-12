package com.example.tp1quiz.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.tp1quiz.models.Question;
import com.example.tp1quiz.models.QuizGenerator;

import java.util.List;

public class QuizViewModel extends BaseObservable {
    private final String name;
    private List<Question> questions;
    private int currentQuestionNumber;
    private int nbQuestions;
    public final ObservableInt score = new ObservableInt();
    public final ObservableField<Question> currentQuestion = new ObservableField<>();

    public QuizViewModel(String name, int nbQuestions) {
        this.name = name;
        score.set(0);
        QuizGenerator qg = new QuizGenerator();
        this.nbQuestions = nbQuestions;
        this.questions = qg.getQuestions(nbQuestions);
        this.currentQuestionNumber = 0;
        currentQuestion.set(questions.get(currentQuestionNumber));
    }

    public String getName() {
        return name;
    }

    public int getNbQuestions() {
        return nbQuestions;
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }

    public void incrementQuestion(){
        currentQuestionNumber++;

        currentQuestion.set(questions.get(currentQuestionNumber));
    }
}
