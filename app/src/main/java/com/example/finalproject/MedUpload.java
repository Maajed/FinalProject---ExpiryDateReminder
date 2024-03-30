package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MedUpload extends AppCompatActivity {

    ImageView uploadImage;
    Button saveButton;

    String imageURL;

    Uri uri;
    EditText uploadProductId,uploadProductName,uploadDesc,uploadLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_upload);

        uploadImage = findViewById(R.id.uploadImage);
        uploadProductId = findViewById(R.id.uploadProductId);
        uploadProductName = findViewById(R.id.uploadProductName);
        uploadDesc = findViewById(R.id.uploadDesc);
        uploadLang = findViewById(R.id.uploadLang);
        saveButton = findViewById(R.id.saveButton);

//        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if(result.getResultCode() ==Activity.RESULT_OK){
//                            Intent data = result.getData();
//                            uri = data.getData();
//                            uploadImage.setImageURI(uri);
//
//                        } else {
//                            Toast.makeText(FoodUpload.this,"No Image Selected", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                }
//        );
//
//        uploadImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent photoPicker = new Intent(Intent.ACTION_PICK);
//                photoPicker.setType("image/*");
//                activityResultLauncher.launch(photoPicker);
//            }
//        });
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveData();
//            }
//        });
//
//        public void saveData(){
//
//
//        }
    }
}