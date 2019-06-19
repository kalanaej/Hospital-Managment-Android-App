package com.abc.testapp1;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<DoctorModel> userModelArrayList;
    private DoctorAdapter doctorAdapter;
    private DatabaseHelper databaseHelper;
    private Button back;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initView();
        loadData();

        back = (Button) findViewById(R.id.back_btn);

        listView = (ListView) findViewById(R.id.lv);

        databaseHelper = new DatabaseHelper(this);

        userModelArrayList = databaseHelper.getAllDoctors();

        doctorAdapter = new DoctorAdapter(this,userModelArrayList);
        listView.setAdapter(doctorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DocEditActivity.class);
                intent.putExtra("user",userModelArrayList.get(position));
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, DocActivity.class);
                startActivity(intent);
            }
        });



    }

    private void initView() {
        listView = findViewById(R.id.lv);
        registerForContextMenu(listView);
    }

    private void loadData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        ArrayList<DoctorModel> doctorModels = databaseHelper.getAllDoctors();
        if (doctorModels != null) {
            listView.setAdapter(new DoctorAdapter(getApplicationContext(), doctorModels));
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
        ArrayList<DoctorModel> doctorModels = databaseHelper.search(keyword);
        if (doctorModels != null) {
            listView.setAdapter(new DoctorAdapter(getApplicationContext(), doctorModels));
        }
    }
}
