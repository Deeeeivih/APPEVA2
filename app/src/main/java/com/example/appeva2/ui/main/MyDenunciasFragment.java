package com.example.appeva2.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appeva2.LoginActivity;
import com.example.appeva2.R;
import com.example.appeva2.model.AdapterDenuncia;
import com.example.appeva2.model.Denuncia;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyDenunciasFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Denuncia> list;
    RecyclerView recyclerView;
    TextView button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_mydenuncias, container, false);

        recyclerView = view.findViewById(R.id.recycler_mis_denuncias);
        button = view.findViewById(R.id.item_delete);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        String uid = auth.getCurrentUser().getUid();
        myRef = database.getReference("denuncias").child(uid);
        list = new ArrayList<>();
        LoadDenuncias();

        return view;
    }



    private void LoadDenuncias() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    list.clear();
                    for (DataSnapshot dn : dataSnapshot.getChildren()){
                        Denuncia denuncia = dn.getValue(Denuncia.class);
                        denuncia.setId(dn.getKey());
                        list.add(denuncia);
                    }
                    LinearLayoutManager lm = new LinearLayoutManager(getActivity());
                    lm.setOrientation(recyclerView.VERTICAL);

                    AdapterDenuncia adapterDenuncia = new AdapterDenuncia(getActivity(),R.layout.item_mydenuncias,list);
                    recyclerView.setLayoutManager(lm);
                    recyclerView.setAdapter(adapterDenuncia);

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
}