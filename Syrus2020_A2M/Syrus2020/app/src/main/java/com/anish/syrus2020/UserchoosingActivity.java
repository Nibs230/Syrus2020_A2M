package com.anish.syrus2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserchoosingActivity extends AppCompatActivity {
    private Button user,doctor,hospital,driver,medic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userchoosing);
        user = findViewById(R.id.user);
        doctor = findViewById(R.id.doctor);
        hospital = findViewById(R.id.hospital);
        driver = findViewById(R.id.driver);
        medic = findViewById(R.id.medic);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserchoosingActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserchoosingActivity.this,DriverMapActivity.class);
                startActivity(intent);
            }
        });

        medic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserchoosingActivity.this,QrCodeTry.class);
                startActivity(intent);
            }
        });
    }
}
