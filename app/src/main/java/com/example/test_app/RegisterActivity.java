package com.example.test_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.xml.transform.ErrorListener;

public class RegisterActivity extends AppCompatActivity {

    EditText ipemail , ippassword , reg_name , reg_number;
    Button reg_button;
//    to validate the email
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";

    ProgressDialog ProgressDialog;

//    for firebase
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView loginTextView = findViewById(R.id.login_now);

//    for capture email and password and other details
    ipemail = findViewById(R.id.email);
    ippassword = findViewById(R.id.password);
    reg_button = findViewById(R.id.reg_button);
    ProgressDialog = new ProgressDialog(this);
//    for name and contact no
    reg_name = findViewById(R.id.reg_name);
    reg_number = findViewById(R.id.phone_no);

//    initialized variables for the inputs on line 15&16

//    firebase initialization
    mAuth = FirebaseAuth.getInstance();
    mUser = mAuth.getCurrentUser();


//            for redirect to login page

         loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(RegisterActivity.this,
                        R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(intent, options.toBundle());
            }
        });

//         clicklistner for button register
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performAuth();
//                activity to perform the authentication
            }

            private void performAuth() {
                String email =ipemail.getText().toString();
                String password =ippassword.getText().toString();
                String name =reg_name.getText().toString();
                String number = reg_number.getText().toString();

//            to check email correctly written or not
            if (!email.matches(emailPattern)){
                Toast.makeText(RegisterActivity.this, "enter the correct email address", Toast.LENGTH_SHORT).show();
            }
//            to check password is correct
            else if (password.isEmpty() || password.length()<4) {

                Toast.makeText(RegisterActivity.this, "enter password atleast 4 character long", Toast.LENGTH_SHORT).show();

            }
            else if (number.length()<10|number.length()>10){
                Toast.makeText(RegisterActivity.this, "enter correct mobile number", Toast.LENGTH_SHORT).show();
            }
//            for the progressbar
            ProgressDialog.setMessage("please wait.....");
            ProgressDialog.setTitle("registration");
            ProgressDialog.setCanceledOnTouchOutside(false);
            ProgressDialog.show();

//            for init to firebase
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
//                        to remove progressbar
                        ProgressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Registration is successful...", Toast.LENGTH_LONG).show();
//                    to send user to next activity
                    sendUserToNextActivity();
                    }
                    else {
                        ProgressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }

                private void sendUserToNextActivity() {
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            }
        });

    }
}
