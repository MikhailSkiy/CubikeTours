package activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mikhail.cubike.R;
import com.example.mikhail.cubike.adapters.TrackAdapter;
import com.example.mikhail.cubike.interfaces.UIactions;
import com.example.mikhail.cubike.managers.RequestManager;
import com.example.mikhail.cubike.model.Preview;
import com.example.mikhail.cubike.model.Track;
import com.example.mikhail.cubike.utily.UtilMethods;

import java.util.ArrayList;
import java.util.List;


public class TracksActivity extends FragmentActivity implements UIactions{

    private List<Preview> previews_ = new ArrayList<>();
    private  ListView listView_;
    private Context context_;
    private RequestManager manager_ = new RequestManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView_ = (ListView) this.findViewById(R.id.track_list);

       // manager_.getTracks();

        // Create track
        int id = 8;
        int duration = 20;
        int length = 20;
        String title = "Welcome to taganrog";
        String description = "Some description";
        int rating = 5;
       // byte [] icon = UtilMethods.getBytesFromDrawable(getResources().getDrawable(R.drawable.round_place_ranevskya));
        byte [] icon = UtilMethods.getBytesFromDrawable(getResources().getDrawable(R.drawable.place_ranevskaya));
        Track track = new Track(id,title,description,duration,length,rating,icon);
        Track track2 = new Track(id,title,description,duration,length,rating,UtilMethods.getBytesFromDrawable(getResources().getDrawable(R.drawable.place_park)));

        Preview preview = new Preview(track.getTrackId(),track.getTitle(),track.getDescription(),track.getDuration(),track.getLength(),track.getIcon());
        Preview preview2 = new Preview(track2.getTrackId(),track2.getTitle(),track2.getDescription(),track2.getDuration(),track2.getLength(),track2.getIcon());
        previews_.add(preview);
        previews_.add(preview2);



        listView_.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("Position", Integer.toString(position));
                int itemId = previews_.get(position).getId_();

                Intent intent = new Intent(TracksActivity.this, MapActivity.class);
                intent.putExtra("SelectedItemId", itemId);
                startActivity(intent);
            }
        });


        updateUI(previews_);
    }

    @Override
    public void updateUI(List<Preview> items) {
        previews_ = items;
        TrackAdapter trackAdapter = new TrackAdapter(this,items);
        listView_.setAdapter(trackAdapter);
    }

    @Override
    public Context getClientActivityContext() {
        return this;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
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
        if (id == R.id.action_update) {
           manager_.sendAsyncTracksRequest();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
