package com.example.casiophake.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.casiophake.R;

public class InOutFragment extends Fragment {
    private TextView input, output;

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
    }

    private void focusOutput() {
        output.setTextSize(45);
        input.setTextSize(14);
    }

    private void focusInput() {
        input.setTextSize(45);
        output.setText("");
    }

    public void getInput(View view){
        focusInput();
        int id = view.getId();
        switch (id){
            case R.id.equal:
                focusOutput();

                break;

            case R.id.zero:
            case R.id.one:
            case R.id.two:
            case R.id.twozezo:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
            case R.id.plus:
            case R.id.minus:
            case R.id.multiply:
            case R.id.split:
            case R.id.luythua:
            case R.id.giaithua:
            case R.id.percent:
            case R.id.mongoac:
            case R.id.dongngoac:
                focusInput();

                break;

            case R.id.delete:


                break;

            case R.id.clear:

                }
        }



    }

