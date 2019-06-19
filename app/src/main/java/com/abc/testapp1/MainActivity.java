package com.abc.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton doctor, patient, staff, drug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doctor = (ImageButton) findViewById(R.id.doctor_btn);
        patient = (ImageButton) findViewById(R.id.patient_btn);
        staff = (ImageButton) findViewById(R.id.staff_btn);
        drug = (ImageButton) findViewById(R.id.drugs_btn);

        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DocActivity.class);
                startActivity(intent);
            }
        });

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainPatientActivity.class);
                startActivity(intent);
            }
        });

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Staff_List_Main_Activity.class);
                startActivity(intent);
            }
        });

        drug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrugsMain.class);
                startActivity(intent);
            }
        });
    }
}
