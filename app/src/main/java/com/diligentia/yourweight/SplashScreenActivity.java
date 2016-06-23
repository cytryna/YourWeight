package com.diligentia.yourweight;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIME_OUT = 1000;
    private static final String PREF_UINT_SYSTEM = "unit_system";
    private static final String PREF_LOGIN = "pref_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Resources res = getResources();
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String animalValue = sharedPreferences.getString(PREF_UINT_SYSTEM, "");
        final boolean login = sharedPreferences.getBoolean(PREF_LOGIN, true);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Class cls;
                if (login) {
                    cls = LoginActivity.class;
                } else {
//                    cls = MainActivity.class;
                    cls = MainActivity.class;
                }
                Intent i = new Intent(SplashScreenActivity.this, cls);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

}