package com.anish.syrus2020;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;



/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private RecyclerView news;
    private String email;
    private DatabaseReference mNews;
    private int stopPosition;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_news, container, false);
        news = view.findViewById(R.id.news);
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        int index = email.indexOf('@');
        email = email.substring(0,index);

        news.setHasFixedSize(true);
        news.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNews = FirebaseDatabase.getInstance().getReference().child("news");
        FirebaseRecyclerAdapter<news,newsHolder> adapter = new FirebaseRecyclerAdapter<news,newsHolder>(
                news.class,
                R.layout.news_user,
                newsHolder.class,
                mNews

        ) {

            @Override
            protected void populateViewHolder(final newsHolder newsHolder, news news, int i) {
                String list_news_id = getRef(i).getKey();
                mNews.child(list_news_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String Title = dataSnapshot.child("title").getValue().toString();
                        String content = dataSnapshot.child("content").getValue().toString();
                        String location = dataSnapshot.child("location").getValue().toString();
                        String post = dataSnapshot.child("post").getValue().toString();
                        String type = dataSnapshot.child("type").getValue().toString();
                        newsHolder.setTitle(Title);
                        newsHolder.setContent(content);
                        newsHolder.setLocation(location);
                        newsHolder.setPost(post,type,getContext());
                       /*newsHolder.mediaController.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (newsHolder.vw.isPlaying()){
                                    stopPosition = newsHolder.vw.getCurrentPosition(); //stopPosition is an int
                                  //  newsHolder.vw.pause();
                                }else{
                                    newsHolder.vw.seekTo(stopPosition);
                                   // newsHolder.vw.start();
                                }
                                return true;
                            }
                        }); */

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        };
        news.setAdapter(adapter);
        return view;
    }
    public static class newsHolder extends RecyclerView.ViewHolder{
        View mView;
        VideoView vw;
        int  stopPosition;
        MediaController mediaController;
        public newsHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            vw = mView.findViewById(R.id.vid);

        }




        public void setTitle(String title) {
            TextView tit = mView.findViewById(R.id.title);
            tit.setText(title);
        }

        public void setPost(String post, String type, Context context) {
            ImageView Iw = mView.findViewById(R.id.imag);
            if (type.equals("image")){
                vw.setVisibility(View.GONE);
                Iw.setVisibility(View.VISIBLE);


                Picasso.with(context).load(post).placeholder(R.drawable.default_avatar).into(Iw);


            }else {
                mediaController = new MediaController(context);

                vw.setVisibility(View.VISIBLE);
                Iw.setVisibility(View.GONE);
                vw.setMediaController(mediaController);
                mediaController.setAnchorView(vw);
                vw.setVideoPath(post);
                //vw.setBackgroundResource(R.drawable.playvideo);
                // mediaController.show(5000);



                // vw.requestFocus()
                vw.start();


            }

        }

        public void setLocation(String location) {
            TextView loca = mView.findViewById(R.id.location_text);
            loca.setText(location);
        }

        public void setContent(String content) {
            TextView cont = mView.findViewById(R.id.content);
            cont.setText(content);
        }

    }

}
