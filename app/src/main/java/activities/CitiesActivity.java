package activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mikhail.cubike.R;
import com.example.mikhail.cubike.adapters.CityAdapter;
import com.example.mikhail.cubike.database.DatabaseHelper;
import com.example.mikhail.cubike.model.CityManager;
import com.example.mikhail.cubike.model.Track;
import com.example.mikhail.cubike.utily.UtilMethods;

import java.io.IOException;
import java.util.List;


public class CitiesActivity extends ActionBarActivity {

    private RecyclerView recyclerView_;
    private CityAdapter cityAdapter_;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mainNavigationMenu_;
    private static Context context_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        recyclerView_ = (RecyclerView)findViewById(R.id.cities_recycler_view);
        recyclerView_ .setLayoutManager(new LinearLayoutManager(this));
        recyclerView_.setItemAnimator(new DefaultItemAnimator());

        context_ = this.getApplicationContext();

       mainNavigationMenu_ = (NavigationView) findViewById(R.id.main_drawer);

//        mToolbar = (Toolbar) findViewById(R.id.app_bar);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_activity_drawer_layout);
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        mDrawerToggle.syncState();

        mainNavigationMenu_.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    // Show all movies
                    case R.id.achieves_menu_btn:
                        Intent intent = new Intent(CitiesActivity.this, AchievmentActivity.class);
                        startActivity(intent);
                        break;

                    default:
                        break;
                }
                return true;
            }
        });



       // setSupportActionBar(mToolbar);

       DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.openDataBase();
        List<Track> trackList = dbHelper.getAllTracks();



        cityAdapter_ = new CityAdapter(CityManager.getInstance().getCities(), R.layout.item_city, this,
                new CityAdapter.onCityItemListener() {
                    @Override
                    public void onClick(int id) {
                        Intent intent = new Intent(CitiesActivity.this,TracksActivity.class);
                        intent.putExtra("SelectedItemId",id);
                        startActivity(intent);
                    }
                });

        recyclerView_.setAdapter(cityAdapter_);
    }

    public static Context getContext(){
        return context_;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cities, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
