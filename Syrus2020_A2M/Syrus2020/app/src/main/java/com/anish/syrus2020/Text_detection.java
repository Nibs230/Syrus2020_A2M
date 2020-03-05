package com.anish.syrus2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import java.util.List;

public class Text_detection extends AppCompatActivity {


    private Button capture_image;
    private Button detect_text_image;
    private ImageView image_view;
    private TextView text_display;

    private int x=0;

    private Bitmap imageBitmap;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_detection);

        capture_image=findViewById(R.id.capture_image);
        detect_text_image=findViewById(R.id.detect_text_image);
        image_view=findViewById(R.id.image_view);
        text_display=findViewById(R.id.text_display);


        capture_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                text_display.setText("");
            }
        });


        detect_text_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x==1)

                {
                    detectTextFromImage();
                }
                else
                {
                    Toast.makeText(Text_detection.this, "Select a number plate ", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            image_view.setImageBitmap(imageBitmap);
            x=1;
        }
    }

    private void detectTextFromImage()
    {
        FirebaseVisionImage firebaseVisionImage= FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextDetector firebaseVisionTextDetector= FirebaseVision.getInstance().getVisionTextDetector();
        firebaseVisionTextDetector.detectInImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {

                displayTextFromImage(firebaseVisionText);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Text_detection.this,"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();

                Log.d("Error: ",e.getMessage());

            }
        });
    }



    private void displayTextFromImage(FirebaseVisionText firebaseVisionText)
    {
        String full_text = null;
        List<FirebaseVisionText.Block> blockList=firebaseVisionText.getBlocks();
        if (blockList.size()==0)
        {
            Toast.makeText(this, "No text found in image", Toast.LENGTH_SHORT).show();
        }
        else {
            for (FirebaseVisionText.Block block:firebaseVisionText.getBlocks())
            {
                String text=block.getText();

                text = text.replaceAll("\\s+","");
                text_display.setText(text);
                Intent intent = new Intent(Text_detection.this,MedicineOrderActivity.class);
                intent.putExtra("order",text);
                startActivity(intent);
                //  full_text = full_text + text;
            }


        }
    }




}
