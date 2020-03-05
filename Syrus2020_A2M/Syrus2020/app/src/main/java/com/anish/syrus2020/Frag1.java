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

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class Frag1 extends Fragment {

    public Frag1() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag1, container, false);

        ImageView chatbot = (ImageView) view.findViewById(R.id.question_image);
        ImageView rate = (ImageView) view.findViewById(R.id.smiley_image);
        ImageView sos_button = (ImageView) view.findViewById(R.id.sos_image);
        Button apt_btn = (Button) view.findViewById(R.id.btn_1);
        Button online_medicine = (Button) view.findViewById(R.id.btn_4);
        Button health_insurance = (Button) view.findViewById(R.id.btn_3);
        Button video_Call = (Button) view.findViewById(R.id.btn_2);

        video_Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),RegistrationActivity.class);
                startActivity(intent);
            }
        });

        health_insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),LoadWebViewActivity.class);
                intent.putExtra("website-link","https://www.hdfclife.com/health-insurance-plans");
                startActivity(intent);
            }
        });

        online_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Text_detection.class);
                startActivity(intent);
            }
        });

        apt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),BookAppointmentActivity.class);
                startActivity(intent);
            }
        });


        sos_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String s = "tel:"+"8652206451";
//                Intent intent = new Intent(Intent.ACTION_CALL);
//                intent.setData(Uri.parse(s));
//                startActivity(intent);
                Intent intent=new Intent(getContext(),LocationFinder.class);
                startActivity(intent);
            }
        });

        chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChatbotActivity.class);
                startActivity(intent);
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                View view_bottom = getLayoutInflater().inflate(R.layout.activity_bottom_rate,null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                bottomSheetDialog.setContentView(view_bottom);
                BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view_bottom.getParent());
                mBehavior.setPeekHeight(400);
                final ImageView img1 = (ImageView) view_bottom.findViewById(R.id.sad);
                final ImageView img2 = (ImageView) view_bottom.findViewById(R.id.equal);
                final ImageView img3 = (ImageView) view_bottom.findViewById(R.id.smile);

                bottomSheetDialog.show();


                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        img1.setImageResource(R.drawable.mesadcolored);
                        img2.setImageResource(R.drawable.meequal);
                        img3.setImageResource(R.drawable.mesmile);
                    }
                });
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        img2.setImageResource(R.drawable.meequalcolored);
                        img1.setImageResource(R.drawable.mesad);
                        img3.setImageResource(R.drawable.mesmile);
                    }
                });

                img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        img1.setImageResource(R.drawable.mesad);
                        img2.setImageResource(R.drawable.meequal);
                        img3.setImageResource(R.drawable.mesmilecolored);
                    }
                });


            }
        });

        return view;
    }

}
