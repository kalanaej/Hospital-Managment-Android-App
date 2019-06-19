package com.abc.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Staff_Update_Activity extends AppCompatActivity {

    private StaffModel staffModel;
    private EditText name, age, address, tp, dept,gen;
    private Button btnupdate, btndelete;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_update_activity_);

        Intent intent = getIntent();
        staffModel = (StaffModel) intent.getSerializableExtra("user");

        //connect DB
        databaseHelper = new DatabaseHelper(this);
        //get values
        name = (EditText) findViewById(R.id.StaffnameU);
        age = (EditText) findViewById(R.id.StaffageU);
        gen = (EditText) findViewById(R.id.StaffgenU);
        address = (EditText) findViewById(R.id.StaffaddU);
        tp = (EditText) findViewById(R.id.StafftpU);
        dept = (EditText) findViewById(R.id.StaffdepU);

        //button
        btndelete = (Button) findViewById(R.id.member_delete_btn);
        btnupdate = (Button) findViewById(R.id.member_update_btn);

        name.setText(staffModel.getName());
        age.setText(staffModel.getAge());
        gen.setText(staffModel.getGender());
        address.setText(staffModel.getAddress());
        tp.setText(staffModel.getTp());
        dept.setText(staffModel.getDepartment());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //valiidation
                if(name.getText().toString().equals("")|| age.getText().toString().equals("")||  gen.getText().toString().equals("")||address.getText().toString().equals("") ||tp.getText().toString().equals("") || dept.getText().toString().equals("")){
                    Toast.makeText(Staff_Update_Activity.this, "FILL THE EMPTY FEILDS!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(age.getText().toString().length() > 2){
                    Toast.makeText(Staff_Update_Activity.this, "Invalide Age!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(tp.getText().toString().length() > 10 || tp.getText().toString().length() < 10 ){
                    Toast.makeText(Staff_Update_Activity.this, "Number Must 10 Digit!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!name.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Staff_Update_Activity.this, "Please Enter Letters for Name", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(!gen.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Staff_Update_Activity.this, "Please Enter Letters for Gender", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(!dept.getText().toString().matches("[a-zA-Z ]+")){
                    Toast.makeText(Staff_Update_Activity.this, "Please Enter Letters for Departmment", Toast.LENGTH_SHORT).show();
                    return ;
                }
                //insert values in to the DB
                databaseHelper.updateStaff(staffModel.getId(),name.getText().toString(),age.getText().toString(),gen.getText().toString(),address.getText().toString(),tp.getText().toString(),dept.getText().toString() );
                Toast.makeText(Staff_Update_Activity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Staff_Update_Activity.this,Staff_list_display_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        //delete button code
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteStaff(staffModel.getId());
                Toast.makeText(Staff_Update_Activity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Staff_Update_Activity.this,Staff_list_display_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });



    }
}
