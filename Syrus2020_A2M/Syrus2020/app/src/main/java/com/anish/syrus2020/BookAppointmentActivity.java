package com.anish.syrus2020;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jeevandeshmukh.glidetoastlib.GlideToast;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class BookAppointmentActivity extends AppCompatActivity {


    private EditText input_type,input_aadhar,input_hospital,input_email;
    private EditText input_details, input_relation;
    private DatabaseReference CaseDatabase;
    private ImageView attachment1,attachment2,attachment3;
    private CheckBox terms;
    private ProgressDialog mProgress;
    private ProgressDialog mProgressDialog;
    private Button savebutton;
    private Button btn1,btn2;
    private TextView trial,input_mobile;
    private String link1=null,link2=null,link3=null,mCurrent_user_id,email;
    private static final int GALLERY_PICK = 1;
    private int choice = 1;
    private StorageReference mImageStorage;
    private TextView esign;
    private String esign_link = null;
    private TextView trying;

    private ImageView greentick;

    private TextView trying2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

       /* email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        int index = email.indexOf('@');
        email = email.substring(0,index);

*/
       email = "7303263620";
        input_type = (EditText) findViewById(R.id.input_type); //type

        input_hospital = (EditText) findViewById(R.id.input_hospital);  //hospital
        input_details = (EditText) findViewById(R.id.input_details);  //brief about appointment
        input_relation = (EditText) findViewById(R.id.input_relation);  //days since suffer


        CaseDatabase = FirebaseDatabase.getInstance().getReference().child("Hospital-Cases");
        terms = (CheckBox) findViewById(R.id.terms);
        savebutton = (Button) findViewById(R.id.submitbtn);
        attachment1 = (ImageView) findViewById(R.id.input_attach1);
        attachment2 = (ImageView) findViewById(R.id.input_attach2);
        attachment3 = (ImageView) findViewById(R.id.input_attach3);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        esign = (TextView) findViewById(R.id.esign);
        trying = (TextView) findViewById(R.id.text1);
        mImageStorage = FirebaseStorage.getInstance().getReference();
        esign_link = getIntent().getStringExtra("sign_link");
        greentick = (ImageView) findViewById(R.id.green_tick);
        trying2 = (TextView) findViewById(R.id.text2);


        if(esign_link!=null)
        {
            greentick.setVisibility(View.VISIBLE);
        }

        esign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookAppointmentActivity.this,SignatureActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setTextColor(Color.parseColor("#35ba1a"));
                int color = Color.parseColor("#ffffff");
                btn1.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC));
                btn2.setTextColor(Color.parseColor("#ffffff"));
                int color2 = Color.parseColor("#35ba1a");
                btn2.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(color2, PorterDuff.Mode.SRC));

                // input_details.setHint(Html.fromHtml(getString(R.string.hint_details)));

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2.setTextColor(Color.parseColor("#35ba1a"));
                int color = Color.parseColor("#ffffff");
                btn2.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC));
                btn1.setTextColor(Color.parseColor("#ffffff"));
                int color2 = Color.parseColor("#35ba1a");
                btn1.getBackground().mutate().setColorFilter(new PorterDuffColorFilter(color2, PorterDuff.Mode.SRC));


            }
        });
////////////////////////////////////////// TO ADD ALL THE ATTACHMENTS   /////////////////////////////////
        attachment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                choice = 1;
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"), GALLERY_PICK);

            }
        });

        attachment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                choice = 2;
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"), GALLERY_PICK);

            }
        });


        attachment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                choice = 3;
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"), GALLERY_PICK);

            }
        });





