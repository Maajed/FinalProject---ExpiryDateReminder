package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedCategory extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 123;
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
                String strMessage = "";
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    myList.clear();
                    for (DataSnapshot valueSnapshot: itemSnapshot.getChildren()){
                        myList.add(valueSnapshot.getValue(String.class));
                    }
                    DataClass dataClass = new DataClass(myList.get(1), myList.get(3), myList.get(0), myList.get(2), myList.get(4));
                    finalList.add(dataClass);
                    long daysDifference = daysCalculator(dataClass);

                    if(daysDifference <= 5 && daysDifference >=0){
                        strMessage += " " + dataClass.getDataProduct() + " expires in " + String.valueOf(daysDifference) + " days "+" \n";
                        Log.d("Reminder", "Expired food for " + dataClass.getDataProduct());

                    }
//
                }
                notification(strMessage);
                recyclerView.setHasFixedSize(true);

                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);

                MedAdapter medAdapter = new MedAdapter(finalList, getApplicationContext());

                recyclerView.setAdapter(medAdapter);
//                DataClass dataClass = new DataClass(myList.get(1), myList.get(3), myList.get(0), myList.get(2), myList.get(4));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.d("Medicine", "finalList: " + finalList.toString());


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




    private void notification(String message) {
        // Define notification channel ID
        String CHANNEL_ID = "CHANNEL_ID";

        // Check if notification permission is granted
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // If permission is granted, proceed with creating the notification
            try {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Expired goods reminder")
                        .setContentText("You have some expired goods: \n")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(message))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                // Check for Android version and create notification channel if necessary
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = notificationManager.getNotificationChannel(CHANNEL_ID);
                    if (notificationChannel == null) {
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        notificationChannel = new NotificationChannel(CHANNEL_ID, "NO", importance);
                        notificationChannel.setLightColor(Color.GREEN);
                        notificationChannel.enableVibration(true);
                        notificationManager.createNotificationChannel(notificationChannel);
                    }
                }

                // Display the notification
                notificationManager.notify(0, builder.build());
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            // If permission is not granted, request it from the user
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, PERMISSION_REQUEST_CODE);
        }
    }

    // Override onRequestPermissionsResult to handle the result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // If permission is granted, proceed with creating the notification
                notification("permission test");
            } else {
                // If permission is denied, handle it accordingly (e.g., display a message to the user)
                Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private long daysCalculator(DataClass dataClass) {
        // Sample dates
        int[] dateValues = new int[3];
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(dataClass.getExpiryDate());
            if (date != null) {
                dateValues[0] = Integer.parseInt(new SimpleDateFormat("dd").format(date));
                dateValues[1] = Integer.parseInt(new SimpleDateFormat("MM").format(date));
                dateValues[2] = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
            } else {
                // Handle the case when parsing fails
                Log.e("MedCategory", "Failed to parse date");
                return -1; // or any suitable default value
            }
        } catch (ParseException e) {
            // Handle the parsing exception
            Log.e("MedCategory", "ParseException: " + e.getMessage());
            return -1; // or any suitable default value
        }

        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }
        LocalDate alarmDate = null; // Change this to your desired alarm date
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            alarmDate = LocalDate.of(dateValues[2], dateValues[1], dateValues[0]);
        }

        // Calculate the difference in days
        long daysUntilAlarm = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            daysUntilAlarm = ChronoUnit.DAYS.between(currentDate, alarmDate);
        }

        // Output the difference
        return daysUntilAlarm;
    }

}