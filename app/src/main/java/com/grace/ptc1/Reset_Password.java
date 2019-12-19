package com.grace.ptc1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset_Password extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset__password);

        auth = FirebaseAuth.getInstance();

        final TextView inputEmail = findViewById(R.id.Email);
        Button reset = findViewById(R.id.Reset);
        Button back = findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Reset_Password.this, "Enter E-mail ", Toast.LENGTH_SHORT).show();
                }

                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Reset_Password.this, "We have sent you instructions to reset your password to your email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Reset_Password.this, "Failed to send reset email", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }

}


