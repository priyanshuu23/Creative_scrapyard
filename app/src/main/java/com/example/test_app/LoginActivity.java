package com.example.test_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Step 1: Create an instance of TextView that you want to click on to redirect to the RegisterActivity.
        TextView registerTextView = findViewById(R.id.register_now);

        // Step 2: Set an onClickListener on the TextView to create an Intent to open the RegisterActivity.
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(LoginActivity.this,
                        R.anim.slide_in_left, R.anim.slide_out_right);
                startActivity(intent, options.toBundle());
            }
        });
    }
}
