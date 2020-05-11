package com.example.tp1quiz.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Question {
    private String questionText;
    private List<String> answers;

    public Question(String questionText, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) {
        this.questionText = questionText;
        this.answers = new ArrayList<>();
        answers.add(correctAnswer);
        answers.add(wrongAnswer1);
        answers.add(wrongAnswer2);
        answers.add(wrongAnswer3);
     }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getShuffledAnswers(){
        List<String> shuffledAnswers = new ArrayList<>(answers);

        Collections.shuffle(shuffledAnswers);

        return shuffledAnswers;
    }
}
