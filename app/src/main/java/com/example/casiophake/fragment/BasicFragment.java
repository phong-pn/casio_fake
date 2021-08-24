package com.example.casiophake.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.casiophake.R;
import com.example.casiophake.ViewModel.ModelViewModel;
import com.example.casiophake.databinding.BasicFragmentBinding;

import org.jetbrains.annotations.NotNull;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BasicFragment extends Fragment {
    public static final String CURRENT_POSITION = "CURRENT_POSITION";
    private @NonNull BasicFragmentBinding binding;




    public BasicFragment() {
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = BasicFragmentBinding.inflate(inflater, container, false);

        InOutFragment inOutFragment = new InOutFragment();



        getParentFragmentManager().beginTransaction()
                .add(R.id.keyboard_fragment_container, new KeyBoardFragment((v)-> inOutFragment.getInput(((Button)v).getText().toString())))
                .add(R.id.in_out_fragment_container, inOutFragment)
                .commit();

        ModelViewModel viewModel = new ViewModelProvider(this).get(ModelViewModel.class);
        return binding.getRoot();
    }
}