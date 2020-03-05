package com.anish.syrus2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DoctorViewProfileActivity extends AppCompatActivity {

    private DatabaseReference DoctorDb;
    private ImageView Doc_image,Doc_call;
    private RatingBar akk;
    private TextView Doc_name,Doc_type,Doc_ratenum,Doc_years,Doc_about;
    private Button book_btn;

    //////////

    private String img,name,type,ratenum,years,about,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_profile);

        final String docId = getIntent().getStringExtra("id");
        Toast.makeText(getApplicationContext(),docId,Toast.LENGTH_SHORT).show();

        Doc_image = (ImageView) findViewById(R.id.doc_img);
        Doc_name = (TextView) findViewById(R.id.doc_name);
        Doc_type = (TextView) findViewById(R.id.doc_type);
        Doc_ratenum = (TextView) findViewById(R.id.doc_ratenum);
        Doc_years = (TextView) findViewById(R.id.doc_years);
        Doc_about = (TextView) findViewById(R.id.doc_about);
        akk = (RatingBar) findViewById(R.id.doc_rating);
        book_btn = (Button) findViewById(R.id.booking);
        Doc_call = (ImageView) findViewById(R.id.doc_call);

        DoctorDb = FirebaseDatabase.getInstance().getReference().child("Doctors").child(docId);
        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorViewProfileActivity.this,BookAppointmentActivity.class);
                intent.putExtra("docid",docId);
                startActivity(intent);
            }
        });

        Doc_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String smn = "tel:"+phone;
                Intent intent = new Intent(Intent.ACTION_CALL);
                Log.d("CheckVer",""+phone);
                intent.setData(Uri.parse(smn));
                startActivity(intent);
            }
        });

        DoctorDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    img = dataSnapshot.child("image").getValue().toString();
                    Picasso.with(getApplicationContext()).load(img).into(Doc_image);

                    name = dataSnapshot.child("name").getValue().toString();
                    Doc_name.setText(name);

                    type = dataSnapshot.child("descp").getValue().toString();
                    Doc_type.setText(type);

                    ratenum = dataSnapshot.child("rating").getValue().toString();
                    Float abc = Float.parseFloat(ratenum);
                    akk.setRating(abc);

                    years = dataSnapshot.child("years").getValue().toString();
                    Doc_years.setText(years);

                    ratenum = dataSnapshot.child("number_of_rating").getValue().toString();
                    Doc_ratenum.setText(ratenum);

                    about = dataSnapshot.child("about").getValue().toString();
                    Doc_about.setText(about);

                    phone = dataSnapshot.child("phone").getValue().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
