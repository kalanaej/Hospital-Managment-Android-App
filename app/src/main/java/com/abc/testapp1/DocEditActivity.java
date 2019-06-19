package com.abc.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DocEditActivity extends AppCompatActivity {

    private DoctorModel doctorModel;
    private EditText name, age, gender, special, phone, address, ward;
    private Button update, delete;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_edit);

        Intent intent = getIntent();
        doctorModel = (DoctorModel) intent.getSerializableExtra("user");

        databaseHelper = new DatabaseHelper(this);

        name = (EditText) findViewById(R.id.nametxt);
        age = (EditText) findViewById(R.id.agetxt);
        gender = (EditText) findViewById(R.id.gendertxt);
        special = (EditText) findViewById(R.id.specialtxt);
        phone = (EditText) findViewById(R.id.phonetxt);
        address = (EditText) findViewById(R.id.addresstxt);
        ward = (EditText) findViewById(R.id.wardtxt);


        delete = (Button) findViewById(R.id.delete_btn);
        update = (Button) findViewById(R.id.update_btn);

        name.setText(doctorModel.getName());
        age.setText(doctorModel.getAge());
        gender.setText(doctorModel.getGender());
        special.setText(doctorModel.getSpecial());
        phone.setText(doctorModel.getPhone());
        address.setText(doctorModel.getAddress());
        ward.setText(doctorModel.getWard());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("") || age.getText().toString().equals("") || gender.getText().toString().equals("") || special.getText().toString().equals("") || phone.getText().toString().equals("") || address.getText().toString().equals("") || ward.getText().toString().equals("")){
                    Toast.makeText(DocEditActivity.this, "Fields can not be empty!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!name.getText().toString().matches("[a-zA-Z]+") || !gender.getText().toString().matches("[a-zA-Z]+") || !special.getText().toString().matches("[a-zA-Z]+")){
                    Toast.makeText(DocEditActivity.this, "Name , gender and special fields must filled with Letters only", Toast.LENGTH_LONG).show();
                    return;
                }
                if(phone.getText().toString().length() > 10 || phone.getText().toString().length() < 10){
                    Toast.makeText(DocEditActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(age.getText().toString().length() > 2 || age.getText().toString().length() < 2){
                    Toast.makeText(DocEditActivity.this, "Invalid Age", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ward.getText().toString().length() > 3){
                    Toast.makeText(DocEditActivity.this, "Invalid ward number", Toast.LENGTH_SHORT).show();
                    return;
                }

                databaseHelper.updateDoctor(doctorModel.getId(),name.getText().toString(),age.getText().toString(),gender.getText().toString(),special.getText().toString(),phone.getText().toString(),address.getText().toString(),ward.getText().toString());
                Toast.makeText(DocEditActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DocEditActivity.this,ListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteDoctor(doctorModel.getId());
                Toast.makeText(DocEditActivity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DocEditActivity.this,ListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
