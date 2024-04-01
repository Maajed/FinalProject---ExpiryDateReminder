package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MedCategory extends AppCompatActivity {

    FloatingActionButton fab;

    BottomNavigationView nav_view;
    FirebaseAuth mAuth;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    List<String> myList = new ArrayList<>();
    List<DataClass> finalList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_category);
        nav_view = findViewById(R.id.nav_view);

        mAuth = FirebaseAuth.getInstance();

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);

        database = FirebaseDatabase.getInstance();

        String currentUser = mAuth.getCurrentUser().getUid();
        String statement = "Categories/Medicine/" + currentUser;
        databaseReference = database.getReference(statement);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                finalList.clear();
                int count = 0;
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    myList.clear();
                    for (DataSnapshot valueSnapshot: itemSnapshot.getChildren()){
                        myList.add(valueSnapshot.getValue(String.class));
                    }
                    DataClass dataClass = new DataClass(myList.get(1), myList.get(3), myList.get(0), myList.get(2), myList.get(4));
                    finalList.add(dataClass);
//                    Toast.makeText(FoodCategory.this, "Worked", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(FoodCategory.this, itemSnapshot.getValue(String.class), Toast.LENGTH_SHORT).show();
//                    Log.e("Naim", "itemSnapShot: " + .getValue(String.class));
                    Log.d("Naim", "listValue: " + myList.get(count));
                    count++;
                }
                recyclerView.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);

                MedAdapter medadapter = new MedAdapter(finalList, getApplicationContext());

                recyclerView.setAdapter(medadapter);
//                DataClass dataClass = new DataClass(myList.get(1), myList.get(3), myList.get(0), myList.get(2), myList.get(4));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.d("Naim", "finalList: " + finalList.toString());


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedCategory.this,MedUpload.class);
                startActivity(intent);
            }
        });

        nav_view.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = null;
                if(item.getItemId()== R.id.home_btn){
                    intent = new Intent(MedCategory.this,Home.class);
                }
                else if(item.getItemId()== R.id.profile_btn){
                        intent = new Intent(MedCategory.this,Profile.class);
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
                Intent MainActivity = new Intent(MedCategory.this,MainActivity.class);
                MainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(MainActivity);
                finish();

            }

        });

    }
}