package com.example.casiophake.fragment;

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
        output.setTextSize(30);
        input.setTextSize(14);
    }

    private void focusInput() {
        input.setTextSize(30);
        output.setTextSize(14);
    }

    public void getInput(String inputText){
        switch (inputText){
            case "=":
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

                break;

            case "D":


                break;

            case "C":

                }
        }
    }