////////////////////////////////////////////////////////////////////////////////////////////////////////
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input1 = input_type.getText().toString();
                String input3 = input_hospital.getText().toString();
                String input6 = input_details.getText().toString();
                String input7 = input_relation.getText().toString();

                HashMap<String, Object> case_data = new HashMap<>();
                case_data.put("type",input1);
              //  case_data.put("aadhar_number",input2);
                case_data.put("hospital_code",input3);
                case_data.put("mobile",email);
              //  case_data.put("email",input5);
                case_data.put("case_details",input6);
                case_data.put("days_since",input7);
                case_data.put("case_assigned","no");
                case_data.put("attach1",link1);
                case_data.put("attach2",link2);
                case_data.put("attach3",link3);
                case_data.put("signature_link",esign_link);
                // case_data.put("attachments",)

                if(input1.equals("") ||  input3.equals("")
                        || input6.equals("") || input7.equals("") || !terms.isChecked() || esign_link.equals(null))
                {
                    Toast.makeText(BookAppointmentActivity.this, "Enter all the required information",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    mProgress = new ProgressDialog(BookAppointmentActivity.this);
                    mProgress.setTitle("Reporting Your Case");
                    mProgress.setMessage("Please wait while we are reporting your case");
                    mProgress.show();

                    final String key = CaseDatabase.push().getKey();

                    CaseDatabase.child(key).updateChildren(case_data)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        mProgress.dismiss();
                                        Toast.makeText(BookAppointmentActivity.this,"Case Reported Successfully",Toast.LENGTH_SHORT).show();
                                        // new GlideToast.makeToast(firRegistration.this,"Case Successfully Reported",GlideToast.LENGTHLONG,GlideToast.SUCCESSTOAST,GlideToast.BOTTOM).show();
                                        Intent intent = new Intent(BookAppointmentActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        //Intent mainIntent = new Intent(firRegistration.this,MainActivity.class);
                                        //mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        //startActivity(mainIntent);
                                        //Toast.makeText(MainActivity.this,"Case Successfully Reported",Toast.LENGTH_SHORT).show();

                                    }
                                    else
                                    {
                                        mProgress.dismiss();
                                        new GlideToast.makeToast(BookAppointmentActivity.this,"Network Error",GlideToast.LENGTHLONG,GlideToast.FAILTOAST,GlideToast.BOTTOM).show();
                                        //Toast.makeText(MainActivity.this,"Network error",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });







    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK) {

            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    // .setAspectRatio(1, 1)
                    .start(this);

            //Toast.makeText(SettingsActivity.this, imageUri, Toast.LENGTH_SHORT).show();
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mProgressDialog = new ProgressDialog(BookAppointmentActivity.this);
                mProgressDialog.setTitle("Uploading Image..");
                mProgressDialog.setMessage("Please wait while we upload and process the image");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();

                Uri resultUri = result.getUri();

                //  final String current_user_id = mCurrentUser.getUid();
                final Date currentTime = Calendar.getInstance().getTime();

                StorageReference filepath = mImageStorage.child("attachments").child(input_mobile.getText().toString()+"_"+ currentTime+ ".jpg");

                filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if(task.isSuccessful()){
                            //Toast.makeText(SettingsActivity.this, "Working", Toast.LENGTH_SHORT).show();

                            //final String download_url= task.getResult().toString();

                            /////////////////

                            mImageStorage.child("attachments").child(input_mobile.getText().toString()+"_"+currentTime+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    if(choice == 1)
                                    {
                                        mProgressDialog.dismiss();
                                        link1 = uri.toString();
                                        Picasso.with(BookAppointmentActivity.this).load(link1).into(attachment1);
                                    }
                                    if(choice == 2)
                                    {
                                        mProgressDialog.dismiss();
                                        link2 = uri.toString();
                                        Picasso.with(BookAppointmentActivity.this).load(link2).into(attachment2);
                                    }
                                    if(choice == 3)
                                    {
                                        mProgressDialog.dismiss();
                                        link3 = uri.toString();
                                        Picasso.with(BookAppointmentActivity.this).load(link3).into(attachment3);
                                    }


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                }
                            });
                            //////////////////



                        }else{

                            Toast.makeText(BookAppointmentActivity.this, "Error in uploading", Toast.LENGTH_SHORT).show();
                            mProgressDialog.dismiss();
                        }
                    }
                });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }



}
