package com.example.casiophake.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.example.casiophake.R;

public class CaculateSpaceFragment extends Fragment {
    public MutableLiveData<Integer> state = new MutableLiveData<>();
    private TextView input, output;
    public CaculateSpaceFragment(int state) {
        this.state.postValue(state);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.caculate_space, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        input = view.findViewById(R.id.input);
        output = view.findViewById(R.id.output);
        state.observe(getViewLifecycleOwner(), (state)->{
            if(state == BasicFragment.FOCUS_INPUT) focusInput();
            else focusOutput();
        });
        state.postValue(0);
    }

    private void focusOutput() {
        output.setTextSize(24);
        input.setTextSize(14);
    }

    private void focusInput() {
        input.setTextSize(24);
        output.setTextSize(14);
    }
}
