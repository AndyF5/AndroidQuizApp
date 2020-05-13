package com.example.tp1quiz.datamodels;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tp1quiz.models.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    private SQLiteDatabase db;
    private QuestionDbHelper dbHelper;

    public QuestionDAO(Context context) {
        dbHelper = new QuestionDbHelper(context);
        try {
            dbHelper.prepareDatabase();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void open(){
        db = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(QuestionDTO q) {
        ContentValues values = new ContentValues();
        values.put(QuestionDbHelper.COLUMN_NAME_QUESTIONTEXT, q.getQuestionText());
        values.put(QuestionDbHelper.COLUMN_NAME_CORRECTANSWER, q.getCorrectAnswer());
        values.put(QuestionDbHelper.COLUMN_NAME_WRONGANSWER1, q.getWrongAnswer1());
        values.put(QuestionDbHelper.COLUMN_NAME_WRONGANSWER2, q.getWrongAnswer2());
        values.put(QuestionDbHelper.COLUMN_NAME_WRONGANSWER3, q.getWrongAnswer3());

        long idInsert = db.insert(QuestionDbHelper.TABLE_NAME, null, values);


    }

    public List<Question> readQuestions(){
        return dbHelper.getQuestions();
    }

}
