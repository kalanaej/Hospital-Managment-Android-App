package com.abc.testapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginMain extends AppCompatActivity {

    EditText urname,urpass;
    Button clk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        urname = (EditText) findViewById(R.id.nameT);
        urpass = (EditText) findViewById(R.id.passT);
        clk = (Button) findViewById(R.id.loginbtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void movePage(View v){

        String stname = urname.getText().toString();
        String stpass = urpass.getText().toString();

        if(stname.equals("a") && stpass.equals("a")){
            Intent intent = new Intent(loginMain.this,MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("uname",stname);

            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(stname.equals("") && stpass.equals("")){
            Toast.makeText(getBaseContext(),"Enter Both Username & Password",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(),"Wrong Username & Password",Toast.LENGTH_SHORT).show();
        }
    }
}
