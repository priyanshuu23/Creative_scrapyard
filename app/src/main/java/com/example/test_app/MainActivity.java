package com.example.test_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout creativelayout;
    private LinearLayout scrapyardlayout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the two pages and assign them to member variables
        creativelayout = findViewById(R.id.creative);
        scrapyardlayout = findViewById(R.id.scrapyard);

        // Set the initial page and button colors
        creativelayout.setVisibility(View.VISIBLE);
        scrapyardlayout.setVisibility(View.GONE);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        button1.setBackgroundColor(getResources().getColor(R.color.colorP2));
        button2.setBackgroundColor(getResources().getColor(R.color.colorP4));


        // Set up the button click listeners
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch to page 1
                creativelayout.setVisibility(View.VISIBLE);
                scrapyardlayout.setVisibility(View.GONE);
                button1.setBackgroundColor(getResources().getColor(R.color.colorP2));
                button2.setBackgroundColor(getResources().getColor(R.color.colorP4));
            }
        });
//        to hide button and searchbar



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch to page 2
                creativelayout.setVisibility(View.GONE);
                scrapyardlayout.setVisibility(View.VISIBLE);
                button1.setBackgroundColor(getResources().getColor(R.color.colorP4));
                button2.setBackgroundColor(getResources().getColor(R.color.colorP2));
            }
        });

    }

}