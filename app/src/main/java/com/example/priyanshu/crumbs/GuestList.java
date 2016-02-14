package com.example.priyanshu.crumbs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.parse.ParseUser;

import java.util.ArrayList;

public class GuestList extends AppCompatActivity {


    ArrayList<String> finalArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox_friends);
        ParseUser user = ParseUser.getCurrentUser();
        ArrayList<String> Logs  = (ArrayList<String>) user.get("friends");
        LinearLayout lw = (LinearLayout) findViewById(R.id.guest_list);
        CheckBox checkBox;
        Log.d("GUEST","Checkbox time");
        for(int i=0; i<Logs.size(); i++)
        {
            checkBox=new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(Logs.get(i));
            checkBox.setOnClickListener(getOnClickDo(checkBox));
            lw.addView(checkBox);
        }
        Log.d("GUEST","Checkbox ready");
    }

    View.OnClickListener getOnClickDo(final Button b)
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(finalArray.contains(b.getText().toString()))
                    finalArray.remove(b.getText().toString());
                else
                    finalArray.add(b.getText().toString());
            }
        };
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
        if (id == R.id.done) {
            TabBarActivity.numFriends.setText(""+finalArray.size());
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
