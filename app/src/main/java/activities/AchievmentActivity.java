package activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mikhail.cubike.R;
import com.example.mikhail.cubike.adapters.AchievmentsAdapter;
import com.example.mikhail.cubike.adapters.CityAdapter;
import com.example.mikhail.cubike.model.AchievmentManager;

public class AchievmentActivity extends AppCompatActivity {
    private RecyclerView recyclerView_;
    private AchievmentsAdapter adapter_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievment);
        recyclerView_ = (RecyclerView)findViewById(R.id.achievments_recycler_view);
        recyclerView_ .setLayoutManager(new LinearLayoutManager(this));
        recyclerView_.setItemAnimator(new DefaultItemAnimator());

        adapter_ = new AchievmentsAdapter(AchievmentManager.getInstance().getAchievments(),R.layout.item_achievment,this);
        recyclerView_.setAdapter(adapter_);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_achievment, menu);
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
