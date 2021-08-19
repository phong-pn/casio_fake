package com.example.casiophake.View;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.casiophake.Model.Expression;
import com.example.casiophake.R;
import com.example.casiophake.ViewModel.ModelViewModel;
import com.example.casiophake.adapter.ScreenRecycleViewAdapter;
import com.example.casiophake.brain.Brain;
import com.example.casiophake.databinding.FragmentBasicViewBinding;

import org.jetbrains.annotations.NotNull;
import com.example.casiophake.adapter.ScreenRecycleViewAdapter.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BasicView extends Fragment {
    public static final String CURRENT_POSITION = "CURRENT_POSITION";
    private FragmentBasicViewBinding binding;
    private RecyclerView screen;
    private ScreenRecycleViewAdapter screenAdapter;
    private int currentPos;
    private ViewHolder currentOperator;
    private EditText input;
    private List<Expression>expressionList;
    private MutableLiveData<List<Expression>> observe;
    private HashMap<Integer, ViewHolder> holderHashMap = new HashMap<>();
    public String width;
    private         ConstraintLayout container ;



    public BasicView() {
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBasicViewBinding.inflate(inflater, container, false);

        ModelViewModel viewModel = new ViewModelProvider(this).get(ModelViewModel.class);

        if(ModelViewModel.getListExpressions().size()==0) ModelViewModel.insertExpression(new Expression(" ", 0));

        expressionList = new ArrayList<>();
        expressionList.add(new Expression("",0));
        observe = new MutableLiveData<>();
        observe.postValue(expressionList);

        screenAdapter = new ScreenRecycleViewAdapter(
                expressionList,
                (v) -> BasicView.this.onClick(v),
                (position, holder) -> {
                    currentOperator = holder;
                    if(holderHashMap.containsKey(position)) holderHashMap.remove(position);
                    holderHashMap.put(position, holder);
                });



        ModelViewModel.allExpressions.observe(requireActivity(), expressions -> {

        });
        screen = binding.screen;
        screen.setLayoutManager(new LinearLayoutManager(requireActivity()));
        screen.setAdapter(screenAdapter);
        screen.setHasFixedSize(false);

        observe.observe(getViewLifecycleOwner(), expressions -> {
            screenAdapter.notifyItemChanged(expressions.size());
            screen.scrollToPosition(expressions.size());
        });




        if(savedInstanceState == null) {
           currentPos = 0;
        }
        else {
            currentPos = savedInstanceState.getInt(CURRENT_POSITION);
        }
        currentOperator = holderHashMap.get(currentPos);
        return binding.getRoot();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
         container = view.findViewById(R.id.container);
        ViewTreeObserver vto = container.getViewTreeObserver();
        vto.addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                width = String.valueOf(container.getMeasuredWidth());
                container.getViewTreeObserver().removeOnGlobalLayoutListener(this::onDraw);

            }


        });
        Executors.newFixedThreadPool(1).execute(()->{
            while (width == null) {}
            int w = Integer.parseInt(width);
            ViewGroup gridBottomButton = binding.gridBottomButton;

            for(int i=0; i<gridBottomButton.getChildCount();i++){
                View item = gridBottomButton.getChildAt(i);
                Log.d("ahihi", "ddbh"+Integer.toString(w));
                item.getLayoutParams().width = w/5-24;
                item.setOnClickListener(v -> onClickForButton(v));
            }
            Log.d("ahihi", "ddbh"+Integer.toString(w));

        });
        while (width == null) {}
     //   Log.d("ahihi", "cc"+Integer.toString(width));
        //set the width and onClickListener for buttons in gridBottomButton
        ViewGroup gridBottomButton = binding.gridBottomButton;


//        for(int i=0; i<gridBottomButton.getChildCount();i++){
//            View item = gridBottomButton.getChildAt(i);
//           // Log.d("ahihi", "ddbh"+Integer.toString(width));
//            item.getLayoutParams().width = 444/5-24;
//            item.setOnClickListener(v -> onClickForButton(v));
//        }
        //Log.d("ahihi", "ddbh"+Integer.toString(width));


        super.onViewCreated(view, savedInstanceState);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.add_operator) {
            expressionList.add(new Expression("",0));
            observe.postValue(expressionList);
        }
    }

    /** Call when one of button is clicked*/
    public void onClickForButton(View view){

        int id = view.getId();
        Button button = (Button) view;
        input = currentOperator.input;
        StringBuffer inputText = new StringBuffer(input.getText().toString());

        switch (id){
            case R.id.equal_button:
                Float output = Brain.solve(inputText.toString());
                if(output!=null)
                    currentOperator.output.setText(output.toString());
                ModelViewModel.insertExpression(new Expression(inputText.toString(), output));
                break;

            case R.id.number_0:
            case R.id.number_1:
            case R.id.number_2:
            case R.id.number_3:
            case R.id.number_4:
            case R.id.number_5:
            case R.id.number_6:
            case R.id.number_7:
            case R.id.number_8:
            case R.id.number_9:
            case R.id.operator_plus:
            case R.id.operator_div:
            case R.id.operator_minus:
            case R.id.operator_x:
            case R.id.dot_button:
            case R.id.ans_button:
                inputText.append(button.getText().toString());

                input.setText(inputText);
                break;

            case R.id.delete_button:
                if(inputText.length()>0) {
                    inputText.deleteCharAt(inputText.length() - 1);
                    input.setText(inputText);

                }
                break;

            case R.id.clear_button:
                if(inputText.length()>0) {
                    inputText.delete(0, inputText.length());
                    input.setText(inputText);
                }
        }
        input.setSelection(inputText.length());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_POSITION, currentPos);
    }
}