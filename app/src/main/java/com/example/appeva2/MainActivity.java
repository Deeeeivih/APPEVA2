package com.example.appeva2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.example.appeva2.ui.main.SectionsPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
        auth = FirebaseAuth.getInstance();
        title = findViewById(R.id.title);
        title.setText(auth.getCurrentUser().getEmail());

    }

    public void boton(View view) {



        auth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();



        
    }
    //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
    //.setAction("Action", null).show();
    // FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = database.getReference("tareas");

    //Reclamos reclamo = new Reclamos();
    ///reclamo.setTitle("Reclamo #1");
    //reclamo.setDirection("257338");
    ///reclamo.setEstado("En Espera");
    // myRef.push().setValue(reclamo);
}