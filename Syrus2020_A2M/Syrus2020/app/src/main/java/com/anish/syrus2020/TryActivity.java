package com.anish.syrus2020;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class TryActivity extends AppCompatActivity {

    private Button sub;

    private EditText edt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);

        sub = (Button) findViewById(R.id.adnani);
        edt = (EditText) findViewById(R.id.anish);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String abc = edt.getText().toString();

                FirebaseDatabase.getInstance().getReference().child("anish").setValue(abc);
            }
        });
    }
}
