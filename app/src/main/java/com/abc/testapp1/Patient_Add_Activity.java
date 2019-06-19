package com.abc.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Patient_Add_Activity extends AppCompatActivity {

    private Button register;
    private EditText pname, page,paddress,pphoneNo,pDisease,pGender,pGurdian;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpatient);

        databaseHelper = new DatabaseHelper(this);

        register=(Button) findViewById(R.id.reg_button);
        pname = (EditText) findViewById(R.id.pname);
        page = (EditText) findViewById(R.id.page);
        paddress = (EditText) findViewById(R.id.paddress);
        pphoneNo = (EditText) findViewById(R.id.pphone);
        pDisease = (EditText) findViewById(R.id.pdisease);
        pGender= (EditText) findViewById(R.id.pgender);
        pGurdian= (EditText) findViewById(R.id.pgurName);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Empty Patient Field Validate
                if(pname.getText().toString().equals("")||page.getText().toString().equals("")||paddress.getText().toString().equals("")||pphoneNo.getText().toString().equals("")||pDisease.getText().toString().equals("")||pGender.getText().toString().equals("")||pGurdian.getText().toString().equals("")){
                    Toast.makeText(Patient_Add_Activity.this, "Fill The Empty Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                ///Patient Name Validation
                if(!pname.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Patient_Add_Activity.this, "Please Enter Letters for Name", Toast.LENGTH_SHORT).show();
                    return ;
                }

                //Patient Age Validation
                if(page.getText().toString().length()>2){
                    Toast.makeText(Patient_Add_Activity.this, "Age is Wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Patient location Validation
                if(!paddress.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Patient_Add_Activity.this, "Please Enter Letters for Location", Toast.LENGTH_SHORT).show();
                    return ;
                }

                //Phone Validation
                if(pphoneNo.getText().toString().length()>10 ||pphoneNo.getText().toString().length()< 10 ){
                    Toast.makeText(Patient_Add_Activity.this, "Phone Number is Wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Patient diseases Validation
                if(!pDisease.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Patient_Add_Activity.this, "Please Enter Letters for Disease", Toast.LENGTH_SHORT).show();
                    return ;
                }

                //Patient gender Validation
                if(!pGender.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Patient_Add_Activity.this, "Please Enter Letters for Gender", Toast.LENGTH_SHORT).show();
                    return ;
                }

                //Patient guardian name Validation
                if(! pGurdian.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Patient_Add_Activity.this, "Please Enter Letters for Guardian", Toast.LENGTH_SHORT).show();
                    return ;
                }


                databaseHelper.addPatientDetail(pname.getText().toString(),page.getText().toString(),paddress.getText().toString(),pphoneNo.getText().toString(),pDisease.getText().toString(),pGender.getText().toString(),pGurdian.getText().toString());
                pname.setText("");
                page.setText("");
                paddress.setText("");
                pphoneNo.setText("");
                pDisease.setText("");
                pGender.setText("");
                pGurdian.setText("");
                Toast.makeText(Patient_Add_Activity.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Patient_Add_Activity.this, Patient_View_Activity.class);
                startActivity(intent);
            }
        });


    }
}
