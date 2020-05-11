package com.example.tp1quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tp1quiz.databinding.ActivityQuizBinding;
import com.example.tp1quiz.viewmodels.QuizViewModel;

import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    private int score = 0;
    private String name = "";
    private QuizViewModel quizVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityQuizBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);

        name = getIntent().getStringExtra("EXTRA_SESSION_NAME");

        quizVM = new QuizViewModel("Andrew", 3);

        binding.setQuizVM(quizVM);

        Toast.makeText(this, name + " Clicked Play -> open quiz", Toast.LENGTH_LONG).show();
    }

    public QuizViewModel getViewModel() {
        return quizVM;
    }

    public void onClickPlay(View view) {
        NavDirections action =
                RulesFragmentDirections
                        .actionRulesFragmentToQuestionFragment();

        Navigation.findNavController(view).navigate(action);
    }

    public void onClickAnswer(View view){
        Button clicked = findViewById(view.getId());


    }
}
