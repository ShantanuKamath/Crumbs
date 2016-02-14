package com.example.priyanshu.crumbs;

import android.app.TabActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.HashMap;


public class TabBarActivity extends TabActivity implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {
    private SliderLayout mDemoSlider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_bar);

        TabHost tabHost = getTabHost();

        // setNewTab(context, tabHost, tag, title, icon, contentID);
        this.setNewTab(this, tabHost, "tab1", R.drawable.home, R.id.tab1);
        this.setNewTab(this, tabHost, "tab2", R.drawable.accountmultiple, R.id.tab2);
        this.setNewTab(this, tabHost, "tab3", R.drawable.plus, R.id.tab3);
        this.setNewTab(this, tabHost, "tab2", R.drawable.account, R.id.tab2);
        this.setNewTab(this, tabHost, "tab3", R.drawable.settings, R.id.tab3);

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
}