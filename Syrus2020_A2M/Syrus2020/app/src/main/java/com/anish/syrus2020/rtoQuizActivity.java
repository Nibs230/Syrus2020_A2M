package com.anish.syrus2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class rtoQuizActivity extends AppCompatActivity {


    private QuestionLibrary mQuestionLibrary=new QuestionLibrary();
    private TextView mScoreView;
    private TextView quest_out_of;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private LinearLayout mLinear1;
    private LinearLayout mLinear2;

    private TextView dScore;
    private Button dButton;

    private int rAns=0;
    private int wAns=0;

    private TextView tv_rAns;
    private TextView tv_wAns;

    private String mAnswer;
    private int mScore=0;
    private int mQuestionNumber=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rto_quiz);
        mScoreView=(TextView)findViewById(R.id.score);
        quest_out_of=(TextView)findViewById(R.id.no_of_questions);
        mQuestionView=(TextView)findViewById(R.id.question);
        mButtonChoice1=(Button) findViewById(R.id.choice1);
        mButtonChoice2=(Button) findViewById(R.id.choice2);
        mButtonChoice3=(Button) findViewById(R.id.choice3);
        mButtonChoice4=(Button) findViewById(R.id.choice4);
        mLinear1=(LinearLayout)findViewById(R.id.mcq_take);
        //mLinear2=(LinearLayout)findViewById(R.id.result_page);
        //dScore=(TextView)findViewById(R.id.result_score) ;

        tv_rAns=(TextView) findViewById(R.id.rAns);
        tv_wAns=(TextView) findViewById(R.id.wAns);

        mQuestionNumber=0;
        rAns=0;
        wAns=0;
        updateQuestion();

        //Radio1 ke options
        mButtonChoice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if( mButtonChoice1.getText()==mAnswer && mQuestionNumber<10){
                    mScore=mScore+1;
                    rAns=rAns+1;
                    updateScore(mScore);
                    updateQuestionsAttempted(rAns,wAns);
                    updateQuestion();
                    Toast.makeText(rtoQuizActivity.this,"Correct",Toast.LENGTH_SHORT).show();

                } else if(mQuestionNumber==10) {

                    if( mButtonChoice1.getText()==mAnswer){
                        mScore=mScore+1;
                        rAns=rAns+1;
                    }


                    Toast.makeText(rtoQuizActivity.this,"Successful",Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(rtoQuizActivity.this,ResultsActivity.class);
                    intent.putExtra("results",mScore);
                    startActivity(intent);
                    finish();


                }

                else{
                    Toast.makeText(rtoQuizActivity.this,"Wrong",Toast.LENGTH_SHORT).show();
                    wAns+=1;
                    updateQuestionsAttempted(rAns,wAns);
                    updateQuestion();

                }
            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if( mButtonChoice2.getText()==mAnswer && mQuestionNumber<10){
                    mScore=mScore+1;
                    rAns=rAns+1;
                    updateQuestionsAttempted(rAns,wAns);
                    updateScore(mScore);
                    updateQuestion();
                    Toast.makeText(rtoQuizActivity.this,"Correct",Toast.LENGTH_SHORT).show();

                }
                else if(mQuestionNumber==10) {

                    if( mButtonChoice2.getText()==mAnswer){
                        mScore=mScore+1;
                        rAns=rAns+1;
                    }


                    Toast.makeText(rtoQuizActivity.this,"Successful",Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(rtoQuizActivity.this,ResultsActivity.class);
                    intent.putExtra("results",mScore);
                    startActivity(intent);
                    finish();


                }else
                {
                    Toast.makeText(rtoQuizActivity.this,"Wrong",Toast.LENGTH_SHORT).show();
                    wAns+=1;
                    updateQuestionsAttempted(rAns,wAns);
                    updateQuestion();

                }
            }
        });


        mButtonChoice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if( mButtonChoice3.getText()==mAnswer && mQuestionNumber<10){
                    mScore=mScore+1;
                    rAns=rAns+1;
                    updateQuestionsAttempted(rAns,wAns);
                    updateScore(mScore);
                    updateQuestion();
                    Toast.makeText(rtoQuizActivity.this,"Correct",Toast.LENGTH_SHORT).show();

                }
                else if(mQuestionNumber==10) {

                    if( mButtonChoice3.getText()==mAnswer){
                        mScore=mScore+1;
                        rAns=rAns+1;
                    }


                    Toast.makeText(rtoQuizActivity.this,"Successful",Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(rtoQuizActivity.this,ResultsActivity.class);
                    intent.putExtra("results",mScore);
                    startActivity(intent);
                    finish();


                }else
                {
                    Toast.makeText(rtoQuizActivity.this,"Wrong",Toast.LENGTH_SHORT).show();
                    wAns+=1;
                    updateQuestionsAttempted(rAns,wAns);
                    updateQuestion();

                }
            }
        });


        mButtonChoice4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if( mButtonChoice3.getText()==mAnswer && mQuestionNumber<10){
                    mScore=mScore+1;
                    rAns=rAns+1;
                    updateScore(mScore);
                    updateQuestionsAttempted(rAns,wAns);
                    updateQuestion();
                    Toast.makeText(rtoQuizActivity.this,"Correct",Toast.LENGTH_SHORT).show();

                }
                else if(mQuestionNumber==10) {

                    if( mButtonChoice4.getText()==mAnswer){
                        mScore=mScore+1;
                        rAns=rAns+1;
                    }


                    Toast.makeText(rtoQuizActivity.this,"Successful",Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(rtoQuizActivity.this,ResultsActivity.class);
                    intent.putExtra("results",mScore);
                    startActivity(intent);
                    finish();


                }else
                {
                    Toast.makeText(rtoQuizActivity.this,"Wrong",Toast.LENGTH_SHORT).show();
                    updateQuestionsAttempted(rAns,wAns);
                    updateQuestion();

                }
            }
        });


    }


    private void updateQuestionsAttempted(int rAns, int wAns) {
        tv_rAns.setText(""+rAns);
        tv_wAns.setText(""+wAns);
    }

    private void updateQuestion(){

        int x=mQuestionNumber+1;
        quest_out_of.setText(""+x);
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        if(mQuestionLibrary.getNoOptions(mQuestionNumber)>=2)
        {
            mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
            mButtonChoice3.setVisibility(View.GONE);
            mButtonChoice4.setVisibility(View.GONE);
            if(mQuestionLibrary.getNoOptions(mQuestionNumber)>=3)
            {
                mButtonChoice3.setVisibility(View.VISIBLE);
                mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
                if(mQuestionLibrary.getNoOptions(mQuestionNumber)==4)
                {
                    mButtonChoice4.setVisibility(View.VISIBLE);
                    mButtonChoice4.setText(mQuestionLibrary.getChoice4(mQuestionNumber));
                }
            }
        }


        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;
    }

    private void updateScore(int point){
        mScoreView.setText(""+mScore);
    }
}
