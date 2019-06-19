package com.abc.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Staff_Add_Activity extends AppCompatActivity {

    Button view_members, add ;
    private EditText name, age ,gen, address, tp , dpt;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff__add_);

        //connect to DB
        databaseHelper = new DatabaseHelper(this);

        //get values and assing the valiables
        add = (Button) findViewById(R.id.member_reg_btn);
        view_members = (Button) findViewById(R.id.view_members_btn);
        name = (EditText) findViewById(R.id.nameS);
        age = (EditText) findViewById(R.id.ageS);
        gen = (EditText) findViewById(R.id.genderS);
        address = (EditText) findViewById(R.id.addS);
        tp = (EditText) findViewById(R.id.tpS);
        dpt = (EditText) findViewById(R.id.dptS);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //vallidation
                if(name.getText().toString().equals("")|| age.getText().toString().equals("")|| gen.getText().toString().equals("")||address.getText().toString().equals("") ||tp.getText().toString().equals("") || dpt.getText().toString().equals("")){
                    Toast.makeText(Staff_Add_Activity.this, "FILL THE EMPTY FEILDS!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(age.getText().toString().length() > 2){
                    Toast.makeText(Staff_Add_Activity.this, "Invalide Age!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(tp.getText().toString().length() > 10 || tp.getText().toString().length() < 10 ){
                    Toast.makeText(Staff_Add_Activity.this, "Number Must 10 Digit!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!name.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Staff_Add_Activity.this, "Please Enter Letters for Name", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(!gen.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Staff_Add_Activity.this, "Please Enter Letters for Gender", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(!dpt.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Staff_Add_Activity.this, "Please Enter Letters for Departmment", Toast.LENGTH_SHORT).show();
                    return ;
                }


                //insert values in to the DB
                databaseHelper.addStaffDetail(name.getText().toString(), age.getText().toString() ,gen.getText().toString(), address.getText().toString() , tp.getText().toString() , dpt.getText().toString());
                //clear add form
                name.setText("");
                age.setText("");
                gen.setText("");
                address.setText("");
                tp.setText("");
                dpt.setText("");

                Toast.makeText(Staff_Add_Activity.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        //button click
        view_members.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Staff_Add_Activity.this,Staff_list_display_Activity.class);
                startActivity(intent);
            }
        });

    }
}
