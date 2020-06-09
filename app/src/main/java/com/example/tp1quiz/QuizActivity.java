package com.example.tp1quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tp1quiz.databinding.ActivityQuizBinding;
import com.example.tp1quiz.viewmodels.QuizViewModel;

import java.util.Locale;
import java.util.Objects;

public class QuizActivity extends AppCompatActivity {

    private QuizViewModel quizVM;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Créer le viewmodel et bind avec le layout.
        ActivityQuizBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);

        String name = getIntent().getStringExtra("EXTRA_SESSION_NAME");

        quizVM = new QuizViewModel(name, 20, this);

        binding.setQuizVM(quizVM);
    }

    //  Pour chercher le VM à partir des Fragments.
    public QuizViewModel getViewModel() {
        return quizVM;
    }

    //  Bouton de navigation rules -> questions.
    public void onClickPlay(View view) {
        //  Naviguer.
        NavDirections action =
                RulesFragmentDirections
                        .actionRulesFragmentToQuestionFragment();

        Navigation.findNavController(view).navigate(action);
    }

    //  Une réponse sélectionné.
    public void onClickAnswer(final View view){
        //  Lequel bouton clické?
        Button clicked = findViewById(view.getId());

        final int[] btnIDs = {R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4};

        for(int btnID : btnIDs ) {
            Button button = findViewById(btnID);
            button.setEnabled(false);
            if(button.getText() == Objects.requireNonNull(quizVM.currentQuestion.get()).getCorrectAnswer()){
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCorrect));
            }
            else {
                button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorIncorrect));
            }
        }

        //  Est-ce que la réponse est la bonne?
        if(clicked.getText() == Objects.requireNonNull(quizVM.currentQuestion.get()).getCorrectAnswer()){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            quizVM.score.set(quizVM.score.get() + 1);
        }
        else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_LONG).show();
        }

        final Context context = this;

        mHandler.postDelayed(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run(){
                        for(int btnID : btnIDs ) {
                            Button button = findViewById(btnID);
                            button.setEnabled(true);
                            button.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBtnBackground));
                        }

                        //  Est-ce que c'est le fin du quiz?
                        if(quizVM.getCurrentQuestionNumber() + 1 < quizVM.getNbQuestions()){
                            //  Chercher la prochaine question.
                            quizVM.incrementQuestion();
                        }
                        else {
                            //  Naviguer à l'onglet de Quiz fini.
                            NavDirections action =
                                    QuestionFragmentDirections
                                            .actionQuestionFragmentToFinishFragment();

                            Navigation.findNavController(view).navigate(action);
                        }
                    }
                });
            }
        }, 3000);


    }

    public void onClickReplay(View view){
        quizVM = new QuizViewModel(quizVM.getName(), 20, this);

        NavDirections action =
                FinishFragmentDirections
                    .actionFinishFragmentToRulesFragment();

        Navigation.findNavController(view).navigate(action);
    }

    //  Override le back press pour rétourner à la page d'accueil plutot que les questions ou page des regles.
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
