package com.example.priyanshu.crumbs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuSelect extends AppCompatActivity {

    int quantity1;
    int quantity2;
    int quantity3;
    int quantity4;
    int quantity5;
    int quantity6;
    public static TextView q1, q2, q3, q4, q5,q6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_select);

        //////// quantities.
        q1= (TextView) findViewById(R.id.quantity1);
        q2= (TextView) findViewById(R.id.quantity2);
        q3= (TextView) findViewById(R.id.quantity3);
        q4= (TextView) findViewById(R.id.quantity4);
        q5= (TextView) findViewById(R.id.quantity5);
        q6= (TextView) findViewById(R.id.quantity6);
    }
    public void increment1(View view) {
        if (quantity1 < 100)
            displayQuantity(++quantity1, q1);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have more than 100 items";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void decrement1(View view) {
        if (quantity1 > 0)
            displayQuantity(--quantity1, q1);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have less than 0 items";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void increment2(View view) {
        if (quantity2 < 100)
            displayQuantity(++quantity2, q2);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have more than 100 items";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void decrement2(View view) {
        if (quantity2 > 0)
            displayQuantity(--quantity2, q2);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have less than 0 items";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void increment3(View view) {
        if (quantity3 < 100)
            displayQuantity(++quantity3, q3);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have more than 100 items";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void decrement3(View view) {
        if (quantity3 > 0)
            displayQuantity(--quantity3, q3);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have less than 0 items";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void increment4(View view) {
        if (quantity4 < 100)
            displayQuantity(++quantity4, q4);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have more than 100 items";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void decrement4(View view) {
        if (quantity4 > 0)
            displayQuantity(--quantity4, q4);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have less than 0 items";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    public void increment5(View view) {
        if (quantity5 < 100)
            displayQuantity(++quantity5, q5);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have more than 100 items";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void decrement5(View view) {
        if (quantity5 > 0)
            displayQuantity(--quantity5, q5);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have less than 0 items";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
    public void increment6(View view) {
        if (quantity6 < 100)
            displayQuantity(++quantity6, q6);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have more than 100 items";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void decrement6(View view) {
        if (quantity6 > 0)
            displayQuantity(--quantity6, q6);
        else{
            Context context = getApplicationContext();
            CharSequence text = "You cannot have less than 0 items";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }
    private void displayQuantity(int number, TextView tv) {
        tv.setText("" + number);
    }

    public void payPal(View view) {
        Intent i = new Intent(this, Payment.class);
        startActivity(i);
    }
}
