package com.jyue.horizontalnumberpicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class HorizontalNumberPickerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<String> datas;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_number_picker);
        findViews();
        initData();
        setAdapter();
        setRecyclerView();
        setOnClickListener();
        recyclerView.scrollToPosition(4);
    }

    private void findViews () {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        button = (Button) findViewById(R.id.button);
    }

    private void initData () {
        datas = new ArrayList<>();

        for(int i =1;i <= 16;i++) {
            if (i <= 3 || i >= 14) {
                datas.add("");
            } else {
                datas.add(String.valueOf(i-3));
            }
        }
    }

    private void setAdapter() {
        recyclerViewAdapter = new RecyclerViewAdapter(this, datas);
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        new LinearSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void setOnClickListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HorizontalNumberPickerActivity.this, String.valueOf(getScrollPosition()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getScrollPosition() {
        return (int) (((double) recyclerView.computeHorizontalScrollOffset() / (double) recyclerViewAdapter.getItemWidth())+0.5f) + 1;
    }
}