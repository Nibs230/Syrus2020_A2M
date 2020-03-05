package com.anish.syrus2020;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class Frag2 extends Fragment {

    private RecyclerView casedetails;
    private DatabaseReference posts;

    public Frag2() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frag2, container, false);
        final Context ct = getActivity();
        casedetails = view.findViewById(R.id.case_details);
        casedetails.setHasFixedSize(true);
        casedetails.setLayoutManager(new LinearLayoutManager(getActivity()));
        posts = FirebaseDatabase.getInstance().getReference().child("Hospital-Cases"); /// doctor id thrpough mauth
        FirebaseRecyclerAdapter<Cases,PhotoViewHolder> fr = new FirebaseRecyclerAdapter<Cases, PhotoViewHolder>(
                Cases.class,
                R.layout.card,
                PhotoViewHolder.class,
                posts
        ) {
            @Override
            protected void populateViewHolder(final PhotoViewHolder photoViewHolder, Cases s, int i) {
                final String list_user_id = getRef(i).getKey();
                posts.child(list_user_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String case_assigned,mobile,signature_link;
                        String type = dataSnapshot.child("type").getValue().toString();
                        String case_details = dataSnapshot.child("case_details").getValue().toString();
                        case_assigned =dataSnapshot.child("case_assigned").getValue().toString();
                        mobile = dataSnapshot.child("mobile").getValue().toString();
                        signature_link = dataSnapshot.child("signature_link").getValue().toString();

                        photoViewHolder.setCase(type,case_details,case_assigned,mobile,signature_link);
                        photoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                photoViewHolder.prescript(ct,posts,list_user_id);
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        };
        casedetails.setAdapter(fr);

        return view;
    }
    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        View mView;
        CircleImageView user_icon;
        TextView noti_type, case_ass, mobile, noti_desc;
        RelativeLayout container;


        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            user_icon = (CircleImageView) mView.findViewById(R.id.user_icon);
            noti_type = (TextView) mView.findViewById(R.id.case_type);
            case_ass = (TextView) mView.findViewById(R.id.case_assigned);
            noti_desc = (TextView) mView.findViewById(R.id.case_details);
            mobile = (TextView) mView.findViewById(R.id.mobile);

        }


        public void setCase(String type, String case_details, String case_assigned, String mf, String signature_link) {
            noti_type.setText(type);
            noti_desc.setText(case_details);
            case_ass.setText(case_assigned);
            mobile.setText(mf);
            Picasso.get().load(signature_link).placeholder(R.drawable.background_gradient).into(user_icon);
        }

        public void prescript(final Context ct, final DatabaseReference posts, final String list_user_id) {
            AlertDialog.Builder alert = new AlertDialog.Builder(ct);

            alert.setTitle("Title");
            alert.setMessage("Message");

// Set an EditText view to get user input
            final EditText input = new EditText(ct);
            alert.setView(input);

            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String value = input.getText().toString();
                    // Do something with value!
                    posts.child(list_user_id).child("prescription").setValue(value);
                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Canceled.
                }
            });

            alert.show();
        }
    }

}
