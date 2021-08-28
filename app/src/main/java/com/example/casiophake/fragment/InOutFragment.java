package com.example.casiophake.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.casiophake.R;

public class InOutFragment extends Fragment {
    private TextView input, output;
    private View keyboardPlus;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.in_out_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        input = view.findViewById(R.id.input);
        output = view.findViewById(R.id.output);
        keyboardPlus = requireActivity().findViewById(R.id.keyboard_plus);
    }

    private void focusOutput() {
        output.setTextSize(45);
        input.setTextSize(14);
    }

    private void focusInput() {
        input.setTextSize(45);
        output.setText("");
    }

    public void getInput(String inputText){
        focusInput();
        switch (inputText){
            case "=":
                focusOutput();
                hide();

                break;

            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "/":
            case "x":
            case "+":
            case "-":
            case ".":
            case "ANS":
            case "%":
                show();


                break;

            case "D":


                break;

            case "C":

                }
        }
        public void show(){
            keyboardPlus.setVisibility(View.VISIBLE);
            keyboardPlus.animate()
                    .translationY(0)
                    .alpha(1.0f)
                    .setListener(null);
        }

        public void hide(){
            keyboardPlus.animate()
                    .translationY(keyboardPlus.getHeight())
                    .alpha(0.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            keyboardPlus.setVisibility(View.GONE);
                        }
                    });
        }

    }

