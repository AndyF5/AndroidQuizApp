package com.example.tp1quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //  Avancer à le quiz.
    public void onClickBtnPlay(View v){
        EditText nameET = (EditText) findViewById(R.id.dataName);
        String name = nameET.getText().toString();

        if (name.length() <= 2){
            Toast.makeText(this, R.string.message_No_Name, Toast.LENGTH_LONG).show();
        }
        else {
            // Envoyer le nom entré avec intent.
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("EXTRA_SESSION_NAME", name);
            startActivity(intent);
        }
    }
}