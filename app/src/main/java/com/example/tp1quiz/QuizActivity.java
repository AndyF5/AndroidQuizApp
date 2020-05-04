package com.example.tp1quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tp1quiz.databinding.ActivityQuizBinding;

import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    private int score = 0;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityQuizBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);

        name = getIntent().getStringExtra("EXTRA_SESSION_NAME");

        Toast.makeText(this, name + " Clicked Play -> open quiz", Toast.LENGTH_LONG).show();

        binding.quizzerName.setText(name);
        binding.quizzerScore.setText(String.valueOf(score));
    }
}
