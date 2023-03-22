package com.example.test_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView loginTextView = findViewById(R.id.login_now);

         loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(RegisterActivity.this,
                        R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(intent, options.toBundle());
            }
        });
    }
}
