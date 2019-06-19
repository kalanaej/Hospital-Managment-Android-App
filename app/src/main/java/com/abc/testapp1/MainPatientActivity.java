package com.abc.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPatientActivity extends AppCompatActivity {

    Button opd,ward;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patienthomepage);

        opd=findViewById(R.id.opd_button1);
        opd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPatientActivity.this, Patient_Add_Activity.class);
                startActivity(intent);
            }
            });

        ward=findViewById(R.id.ward_btn);
        ward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPatientActivity.this, Patient_View_Activity.class);
                startActivity(intent);
            }
        });

    }
}
