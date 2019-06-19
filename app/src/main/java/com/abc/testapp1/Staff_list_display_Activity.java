package com.abc.testapp1;


import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Staff_list_display_Activity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<StaffModel> staffModelArrayList;
    private StaffCustomAdapter customAdapter;
    private DatabaseHelper databaseHelper;
    private Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_list_display_activity_);


        initView();
        loadData();


        listView = (ListView) findViewById(R.id.list);

        b1 = findViewById(R.id.buttonStaffBack);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Staff_list_display_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(this);

        staffModelArrayList = databaseHelper.getAllStaff();

        customAdapter = new StaffCustomAdapter(this,staffModelArrayList);
        listView.setAdapter(customAdapter);


        //click list and go to edit page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Staff_list_display_Activity.this,Staff_Update_Activity.class);
                intent.putExtra("user", (staffModelArrayList.get(position)));
                startActivity(intent);
            }
        });

    }

    // SEARCH PART
    private void initView() {
        listView = findViewById(R.id.list);
        registerForContextMenu(listView);
    }

    private void loadData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        ArrayList<StaffModel> StaffC = databaseHelper.getAllStaff();
        if (StaffC != null) {
            listView.setAdapter(new StaffCustomAdapter(getApplicationContext(), StaffC));
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
        ArrayList<StaffModel> Staff = databaseHelper.searchStaff(keyword);
        if (Staff != null) {
            listView.setAdapter(new StaffCustomAdapter(getApplicationContext(), Staff));
        }
    }



}
