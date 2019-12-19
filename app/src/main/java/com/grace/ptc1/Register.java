package com.grace.ptc1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initialization
        final TextView getname = findViewById(R.id.Name);
        final TextView getemail = findViewById(R.id.Email);
        final TextView getpassword = findViewById(R.id.Password);
        Button signUp = findViewById(R.id.Sign_up);


        //Get Firebase user
        auth = FirebaseAuth.getInstance();

        //Signing Up
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getname.getText().toString().trim();
                String email = getemail.getText().toString().trim();
                String password = getpassword.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(), "Enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter your E-mail", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter your Password", Toast.LENGTH_SHORT).show();
                    return;
                }if (password.length() < 6){
                    Toast.makeText(getApplicationContext(), R.string.minimum_password, Toast.LENGTH_SHORT).show();
                }

                //Create User
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Register.this, "Create User with Email:onComplete:" + task.isSuccessful(),Toast.LENGTH_SHORT).show();

                        if (!task.isSuccessful()){
                            Toast.makeText(Register.this, getString(R.string.Auth_failed) +task.getException(), Toast.LENGTH_SHORT).show();
                        }else{
                            startActivity(new Intent(Register.this, MainActivity.class));
                            finish();
                        }
                    }
                });
            }


        });

        TextView signIn_text  = findViewById(R.id.signIn_text);
        signIn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
                 finish();
            }
        });
    }
}
