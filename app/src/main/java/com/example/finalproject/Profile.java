package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    BottomNavigationView nav_view;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nav_view = findViewById(R.id.nav_view);


        nav_view.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                if(item.getItemId()== R.id.home_btn){
                    intent = new Intent(Profile.this,Home.class);
                }
                else if(item.getItemId()== R.id.profile_btn){
                    intent = new Intent(Profile.this,Profile.class);
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
                Intent MainActivity = new Intent(Profile.this,MainActivity.class);
                MainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(MainActivity);
                finish();

            }

        });

    }
}