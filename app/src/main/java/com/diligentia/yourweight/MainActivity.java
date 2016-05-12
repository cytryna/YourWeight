package com.diligentia.yourweight;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.diligentia.domain.Item;
import com.diligentia.domain.ItemsAdapter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static com.diligentia.yourweight.MainActivity.SettingsEnum.CHART;
import static com.diligentia.yourweight.MainActivity.SettingsEnum.SETTINGS;
import static com.diligentia.yourweight.MainActivity.SettingsEnum.USER;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_LIGHT = "pref_light";
    private static final String PREF_WAHSING_MASHINE = "pref_washing_machine";
    private static final String PREF_NAME = "pref_name";
    private static final String PREF_ANIMALS = "pref_animals";
    private static final String PREF_ICE_CREAM_FLAVOURS = "pref_ice_cream_flavours";
    private static final String PREF_RINGTONE = "pref_ringtone";

    public static final String WEIGHT_DATA = "weightdata";
    public static final String DATABASE = "database" ;
    private final WeightRepository weightRepository = WeightRepository.getInstance();
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpreferences = getSharedPreferences(DATABASE, Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weightRepository.setSharedpreferences(sharedpreferences);
        LinkedList<Item> weightList = weightRepository.getWeightList();

        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ItemsAdapter adapter = new ItemsAdapter(this, weightList);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        boolean lightEnabled = sharedPreferences.getBoolean(PREF_LIGHT, false);
        boolean washingMachineEnabled = sharedPreferences.getBoolean(PREF_WAHSING_MASHINE, false);
        String nameValue = sharedPreferences.getString(PREF_NAME, "");
        String animalValue = sharedPreferences.getString(PREF_ANIMALS, "");
        Set<String> iceCreamFlavourValues = sharedPreferences.getStringSet(PREF_ICE_CREAM_FLAVOURS, null);
        String ringtoneValue = sharedPreferences.getString(PREF_RINGTONE, "");

        String lightValue = (lightEnabled) ? "włączone" : "wyłączone";
        String washingMachineValue = (washingMachineEnabled) ? "włączona" : "wyłączona";


        System.err.println(lightValue);
//        washingMachine.setText(washingMachineValue);
//        name.setText(nameValue);
//        animal.setText(animalValue);
//        iceCreamFlavour.setText(iceCreamFlavourValues.toString());
//        ringtone.setText(ring0toneValue);


// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Wybrano element " + position + ", czyli " , Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(adapter);

        Button ok = (Button) findViewById(R.id.plusButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.MainActivity_insertTodayWeighht), Toast.LENGTH_SHORT).show();
                weightRepository.addWeight(new Item(new Date(), new BigDecimal(113)));

//                sharedpreferences.edit();


                finish();
                startActivity(getIntent());
//                Intent intent = new Intent(getApplicationContext(), ChartActivity.class);
//                startActivity(intent);
            }
        });
    }



    private void addDrawerItems() {
        String[] osArray = {USER.getName(), CHART.getName(), SETTINGS.getName()};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
                Intent intent;
                if (position == SETTINGS.getPosition()) {
                    intent = new Intent(getApplicationContext(), PreferenceAppActivity.class);
                } else if (position == CHART.getPosition()) {
                    intent = new Intent(getApplicationContext(), ChartActivity.class);
                } else {
                    intent = new Intent(getApplicationContext(), SplashScreenActivity.class);// TODO: 10.05.16 Activity o mnie
                }
                startActivity(intent);
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    enum SettingsEnum {
        USER("User", 0),
        CHART("Chart", 1),
        SETTINGS("Settings", 2);

        private String name;
        private final int position;

        SettingsEnum(String name, int position) {
            this.name = name;
            this.position = position;
        }

        public final int getPosition() {
            return position;
        }

        public String getName() {
            return name;
        }
    }
}