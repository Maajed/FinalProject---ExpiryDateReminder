package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    Button DrinkBtn,FoodBtn,MedBtn;

    BottomNavigationView nav_view;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        DrinkBtn = findViewById(R.id.DrinkBtn);
        FoodBtn = findViewById(R.id.FoodBtn);
        MedBtn = findViewById(R.id.MedBtn);

        nav_view = findViewById(R.id.nav_view);


        FoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,FoodCategory.class);
                startActivity(intent);
            }
        });


        MedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,MedCategory.class);
                startActivity(intent);

            }
        });


        DrinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,DrinkCategory.class);
                startActivity(intent);
            }
        });

        nav_view.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                if(item.getItemId()== R.id.home_btn){
                    intent = new Intent(Home.this,Home.class);
                }
                else if(item.getItemId()== R.id.profile_btn){
                    intent = new Intent(Home.this,Profile.class);
                }
                else if(item.getItemId()== R.id.logout_btn){
                    mAuth.signOut();
                    signOutUser();
                }

                if(intent!=null)
                {
                    startActivity(intent);
                    return true;
                }
                return false;
            }

            private void signOutUser() {
                Intent MainActivity = new Intent(Home.this,MainActivity.class);
                MainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(MainActivity);
                finish();

            }

        });






    }
}