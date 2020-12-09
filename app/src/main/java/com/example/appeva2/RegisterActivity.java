package com.example.appeva2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appeva2.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText txt_name, txt_email, txt_pass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txt_name = findViewById(R.id.register_name);
        txt_email = findViewById(R.id.register_email);
        txt_pass = findViewById(R.id.register_pass);
        auth = FirebaseAuth.getInstance();
    }

    public void CreateAccount(View view) {
        final String name, email, pass;
        name = txt_name.getText().toString();
        email = txt_email.getText().toString();
        pass = txt_pass.getText().toString();

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Complete la Informacion!", Toast.LENGTH_SHORT).show();
        } else {
            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("usuarios");
                                Usuario user = new Usuario();
                                user.setName(name);
                                user.setEmail(email);
                                user.setUid(task.getResult().getUser().getUid());
                                myRef.push().setValue(user);
                                Toast.makeText(RegisterActivity.this, "Cuenta Creada!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                String msg = task.getException().getMessage();
                                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

        }

    }
}