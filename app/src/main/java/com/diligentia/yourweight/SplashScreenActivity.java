package com.diligentia.yourweight;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Resources res = getResources();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Class cls;
                if (res.getBoolean(R.bool.withLogin)) {
                    cls = LoginActivity.class;
                } else {
                    cls = MainActivity.class;
                }
                Intent i = new Intent(SplashScreenActivity.this, cls);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

}