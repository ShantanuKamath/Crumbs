package com.example.priyanshu.crumbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class AddressSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_select);

    }
    public void callRestSelection(View view) {
        Intent in = new Intent(this, MenuSelect.class);
        Intent i=getIntent();
        in.putExtra("RESTNAME", i.getSerializableExtra("RESTNAME").toString());
        in.putExtra("TIME", i.getSerializableExtra("TIME").toString());
        in.putExtra("Date", i.getSerializableExtra("Date").toString());
        Log.v("BOBEY", i.getSerializableExtra("Date").toString());

        startActivity(in);


    }


}
