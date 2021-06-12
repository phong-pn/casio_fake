package com.example.casiophake.View;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.casiophake.MainActivity;
import com.example.casiophake.Model.Model;
import com.example.casiophake.R;
import com.example.casiophake.ViewModel.ModelViewModel;
import com.example.casiophake.adapter.ScreenRecycleViewAdapter;
import com.example.casiophake.databinding.FragmentBasicViewBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BasicView extends Fragment implements ScreenRecycleViewAdapter.OnClickViewHolder {
    private FragmentBasicViewBinding binding;

    private RecyclerView screen;
    private ScreenRecycleViewAdapter screenAdapter;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBasicViewBinding.inflate(inflater, container, false);
        screen = binding.screen;

        ModelViewModel modelViewModel = new ModelViewModel(requireActivity().getApplication());
        ModelViewModel.allModels.observe(requireActivity(), new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> models) {
                screen.setLayoutManager(new LinearLayoutManager(requireActivity()));
                screenAdapter = new ScreenRecycleViewAdapter(models, BasicView.this);
                screenAdapter.setModelList(models);
                screen.setAdapter(screenAdapter);

            }
        });
        return binding.getRoot();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup gridBottomButton = binding.gridBottomButton;
            for(int i=0; i<gridBottomButton.getChildCount();i++){
                View item = gridBottomButton.getChildAt(i);
                item.getLayoutParams().width = (Resources.getSystem().getDisplayMetrics().widthPixels)/5-24;
            }

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(int position) {
        screen.scrollToPosition(position);
    }
}