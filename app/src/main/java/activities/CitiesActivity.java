package activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mikhail.cubike.R;
import com.example.mikhail.cubike.adapters.CityAdapter;
import com.example.mikhail.cubike.model.CityManager;


public class CitiesActivity extends ActionBarActivity {

    private RecyclerView recyclerView_;
    private CityAdapter cityAdapter_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        recyclerView_ = (RecyclerView)findViewById(R.id.cities_recycler_view);
        recyclerView_ .setLayoutManager(new LinearLayoutManager(this));
        recyclerView_.setItemAnimator(new DefaultItemAnimator());

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
