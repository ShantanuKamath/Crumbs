package com.example.priyanshu.crumbs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalItem;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class Payment extends AppCompatActivity {

    private static PayPalConfiguration config = new PayPalConfiguration()

            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId("AdAwRc6yrVFSKQhC0YAXubLj7jCHP1mF2hmGvEuaq2vfYUwczZuOCG5OsPCbl260A5feK4LmYiy9VcAy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        Intent intentt = getIntent();
        int quantity[] = (int[]) intentt.getSerializableExtra("QUANTITIES");
        String[] nameOfDish = (String[]) intentt.getSerializableExtra("DISHNAMES");
        String[] priceOfDish = (String[]) intentt.getSerializableExtra("PRICES");


        String[] orderitems = new String[0];
        LinearLayout main = (LinearLayout) findViewById(R.id.orderSummary);
        main.setOrientation(LinearLayout.VERTICAL);
        double total=0.0;
        for (int i = 0; i < nameOfDish.length; i++)
        {
            if(quantity[i]!=0)
            {
                LinearLayout lw = new LinearLayout(this);
                lw.setOrientation(LinearLayout.HORIZONTAL);
                lw.setPadding(0,0,0,8);
                TextView dishName = new TextView(this);
                TextView qty = new TextView(this);
                TextView price = new TextView(this);

                dishName.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,6f));
                qty.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 3f));
                price.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 3f));

                dishName.setTextColor(Color.BLACK);
                dishName.setTextSize(20);

                qty.setTextColor(Color.BLACK);
                qty.setTextSize(20);
                qty.setGravity(Gravity.CENTER_HORIZONTAL);

                price.setTextColor(Color.BLACK);
                price.setTextSize(20);
                price.setGravity(Gravity.CENTER_HORIZONTAL);


                dishName.setText(nameOfDish[i]);
                qty.setText(""+quantity[i]);
                price.setText(""+quantity[i]*Double.parseDouble(priceOfDish[i].substring(priceOfDish[i].lastIndexOf(" "),priceOfDish[i].length())));

                total+=Double.parseDouble(price.getText().toString());
                lw.addView(dishName);
                lw.addView(qty);
                lw.addView(price);
                main.addView(lw);
            }

        }

        final double taxorder=0.07*total;
        final double forPaypal =total+taxorder;
        TextView orderTotal = (TextView) findViewById(R.id.total);
        TextView tax = (TextView) findViewById(R.id.tax);
        tax.setText(String.format("%.2f",taxorder));
        orderTotal.setText(String.format("%.2f", forPaypal));
        Button button = (Button) findViewById(R.id.pay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBuyPressed(""+forPaypal);
            }
        });


        Intent a = getIntent();
        TextView time = (TextView) findViewById(R.id.addressTime);
        time.setText("Address : Block 60, Hall 12,\n30 Nanyang Crescent,\nSingapore 637659.\nTime: " + a.getSerializableExtra("TIME").toString() + "\tDate: " + a.getSerializableExtra("Date").toString());
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    public void onBuyPressed(String amount) {

        // PAYMENT_INTENT_SALE will cause the payment to complete immediately.
        // Change PAYMENT_INTENT_SALE to
        //   - PAYMENT_INTENT_AUTHORIZE to only authorize payment and capture funds later.
        //   - PAYMENT_INTENT_ORDER to create a payment for authorization and capture
        //     later via calls from your server.

        PayPalPayment payment = new PayPalPayment(new BigDecimal(amount), "SGD", "Total Amount",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        PayPalPayment listitem = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE, Double.parseDouble(amount));
        // send the same configuration for restart resiliency
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, listitem);
        startActivityForResult(intent, 0);
    }
    private PayPalPayment getThingToBuy(String paymentIntent, double amount)
    {
        return new PayPalPayment(new BigDecimal(amount), "SGD", "Total Amount", paymentIntent);
    }

    private PayPalPayment getStuffToBuy(String paymentIntent) {
        //--- include an item list, payment amount details
        PayPalItem[] items =
                {
                        new PayPalItem("sample item #1", 2, new BigDecimal("87.50"), "SGD",
                                "sku-12345678"),
                        new PayPalItem("free sample item #2", 1, new BigDecimal("0.00"),
                                "SGD", "sku-zero-price"),
                        new PayPalItem("sample item #3 with a longer name", 6, new BigDecimal("37.99"),
                                "SGD", "sku-33333")
                };
        BigDecimal subtotal = PayPalItem.getItemTotal(items);
        BigDecimal shipping = new BigDecimal("1.5");
        BigDecimal tax = new BigDecimal("7");
        PayPalPaymentDetails paymentDetails = new PayPalPaymentDetails(shipping, subtotal, tax);
        BigDecimal amount = subtotal.add(shipping).add(tax);
        PayPalPayment payment = new PayPalPayment(amount, "SGD", "sample item", paymentIntent);
        payment.items(items).paymentDetails(paymentDetails);

        //--- set other optional fields like invoice_number, custom field, and soft_descriptor
        payment.custom("This is text that will be associated with the payment that the app can use.");

        return payment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("paymentExample", confirm.toJSONObject().toString(4));

                    //  send 'confirm' to your server for verification.
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.

                } catch (JSONException e) {
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("paymentExample", "The user canceled.");
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }
    }


}
