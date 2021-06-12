package com.example.casiophake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.casiophake.Model.Model;
import com.example.casiophake.View.BasicView;
import com.example.casiophake.ViewModel.ModelViewModel;
import com.example.casiophake.adapter.ScreenRecycleViewAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        BasicView basicView = new BasicView();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment, BasicView.class, null)
                    .commit();
        }


    }
}