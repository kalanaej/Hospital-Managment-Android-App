package com.abc.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DocAddActivity extends AppCompatActivity {

    private Button add, demo;
    private EditText name, age, gender, special, phone, address, ward;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_add);

        databaseHelper = new DatabaseHelper(this);

        add = (Button) findViewById(R.id.add_btn);
        demo = (Button) findViewById(R.id.demobtn);

        name = (EditText) findViewById(R.id.nametxt);
        age = (EditText) findViewById(R.id.agetxt);
        gender = (EditText) findViewById(R.id.gendertxt);
        special = (EditText) findViewById(R.id.specialtxt);
        phone = (EditText) findViewById(R.id.phonetxt);
        address = (EditText) findViewById(R.id.addresstxt);
        ward = (EditText) findViewById(R.id.wardtxt);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("") || age.getText().toString().equals("") || gender.getText().toString().equals("") || special.getText().toString().equals("") || phone.getText().toString().equals("") || address.getText().toString().equals("") || ward.getText().toString().equals("")){
                    Toast.makeText(DocAddActivity.this, "Fields can not be empty!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!name.getText().toString().matches("[a-zA-Z]+") || !gender.getText().toString().matches("[a-zA-Z]+") || !special.getText().toString().matches("[a-zA-Z]+")){
                    Toast.makeText(DocAddActivity.this, "Name , gender and special fields must filled with Letters only", Toast.LENGTH_LONG).show();
                    return;
                }
                if(phone.getText().toString().length() > 10 || phone.getText().toString().length() < 10){
                    Toast.makeText(DocAddActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(age.getText().toString().length() > 2 || age.getText().toString().length() < 2){
                    Toast.makeText(DocAddActivity.this, "Invalid Age", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ward.getText().toString().length() > 3){
                    Toast.makeText(DocAddActivity.this, "Invalid ward number", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseHelper.addDoctorDetail(name.getText().toString(), age.getText().toString(), gender.getText().toString(), special.getText().toString(), phone.getText().toString(), address.getText().toString(), ward.getText().toString());
                name.setText("");
                age.setText("");
                gender.setText("");
                special.setText("");
                phone.setText("");
                address.setText("");
                ward.setText("");
                Toast.makeText(DocAddActivity.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(DocAddActivity.this, DocActivity.class);
                startActivity(intent);
            }
        });

        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("jayasuriya");
                age.setText("28");
                gender.setText("male");
                special.setText("heart");
                phone.setText("0789395184");
                address.setText("colombo");
                ward.setText("25");
            }
        });
    }
}
