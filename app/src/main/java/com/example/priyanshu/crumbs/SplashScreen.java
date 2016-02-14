package com.example.priyanshu.crumbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final ImageView iv = (ImageView) findViewById(R.id.pizza);
        final ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setVisibility(View.INVISIBLE);
        Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.move_up);
        final Animation fade_in = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fade_in);
        final Animation fade_out = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fade_out);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(fade_out);
                logo.setVisibility(View.VISIBLE);
                logo.startAnimation(fade_in);
                finish();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
