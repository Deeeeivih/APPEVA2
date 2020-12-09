package com.example.appeva2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appeva2.model.Usuario;
import com.example.appeva2.ui.main.AcercaDeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText txt_email, txt_pass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null){
            setContentView(R.layout.activity_login);
            txt_email = findViewById(R.id.txtemail);
            txt_pass = findViewById(R.id.txtpass);
        }else{
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void LoginRegister(View view) {
        final String email, pass;
        email = txt_email.getText().toString();
        pass = txt_pass.getText().toString();

        if ( email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Complete la Informacion!", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                String msg = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


        }


    }


    public void Register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void AcercaDe(View view) {
        Intent intent = new Intent(LoginActivity.this, AcercaDeActivity.class);
        startActivity(intent);
    }
}