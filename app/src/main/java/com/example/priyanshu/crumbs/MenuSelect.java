package com.example.priyanshu.crumbs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuSelect extends AppCompatActivity {

    int quantity1;
    int quantity2;
    int quantity3;
    int quantity4;
    int quantity5;
    int quantity6;

    int quantity[]= new int[6];
    String[] nameOfDish;
    String[] priceOfDish;

    public static TextView q1, q2, q3, q4, q5, q6;
    public static TextView t1, t2, t3, t4, t5, t6;
    public static TextView p1, p2, p3, p4, p5, p6;
    public static ImageView i1, i2, i3, i4, i5, i6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_select);


        //////// quantities.
        q1 = (TextView) findViewById(R.id.quantity1);
        q2 = (TextView) findViewById(R.id.quantity2);
        q3 = (TextView) findViewById(R.id.quantity3);
        q4 = (TextView) findViewById(R.id.quantity4);
        q5 = (TextView) findViewById(R.id.quantity5);
        q6 = (TextView) findViewById(R.id.quantity6);

        //////// textdishnames.
        t1 = (TextView) findViewById(R.id.TextDishName1);
        t2 = (TextView) findViewById(R.id.TextDishName2);
        t3 = (TextView) findViewById(R.id.TextDishName3);
        t4 = (TextView) findViewById(R.id.TextDishName4);
        t5 = (TextView) findViewById(R.id.TextDishName5);
        t6 = (TextView) findViewById(R.id.TextDishName6);

        //////// prices.
        p1 = (TextView) findViewById(R.id.price1);
        p2 = (TextView) findViewById(R.id.price2);
        p3 = (TextView) findViewById(R.id.price3);
        p4 = (TextView) findViewById(R.id.price4);
        p5 = (TextView) findViewById(R.id.price5);
        p6 = (TextView) findViewById(R.id.price6);

        ////ImageView
        i1 = (ImageView) findViewById(R.id.image1);
        i2 = (ImageView) findViewById(R.id.image2);
        i3 = (ImageView) findViewById(R.id.image3);
        i4 = (ImageView) findViewById(R.id.image4);
        i5 = (ImageView) findViewById(R.id.image5);
        i6 = (ImageView) findViewById(R.id.image6);

//        TextView restName = (TextView) findViewById(R.id.rest_text);
        Intent intent = getIntent();
        String restaurant = (String) intent.getSerializableExtra("RESTNAME");

        switch (restaurant) {
            case "Aston's Specialities":
                nameOfDish = new String[]{"Spicy Chicken Spaghetti", "Prime Ribeye", "Beefy Jack Burger", "Grilled Fish with Herb", "Canned Beer", "Red Wine Glass"};
                priceOfDish = new String[]{"SGD 8.9", "SGD 17.9", "SGD 6.9", "SGD 10.5", "SGD 4.8", "SGD 8.0"};
                i1.setImageResource(R.drawable.ai1);
                i2.setImageResource(R.drawable.ai2);
                i3.setImageResource(R.drawable.ai3);
                i4.setImageResource(R.drawable.ai4);
                i5.setImageResource(R.drawable.ai5);
                i6.setImageResource(R.drawable.ai6);
                break;
            case "Blu Jaz":
                nameOfDish = new String[]{"Spicy Chicken Spaghetti", "Prime Ribeye", "Beefy Jack Burger", "Grilled Fish with Herb", "Canned Beer", "Red Wine Glass"};
                priceOfDish = new String[]{"SGD 8.9", "SGD 17.9", "SGD 6.9", "SGD 10.5", "SGD 4.8", "SGD 8.0"};
                break;
            case "Tomato's Pizza @ Great World City":
                nameOfDish = new String[]{"Spicy Chicken Spaghetti", "Prime Ribeye", "Beefy Jack Burger", "Grilled Fish with Herb", "Canned Beer", "Red Wine Glass"};
                priceOfDish = new String[]{"SGD 8.9", "SGD 17.9", "SGD 6.9", "SGD 10.5", "SGD 4.8", "SGD 8.0"};
                break;
            default:
            case "Prata Wala":
                nameOfDish = new String[]{"Spicy Chicken Spaghetti", "Prime Ribeye", "Beefy Jack Burger", "Grilled Fish with Herb", "Canned Beer", "Red Wine Glass"};
                priceOfDish = new String[]{"SGD 8.9", "SGD 17.9", "SGD 6.9", "SGD 10.5", "SGD 4.8", "SGD 8.0"};
                break;

        }

        /// Putting values to menu
        t1.setText(nameOfDish[0]);
        t2.setText(nameOfDish[1]);
        t3.setText(nameOfDish[2]);
        t4.setText(nameOfDish[3]);
        t5.setText(nameOfDish[4]);
        t6.setText(nameOfDish[5]);

        p1.setText(priceOfDish[0]);
        p2.setText(priceOfDish[1]);
        p3.setText(priceOfDish[2]);
        p4.setText(priceOfDish[3]);
        p5.setText(priceOfDish[4]);
        p6.setText(priceOfDish[5]);


    }

    public void increment1(View view) {
        if (quantity1 < 100)
            displayQuantity(++quantity1, q1);
        else {
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
        else {
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
        else {
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
        else {
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
        else {
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
        else {
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
        else {
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
        else {
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
        else {
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
        else {
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
        else {
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
        else {
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
        quantity[0]=quantity1;
        quantity[1]=quantity2;
        quantity[2]=quantity3;
        quantity[3]=quantity4;
        quantity[4]=quantity5;
        quantity[5]=quantity6;
        Intent a= getIntent();
        Intent i = new Intent(this, Payment.class);
        i.putExtra("QUANTITIES", quantity);
        i.putExtra("DISHNAMES", nameOfDish);
        i.putExtra("PRICES", priceOfDish);
        i.putExtra("TIME", a.getSerializableExtra("TIME").toString());
        i.putExtra("Date", a.getSerializableExtra("Date").toString());

        startActivity(i);
    }


    public void friendOrders(View view) {
        Intent i = new Intent(this,FriendOrder.class);
        startActivity(i);
    }
}
