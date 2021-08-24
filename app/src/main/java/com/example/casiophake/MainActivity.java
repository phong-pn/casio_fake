package com.example.casiophake;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.casiophake.fragment.BasicFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BasicFragment basicFragment = new BasicFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment, basicFragment)
                    .commit();
        }
    }
}