package com.anish.syrus2020;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class Frag5 extends Fragment {
    private DatabaseReference DoctorDb;
    private ImageView Doc_image;
    private RatingBar akk;
    private TextView Doc_name,Doc_type,Doc_ratenum,Doc_years,Doc_about;
    private Button book_btn;
    private String img,name,type,ratenum,years,about;

    public Frag5() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag5, container, false);
        final String docId = FirebaseAuth.getInstance().getUid();
        Doc_image = (ImageView) view.findViewById(R.id.doc_img);
        Doc_name = (TextView) view.findViewById(R.id.doc_name);
        Doc_type = (TextView) view.findViewById(R.id.doc_type);
        Doc_ratenum = (TextView) view.findViewById(R.id.doc_ratenum);
        Doc_years = (TextView) view.findViewById(R.id.doc_years);
        Doc_about = (TextView) view.findViewById(R.id.doc_about);
        akk = (RatingBar) view.findViewById(R.id.doc_rating);
        book_btn = (Button) view.findViewById(R.id.booking);
        DoctorDb = FirebaseDatabase.getInstance().getReference().child("Doctors").child(docId);
        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditProfile.class);
                intent.putExtra("docid",docId);
                startActivity(intent);
            }
        });

        DoctorDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    img = dataSnapshot.child("image").getValue().toString();
                    Picasso.get().load(img).into(Doc_image);

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
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

}
