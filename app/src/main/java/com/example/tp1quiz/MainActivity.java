package com.example.tp1quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);
    }

    //  Avancer à le quiz.
    public void onClickBtnPlay(View v){

        //  Get nom.
        EditText nameET = (EditText) findViewById(R.id.dataName);
        String name = nameET.getText().toString();

        //  Vérifier le nom.
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