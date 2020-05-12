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
import java.util.Objects;

public class QuizActivity extends AppCompatActivity {

    private QuizViewModel quizVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Créer le viewmodel et bind avec le layout.
        ActivityQuizBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);

        String name = getIntent().getStringExtra("EXTRA_SESSION_NAME");

        quizVM = new QuizViewModel(name, 3);

        binding.setQuizVM(quizVM);
    }

    public QuizViewModel getViewModel() {
        return quizVM;
    }

    //  Bouton de navigation rules -> questions.
    public void onClickPlay(View view) {
        NavDirections action =
                RulesFragmentDirections
                        .actionRulesFragmentToQuestionFragment();

        Navigation.findNavController(view).navigate(action);
    }

    //  Une réponse sélectionné.
    public void onClickAnswer(View view){
        //  Lequel bouton clické?
        Button clicked = findViewById(view.getId());

        //  Est-ce que la réponse est la bonne?
        if(clicked.getText() == Objects.requireNonNull(quizVM.currentQuestion.get()).getCorrectAnswer()){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            quizVM.score.set(quizVM.score.get() + 1);
        }
        else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_LONG).show();
        }

        //  Est-ce que c'est le fin du quiz?
        if(quizVM.getCurrentQuestionNumber() + 1 < quizVM.getNbQuestions()){
            quizVM.incrementQuestion();
        }
        else {
            Toast.makeText(this, "Finished!", Toast.LENGTH_SHORT).show();
        }
    }
}
