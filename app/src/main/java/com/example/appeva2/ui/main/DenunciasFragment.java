package com.example.appeva2.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appeva2.R;
import com.example.appeva2.model.AdapterDenuncia;
import com.example.appeva2.model.Denuncia;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DenunciasFragment extends Fragment {

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Denuncia> list;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_denuncias, container, false);
        recyclerView = view.findViewById(R.id.recycler_denuncias);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        String uid = auth.getCurrentUser().getUid();
        myRef = database.getReference("denuncias");
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
                        for (DataSnapshot otherdn : dn.getChildren()){
                            Denuncia denuncia = otherdn.getValue(Denuncia.class);
                            denuncia.setId(otherdn.getKey());
                            list.add(denuncia);
                        }

                    }
                    LinearLayoutManager lm = new LinearLayoutManager(getActivity());
                    lm.setOrientation(recyclerView.VERTICAL);

                    AdapterDenuncia adapterDenuncia = new AdapterDenuncia(getActivity(),R.layout.item_denuncias,list);
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