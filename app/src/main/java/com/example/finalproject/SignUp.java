package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout regName,regUsername,regEmaiL,regPhoneNo,regPassword;

    Button SignUp_Btn, redirectLogin;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks
        regName = findViewById(R.id.reg_Name);
        regUsername = findViewById(R.id.reg_Username);
        regEmaiL = findViewById(R.id.reg_Email);
        regPhoneNo = findViewById(R.id.reg_PhoneNo);
        regPassword = findViewById(R.id.reg_Password);
        SignUp_Btn = findViewById(R.id.SignUp_Btn);

        redirectLogin = findViewById(R.id.redirectLogin);


        SignUp_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmaiL.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();


                UserHelperClass helperClass = new UserHelperClass(name,username,email,phoneNo,password);


                reference.child(username).setValue(helperClass);
            }
        });


        redirectLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
    }
}