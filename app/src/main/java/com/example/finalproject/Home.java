package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button DrinkBtn,FoodBtn,MedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DrinkBtn = findViewById(R.id.DrinkBtn);
        FoodBtn = findViewById(R.id.FoodBtn);
        MedBtn = findViewById(R.id.MedBtn);


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



    }
}