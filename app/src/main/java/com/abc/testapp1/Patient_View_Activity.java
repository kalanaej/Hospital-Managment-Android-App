package com.abc.testapp1;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class Patient_View_Activity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<PatientModel>  patientModelArrayList;
    private PatientAdapter patientAdapter;
    private DatabaseHelper databaseHelper;
    Button viewadd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpatient);



        listView = (ListView) findViewById(R.id.listView);

        databaseHelper = new DatabaseHelper(this);

        patientModelArrayList = databaseHelper.getAllUsers();

        patientAdapter = new PatientAdapter(this,  patientModelArrayList);
        listView.setAdapter(patientAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                initView();
                loadData();
             Intent intent = new Intent(Patient_View_Activity.this, Patient_Edit_Activity.class);
               intent.putExtra("user",patientModelArrayList.get(position));
               startActivity(intent);
            }
        });
        viewadd=findViewById(R.id.view_addbtn);


        viewadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Patient_View_Activity.this, Patient_Add_Activity.class);
                startActivity(intent);
            }
        });



    }

    private void initView() {
        listView = findViewById(R.id.listView);
        registerForContextMenu(listView);
    }

    private void loadData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        ArrayList<PatientModel> patientmodel = databaseHelper.getAllUsers();
        if (patientmodel != null) {
            listView.setAdapter(new PatientAdapter(getApplicationContext(), patientmodel));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchView).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchContact(String keyword) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        ArrayList<PatientModel> patientModels = databaseHelper.searchPatients(keyword);
        if (patientModels != null) {
            listView.setAdapter(new PatientAdapter(getApplicationContext(), patientModels));
        }
    }

}
