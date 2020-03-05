package com.anish.syrus2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    int mScore;

    CardView card2;
    CardView card1;
    TextView result_score;
    TextView result_score_2;
    Button btn_retake;

    TextView right_answers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        result_score=findViewById(R.id.result_score);
        result_score_2=(TextView)findViewById(R.id.result_score_2);
        //right_answers=findViewById(R.id.right_answers);
        btn_retake=findViewById(R.id.btn_retake);
        card1=(CardView)findViewById(R.id.card1);
        card2=(CardView)findViewById(R.id.card2);


        Intent intent=getIntent();
        mScore=intent.getIntExtra("results",0);

        if(mScore>7)
        {
            card1.setVisibility(View.GONE);
            card2.setVisibility(View.VISIBLE);
            result_score_2.append(""+mScore);
        }

        else if(mScore<=7)
        {
            card2.setVisibility(View.GONE);
            card1.setVisibility(View.VISIBLE);
            result_score.append(""+mScore);
        }

        btn_retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultsActivity.this,rtoQuizActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
