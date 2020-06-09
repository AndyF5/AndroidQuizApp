package com.example.tp1quiz.datamodels;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.tp1quiz.models.Question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class QuestionDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "questions";
    public static final String COLUMN_NAME_QUESTIONTEXT = "questionText";
    public static final String COLUMN_NAME_CORRECTANSWER = "correctAnswer";
    public static final String COLUMN_NAME_WRONGANSWER1 = "wrongAnswer1";
    public static final String COLUMN_NAME_WRONGANSWER2 = "wrongAnswer2";
    public static final String COLUMN_NAME_WRONGANSWER3 = "wrongAnswer3";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_QUESTIONTEXT + " TEXT," +
                    COLUMN_NAME_CORRECTANSWER + " TEXT," +
                    COLUMN_NAME_WRONGANSWER1 + " TEXT," +
                    COLUMN_NAME_WRONGANSWER2 + " TEXT," +
                    COLUMN_NAME_WRONGANSWER3 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "questionsDB";

    public final File DB_FILE;

    private final Context mContext;
    private SQLiteDatabase mDataBase;
    private String dbPath;

    public QuestionDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        DB_FILE = context.getDatabasePath(DATABASE_NAME);
        this.mContext = context;

        dbPath = DB_FILE.getAbsolutePath();
    }

    public void prepareDatabase() throws IOException {
        //boolean dbExist = checkDataBase();
        //if(!dbExist) {
            try {
                copyDataBase();
            }
            catch (IOException ex) {
                throw new Error("Error Copying Database");
            }
        //}
    }

    private boolean checkDataBase() {
        return DB_FILE.exists();
    }

    private void copyDataBase() throws IOException {
        InputStream is = mContext.getAssets().open(DATABASE_NAME);
        OutputStream os = new FileOutputStream(dbPath);
        byte[] buffer = new byte[1024];
        int length;

        while((length = is.read(buffer)) > 0){
            os.write(buffer, 0, length);
        }
        is.close();
        os.flush();
        os.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<Question> getQuestions(int nbQuestions) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        String query =
                "SELECT " +
                COLUMN_NAME_QUESTIONTEXT + ", " +
                COLUMN_NAME_CORRECTANSWER + ", " +
                COLUMN_NAME_WRONGANSWER1 + ", " +
                COLUMN_NAME_WRONGANSWER2 + ", " +
                COLUMN_NAME_WRONGANSWER3 +
                " FROM " + TABLE_NAME +
                " ORDER BY RANDOM() LIMIT " +
                Integer.toString(nbQuestions) ;
        Cursor cursor = db.rawQuery(query, null);
        List<Question> questions = new ArrayList<>();
        while(cursor.moveToNext()) {
            Question question = new Question(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
            );
            questions.add(question);
        }
        cursor.close();
        db.close();
        return questions;
    }

    private int getVersionID(){
        return 1;
    }
}

