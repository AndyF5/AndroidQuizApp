package com.example.tp1quiz;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp1quiz.databinding.FragmentFinishBinding;
import com.example.tp1quiz.viewmodels.QuizViewModel;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinishFragment extends Fragment {

    public FinishFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentFinishBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_finish, container, false);

        View view = binding.getRoot();

        QuizViewModel quizVM =  Objects.requireNonNull(((QuizActivity)getActivity()).getViewModel());

        binding.setQuizVM(quizVM);

        return view;
    }
}
