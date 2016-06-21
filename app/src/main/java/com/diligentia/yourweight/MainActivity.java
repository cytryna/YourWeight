package com.diligentia.yourweight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.Date;
import java.util.Set;

import static com.diligentia.yourweight.MainActivity.SettingsEnum.CHART;
import static com.diligentia.yourweight.MainActivity.SettingsEnum.DELETE_ITEMS;
import static com.diligentia.yourweight.MainActivity.SettingsEnum.SETTINGS;
import static com.diligentia.yourweight.MainActivity.SettingsEnum.USER;

public class MainActivity extends AppCompatActivity {

    // TODO: 12.05.16  mniejszy plus przycisk
    // TODO: 12.05.16 dodac wygodne wprowadzanie wagi
    // TODO: 12.05.16 umożliwić rejestrację nowych użytkowników
    // TODO: 15.06.16 Usuń wszystkie dane przetestuj dodwanie kolejnych wag

    private Repository repository;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        repository = Repository.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ItemsAdapter adapter = new ItemsAdapter(this, repository.getWeightList());


        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),
//                        "Wybrano element " + position + ", czyli " , Toast.LENGTH_SHORT).show();
//            }
//        });

        Button ok = (Button) findViewById(R.id.plusButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), getString(R.string.MainActivity_insertTodayWeighht), Toast.LENGTH_SHORT).show();
//                repository.addWeight(new Item(new Date(), new BigDecimal(93)));

//                sharedpreferences.edit();


//                finish();
//                startActivity(getIntent());
                Intent intent = new Intent(getApplicationContext(), PickerActivity.class);
                startActivity(intent);
            }
        });
    }



    private void addDrawerItems() {
        String[] osArray = {USER.getName(), CHART.getName(), SETTINGS.getName(), DELETE_ITEMS.getName()};
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
                } else if (position == DELETE_ITEMS.getPosition()) {
                    repository.deleteWeight();
                    intent = new Intent(getApplicationContext(), MainActivity.class);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    enum SettingsEnum {
        USER("User", 0),
        CHART("Chart", 1),
        SETTINGS("Settings", 2),
        DELETE_ITEMS("Delete items", 3);

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