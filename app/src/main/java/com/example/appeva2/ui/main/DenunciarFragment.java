package com.example.appeva2.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appeva2.R;
import com.example.appeva2.model.Denuncia;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DenunciarFragment extends Fragment {

    EditText txt_title, txt_direction;
    Button button;
    FirebaseAuth auth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_denunciar, container, false);
        txt_title = view.findViewById(R.id.txt_denuncia_title);
        txt_direction = view.findViewById(R.id.txt_denuncia_direction);
        button = view.findViewById(R.id.btn_denuncia);
        auth = FirebaseAuth.getInstance();
        CreateDenuncia();
        return view;




    }

    private void CreateDenuncia() {
        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String title = txt_title.getText().toString();
            String direction = txt_direction.getText().toString();
            String uid = auth.getCurrentUser().getUid();

            if (title.isEmpty() || direction.isEmpty()){
                Snackbar.make(view, "Faltan Datos!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }else{
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("denuncias").child(uid);

                Denuncia denuncia = new Denuncia();
                denuncia.setTitle(title);
                denuncia.setDirection(direction);
                myRef.push().setValue(denuncia);
                txt_title.setText("");
                txt_direction.setText("");

                Snackbar.make(view, "Denuncia creada con exito!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }
    });
    }
}