package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {



    TextInputLayout profile_fullname,profile_username,profile_phone;

    TextView title_fullname, title_username;
    String _USERNAME,_NAME,_EMAIL,_PHONE,_PASSWORD;

    Button UpdateBtn;

    DatabaseReference reference;

    DatabaseReference databaseReference;
    FirebaseDatabase database;

    BottomNavigationView nav_view;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        title_fullname = findViewById(R.id.title_fullname);
        title_username = findViewById(R.id.title_username);


        profile_fullname = findViewById(R.id.profile_fullname);
        profile_phone = findViewById(R.id.profile_phone);
        profile_username = findViewById(R.id.profile_username);
        UpdateBtn = findViewById(R.id.Update_Btn);

        nav_view = findViewById(R.id.nav_view);

        reference = FirebaseDatabase.getInstance().getReference("users");
        database = FirebaseDatabase.getInstance();


        mAuth = FirebaseAuth.getInstance();
        String currentUser = mAuth.getCurrentUser().getUid();



        String statement = "/users/" + currentUser;
        databaseReference = database.getReference(statement);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//
//
//
//                }

                UserHelperClass userHelperClass = snapshot.getValue(UserHelperClass.class);
                title_fullname.setText(userHelperClass.getName());
                title_username.setText(userHelperClass.getUsername());
                profile_fullname.getEditText().setText(userHelperClass.getName());
                profile_username.getEditText().setText(userHelperClass.getUsername());
                profile_phone.getEditText().setText(userHelperClass.getPhoneNo());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        




        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"user created", Toast.LENGTH_SHORT).show();
                UserHelperClass helperClass = new UserHelperClass(profile_fullname.getEditText().getText().toString(), profile_username.getEditText().getText().toString(), currentUser, profile_phone.getEditText().getText().toString());


                reference.child(currentUser).setValue(helperClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Registration successful, show toast message
                                Toast.makeText(Profile.this, "Update successful", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Registration failed, show error message if necessary
                                Toast.makeText(Profile.this, "Update failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
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

    private void showUserData() {
        Intent intent = getIntent();
      _USERNAME = intent.getStringExtra("username");
      _NAME = intent.getStringExtra("name");
      _EMAIL = intent.getStringExtra("email");
      _PASSWORD = intent.getStringExtra("password");
      _PHONE = intent.getStringExtra("phoneNo");


//      title_fullname.setText(_NAME);
//      title_username.setText(_USERNAME);
//      profile_fullname.getEditText().setText(_NAME);
//      profile_email.getEditText().setText(_EMAIL);
//      profile_phone.getEditText().setText(_PHONE);
//      profile_password.getEditText().setText(_PASSWORD);
    }









}

