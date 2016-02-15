package com.example.priyanshu.crumbs;

import android.app.DatePickerDialog;
import android.app.TabActivity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


public class TabBarActivity extends TabActivity implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {
    private SliderLayout mDemoSlider;
    final Calendar myCalendar = Calendar.getInstance();
    public static TextView restName, numFriends;
    public static ImageView restImg;
    public static TextView time;
    public static TextView datee;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_bar);

        TextView hello= (TextView) findViewById(R.id.helloUser);
        String helloname=ParseUser.getCurrentUser().getString("name");
        int i=helloname.indexOf(" ");
        helloname=helloname.substring(0, i);
        hello.setText("Hello, "+helloname);
        TabHost tabHost = getTabHost();

        // setNewTab(context, tabHost, tag, title, icon, contentID);
        this.setNewTab(this, tabHost, "tab1", R.drawable.home, R.id.tab1);
        this.setNewTab(this, tabHost, "tab2", R.drawable.accountmultiple, R.id.tab2);
        this.setNewTab(this, tabHost, "tab3", R.drawable.plus, R.id.tab3);
        this.setNewTab(this, tabHost, "tab4", R.drawable.account, R.id.tab4);
        this.setNewTab(this, tabHost, "tab5", R.drawable.settings, R.id.tab5);

        //tabHost.setCurrentTabByTag("tab2"); //-- optional to set a tab programmatically.

        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("@Mia Pizzeria: 15% off on medium-sized pizzas",R.drawable.e);
        file_maps.put("@Capricci: 1 for 1 weekend offer",R.drawable.c);
        file_maps.put("@The Living Café: BLT meal for $15 only",R.drawable.a);
        file_maps.put("@Sam's Deli: Grilled steak sandwich @$14", R.drawable.b);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Stack);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);




        // DATE AND TIME
        TextView dateText = (TextView) findViewById(R.id.date_text);

        TextView timeText = (TextView
                ) findViewById(R.id.time_text);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(view);
            }

        };

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(TabBarActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //////
        final TimePickerDialog.OnTimeSetListener t =new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay,
                                  int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);
                updateLabel(view);
            }
        };

        timeText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(TabBarActivity.this,
                        t,
                        myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE),
                        true).show();
            }
        });


 /////// SHOW FRIENDS

        ParseUser user = ParseUser.getCurrentUser();
        ArrayList<String> Logs = (ArrayList<String>) user.get("friends");
        if(Logs==null)
            Logs = new ArrayList<>();
        ArrayAdapter<String> mLogsAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, Logs) {
            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.BLACK);
                return view;
            }
        };
        ListView listView = (ListView) findViewById(R.id.lv_show_friends);
        listView.setAdapter(mLogsAdapter);


//////////// Floating add friends button.
        ImageButton FAB = (ImageButton) findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddFriends.class);
                startActivity(intent);
            }
        });
////////

        restName= (TextView) findViewById(R.id.rest_text);
        restImg  = (ImageView) findViewById(R.id.order_rest_img);
        numFriends = (TextView) findViewById(R.id.numFriends);
        time= (TextView) findViewById(R.id.time_text);
        datee=(TextView) findViewById(R.id.date_text);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private void setNewTab(Context context, TabHost tabHost, String tag, int icon, int contentID ){
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(getTabIndicator(tabHost.getContext(),icon));
        tabSpec.setContent(contentID);
        tabHost.addTab(tabSpec);
    }
    private View getTabIndicator(Context context, int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        iv.setImageResource(icon);
        return view;
    }


    private void updateLabel(View v) {
        TextView dateText = (TextView) findViewById(R.id.date_text);
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateText.setText(sdf.format(myCalendar.getTime()));

        TextView timeText = (TextView) findViewById(R.id.time_text);
        myFormat = "HH:mm";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        timeText.setText(sdf.format(myCalendar.getTime()));
        String dateinText= dateText.getText().toString()+" "+timeText.getText().toString();
        Log.d("party", dateinText);
//        party.put("Date", dateinText);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
    public void logout (View v)
    {
        ParseUser.logOut();
//        ParseLoginBuilder builder = new ParseLoginBuilder(getApplicationContext());
//        startActivityForResult(builder.build(), 0);
//        Log.v("Bobey", ParseUser.getCurrentUser().get("name").toString());
//        finish();
        Parse.initialize(this);
        ParseFacebookUtils.initialize(this);
    }

    public void openrest(View view) {
        Intent i= new Intent(this, ListRestaurant.class);
        startActivity(i);
    }

    public void openfriends(View view) {
        Intent in= new Intent(this, AddFriends.class);
        startActivity(in);

    }

    public void guestlist(View view) {
        Intent in= new Intent(this, GuestList.class);
        startActivity(in);
    }

    public void openPayment(View view) {
        Intent in= new Intent(this, MenuSelect.class);
        in.putExtra("RESTNAME", restName.getText().toString());
        in.putExtra("TIME", time.getText().toString());
        in.putExtra("Date", datee.getText().toString());
        Log.v("BOBEY", restName.getText().toString());

        startActivity(in);
    }

    public void showfriends(View view) {
        Intent in = new Intent(this,ShowFriends.class);
        startActivity(in);
    }

    public void chooseRestaurant(View view) {
        Intent i =new Intent(this, ListRestaurant.class);
        startActivity(i);

    }


}