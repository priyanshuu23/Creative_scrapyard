package com.example.test_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    EditText ipemail , ippassword ;
    Button login_button;
    //    to validate the email
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";

    android.app.ProgressDialog ProgressDialog;

    //    for firebase
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//      Create an instance of TextView that you want to click on to redirect to the RegisterActivity.
        TextView registerTextView = findViewById(R.id.register_now);

//          for capture email and password
        ipemail = findViewById(R.id.email);
        ippassword = findViewById(R.id.password);
        login_button = findViewById(R.id.login_button);

//        for progressbar
        ProgressDialog = new ProgressDialog(this);

//            firebase initialization
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

//         Check if user is already logged in
        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(LoginActivity.this, FullscreenActivity.class);
            startActivity(intent);
            finish();
        }



//       Set an onClickListener on the TextView to create an Intent to open the RegisterActivity.
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(LoginActivity.this,
                        R.anim.slide_in_left, R.anim.slide_out_right);
                startActivity(intent, options.toBundle());
            }
        });

//        for capture email and password and other details when pressed the button
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            performLogin();
            }


//for perfirming login activity
            private void performLogin() {

                String email =ipemail.getText().toString();
                String password =ippassword.getText().toString();

//            to check email correctly written or not
                if (!email.matches(emailPattern)){
                    Toast.makeText(LoginActivity.this, "enter the correct email address", Toast.LENGTH_SHORT).show();
                    return;
                }
//            to check password is correct
                else if (password.isEmpty() || password.length()<4) {

                    Toast.makeText(LoginActivity.this, "enter password atleast 4 character long", Toast.LENGTH_SHORT).show();
                    return;
                }

//            for the progressbar
                ProgressDialog.setMessage("please wait while we logging you in.....");
                ProgressDialog.setTitle("Login");
                ProgressDialog.setCanceledOnTouchOutside(false);
                ProgressDialog.show();

//                for authontication
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
//                            to remove progressbar
                            ProgressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Login is successful...", Toast.LENGTH_LONG).show();
                            sendUserToNextActivity();
                        }
                        else {
                            ProgressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "unable to log you in..."+task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
//                    send user to main page
                    private void sendUserToNextActivity() {
                        Intent intent = new Intent(LoginActivity.this,FullscreenActivity.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }


                });
            }
        });
    }
}
