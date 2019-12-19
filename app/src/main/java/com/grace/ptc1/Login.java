package com.grace.ptc1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //Initialise Firebase Auth
        auth = FirebaseAuth.getInstance();

        //Check whether user is logged in
        if (auth.getCurrentUser() != null){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);

        final EditText inputEmail = findViewById(R.id.email);
        final EditText inputPassword = findViewById(R.id.password);
        TextView Forgotpassword = findViewById(R.id.forgot_password);
        Button SignIn = findViewById(R.id.sign_In);

        Forgotpassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Reset_Password.class));
            }
        });

        SignIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = inputEmail.getText().toString();
                final String Password  =inputPassword.getText().toString();

                if (TextUtils.isEmpty(Email)){
                    Toast.makeText(getApplicationContext(), "Enter E-mail address ", Toast.LENGTH_SHORT).show();
                }if (TextUtils.isEmpty(Password)){
                    Toast.makeText(getApplicationContext(), "Enter Password ", Toast.LENGTH_SHORT).show();
                }

                auth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            if (Password.length()<6){
                                inputPassword.setError(getString(R.string.minimum_password));
                            }else {
                                Toast.makeText(Login.this, getString(R.string.Auth_failed), Toast.LENGTH_LONG).show();
                            }
                        }else {
                                Intent intent = new Intent( Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                    }
                });
            }
        });


        TextView signup_text = findViewById(R.id.signup_text);
        signup_text.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
                finish();
            }
        });
    }

}
