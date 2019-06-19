package com.abc.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Patient_Edit_Activity extends AppCompatActivity {


    private PatientModel patientModel;
    private EditText ename, eage,eaddress,ephone,edisease,egender,eguardian;
    private Button update,delete;;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editpatient);

        Intent intent = getIntent();
        patientModel = (PatientModel) intent.getSerializableExtra("user");

        databaseHelper = new DatabaseHelper(this);

        ename = (EditText) findViewById(R.id.ename);
        eage = (EditText) findViewById(R.id.eage);
        eaddress = (EditText) findViewById(R.id.eaddress);
        ephone = (EditText) findViewById(R.id.ephone);
        edisease = (EditText) findViewById(R.id.edisease);
        egender = (EditText) findViewById(R.id.egender);
        eguardian = (EditText) findViewById(R.id.eguardian);
        update = (Button) findViewById(R.id.updatepat_btn);
        delete = (Button) findViewById(R.id.delete_btn);

        ename.setText(patientModel.getName());
        eage.setText(patientModel.getAge());
        eaddress.setText(patientModel.getAddress());
        ephone.setText(patientModel.getContNo());
        edisease.setText(patientModel.getDisease());
        egender.setText(patientModel.getGender());
        eguardian.setText(patientModel.getGurName());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ename.getText().toString().equals("")||eage.getText().toString().equals("")||eaddress.getText().toString().equals("")||ephone.getText().toString().equals("")||edisease.getText().toString().equals("")||egender.getText().toString().equals("")||eguardian.getText().toString().equals("")){
                    Toast.makeText(Patient_Edit_Activity.this, "Fill The Empty Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Patient Name Validation
                if(!ename.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Patient_Edit_Activity.this, "Please Enter Letters for Name", Toast.LENGTH_SHORT).show();
                    return ;
                }

                //Patient Age Validation
                if(eage.getText().toString().length()>2){
                    Toast.makeText(Patient_Edit_Activity.this, "Age is Wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Patient location Validation
                if(!eaddress.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Patient_Edit_Activity.this, "Please Enter Letters for Location", Toast.LENGTH_SHORT).show();
                    return ;
                }

                //Phone Validation
                if(ephone.getText().toString().length()>10 ||ephone.getText().toString().length()< 10 ){
                    Toast.makeText(Patient_Edit_Activity.this, "Phone Number is Wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Patient diseases Validation
                if(!edisease.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Patient_Edit_Activity.this, "Please Enter Letters for Disease", Toast.LENGTH_SHORT).show();
                    return ;
                }

                //Patient gender Validation
                if(!egender.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Patient_Edit_Activity.this, "Please Enter Letters for Gender", Toast.LENGTH_SHORT).show();
                    return ;
                }

                //Patient guardian name Validation
                if(! eguardian.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Patient_Edit_Activity.this, "Please Enter Letters for Guardian", Toast.LENGTH_SHORT).show();
                    return ;
                }


                databaseHelper.updatePatient(patientModel.getId(),ename.getText().toString(),eage.getText().toString(),eaddress.getText().toString(),ephone.getText().toString(),edisease.getText().toString(),egender.getText().toString(),eguardian.getText().toString());
                Toast.makeText(Patient_Edit_Activity.this, "Updated Patient Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Patient_Edit_Activity.this, Patient_View_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deletePatient(patientModel.getId());
                Toast.makeText(Patient_Edit_Activity.this, "Delete Patient Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Patient_Edit_Activity.this, Patient_View_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
