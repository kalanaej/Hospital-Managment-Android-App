package com.abc.testapp1;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Staff_List_Main_Activity extends AppCompatActivity {

    Button staff_list;

    private ListView listView;
    private ArrayList<StaffModel> staffModelArrayList;
    private StaffCustomAdapter customAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_list_main_activity);


        initView();
        loadData();


        //list view
        listView = (ListView) findViewById(R.id.list);

        //connect DB
        databaseHelper = new DatabaseHelper(this);

        //get list
        staffModelArrayList = databaseHelper.getAllStaff();

        customAdapter = new StaffCustomAdapter(this,staffModelArrayList);
        listView.setAdapter(customAdapter);

        staff_list = findViewById(R.id.add_new_member_btn);

        staff_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Staff_List_Main_Activity.this, Staff_Add_Activity.class);
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
