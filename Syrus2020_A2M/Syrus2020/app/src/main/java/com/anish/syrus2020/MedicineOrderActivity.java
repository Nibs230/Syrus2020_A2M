package com.anish.syrus2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class MedicineOrderActivity extends AppCompatActivity {

    private TextView orderr;
    private DatabaseReference reff;
    private Button done;
    private EditText quantity;
    String userid;
    private ProgressDialog mProgress;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_order);

        String order = getIntent().getStringExtra("order");
        reff = FirebaseDatabase.getInstance().getReference().child("Medical_Order");

        orderr = (TextView) findViewById(R.id.medic_name);
        done = (Button) findViewById(R.id.medic_order);
        quantity = (EditText) findViewById(R.id.medic_quantity);
        userid = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        int index = userid.indexOf("@");
        userid = userid.substring(0,index);


        orderr.setText(order);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String, Object> medic_data = new HashMap<>();
                String name = orderr.getText().toString();
                String namelist[] = name.split(",");

                String quan = quantity.getText().toString();
                String quanList[] = quan.split(",");

                Integer length = namelist.length;
                for(int i=0;i<length;i++)
                {

                    medic_data.put("medicine"+i,namelist[i]);
                    medic_data.put("Quantity"+i,quanList[i]);
                }
                medic_data.put("uid",FirebaseAuth.getInstance().getCurrentUser().getUid());

                if(quan.equals(""))
                {
                    Toast.makeText(MedicineOrderActivity.this,"Enter Quantity",Toast.LENGTH_SHORT).show();
                }
                else{


                    mProgress = new ProgressDialog(MedicineOrderActivity.this);
                    mProgress.setTitle("Getting your order placed");
                    mProgress.setMessage("Please wait while we are reporting your case");
                    mProgress.show();

                    final String key = reff.push().getKey();

                    reff.child(key).updateChildren(medic_data)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        mProgress.dismiss();
                                        Toast.makeText(MedicineOrderActivity.this,"Case Reported Successfully",Toast.LENGTH_SHORT).show();

                                        updNotification();
                                        // new GlideToast.makeToast(firRegistration.this,"Case Successfully Reported",GlideToast.LENGTHLONG,GlideToast.SUCCESSTOAST,GlideToast.BOTTOM).show();
                                        Intent intent = new Intent(MedicineOrderActivity.this,MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        //Intent mainIntent = new Intent(firRegistration.this,MainActivity.class);
                                        //mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        //startActivity(mainIntent);
                                        //Toast.makeText(MainActivity.this,"Case Successfully Reported",Toast.LENGTH_SHORT).show();

                                    }
                                    else
                                    {
                                        mProgress.dismiss();
                                        new GlideToast.makeToast(MedicineOrderActivity.this,"Network Error",GlideToast.LENGTHLONG,GlideToast.FAILTOAST,GlideToast.BOTTOM).show();
                                        //Toast.makeText(MainActivity.this,"Network error",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }


            }
        });



    }

    private void updNotification() {

        DatabaseReference NotificationDatabase;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String date_apply = sdf.format(new Date());
        Calendar calender = Calendar.getInstance();
        calender.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        String time_apply = calender.get(Calendar.HOUR_OF_DAY) + ":" + calender.get(Calendar.MINUTE);
        mProgress = new ProgressDialog(MedicineOrderActivity.this);
        mProgress.setTitle("Updating Database");
        mProgress.setMessage("Please wait updating");
        mProgress.show();

        HashMap<String, Object> notification_data = new HashMap<>();
        notification_data.put("date",date_apply);
        notification_data.put("desc","your medicine order is placed..and you will be contacted once accepted by merchant");
        notification_data.put("time",time_apply);
        notification_data.put("type","booking");
        NotificationDatabase = FirebaseDatabase.getInstance().getReference().child("Notification");
        final String key = NotificationDatabase.push().getKey();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String phone = mAuth.getCurrentUser().getEmail();
        int index = phone.indexOf('@');
        phone = phone.substring(0,index);

        NotificationDatabase.child(phone).child(key).updateChildren(notification_data)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {
                            mProgress.dismiss();
                            Toast.makeText(MedicineOrderActivity.this,"Successfully REgistered",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MedicineOrderActivity.this,MainActivity.class);
                            startActivity(intent);
                            // new GlideToast.makeToast(PoliceFineActivity.this,"Data Updated",GlideToast.LENGTHLONG,GlideToast.SUCCESSTOAST,GlideToast.BOTTOM).show();

                        }
                        else
                        {
                            mProgress.dismiss();
                            // new GlideToast.makeToast(PoliceFineActivity.this,"Network Error",GlideToast.LENGTHLONG,GlideToast.FAILTOAST,GlideToast.BOTTOM).show();
                        }
                    }
                });
    }
}
