package com.example.casiophake.fragment;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.casiophake.R;

import java.util.concurrent.atomic.AtomicBoolean;

public class KeyBoardFragment extends Fragment {
    public KeyBoardFragment() {
    }

    public KeyBoardFragment(View.OnClickListener listener) {
        this.onButtonClick = listener;
    }

    private View.OnClickListener onButtonClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.keyboard_fragment, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AtomicBoolean isSetSucess = new AtomicBoolean(false);
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(() -> {
            ViewGroup layout =  view.findViewById(R.id.buttons_caculator_container);
            int sizeInDP = 8;

            int marginInDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, sizeInDP, getResources()
                            .getDisplayMetrics());

            view.requestLayout();
            if (!isSetSucess.get()) {
                for (int i = 0; i < layout.getChildCount(); i++) {
                    View child = layout.getChildAt(i);
                    ViewGroup.LayoutParams params = child.getLayoutParams();
                    params.width = layout.getMeasuredWidth()/4-2*marginInDp;
                    child.setLayoutParams(params);
                    Log.d("vcll", ((Button)child).getText().toString());
                    child.setOnClickListener(onButtonClick);

                }
                isSetSucess.set(true);
            }
        });


    }
}
