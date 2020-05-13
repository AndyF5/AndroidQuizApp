package com.example.tp1quiz.datamodels;

public class QuestionDTO {
    private String QuestionText;
    private String CorrectAnswer;
    private String WrongAnswer1;
    private String WrongAnswer2;
    private String WrongAnswer3;

    public QuestionDTO() {
    }

    public QuestionDTO(String questionText, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) {
        QuestionText = questionText;
        CorrectAnswer = correctAnswer;
        WrongAnswer1 = wrongAnswer1;
        WrongAnswer2 = wrongAnswer2;
        WrongAnswer3 = wrongAnswer3;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public String getWrongAnswer1() {
        return WrongAnswer1;
    }

    public String getWrongAnswer2() {
        return WrongAnswer2;
    }

    public String getWrongAnswer3() {
        return WrongAnswer3;
    }
}
