package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mikhail.cubike.R;
import com.example.mikhail.cubike.database.DatabaseHelper;
import com.example.mikhail.cubike.model.Place;
import com.example.mikhail.cubike.utily.UtilMethods;



public class DetailsActivity extends AppCompatActivity {

    private DatabaseHelper helper_ = new DatabaseHelper(this);

   // DatabaseHelper helper_;
    private CollapsingToolbarLayout collapsingToolbarLayout_;
    private int selectedPlaceId_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Gets selected place id
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        selectedPlaceId_ = extras.getInt("PlaceId", 0);

        // Get info about place from database
        helper_ = new DatabaseHelper(this);
        Place selectedPlace = helper_.getPlaceById(selectedPlaceId_);




        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get title, description and image of selected place
        String placeTitle = selectedPlace.getTitle();
        String placeDescription = selectedPlace.getFullDescription();
        byte[] placeImage = selectedPlace.getIcon();




        // Set values to UI
        collapsingToolbarLayout_ = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);

        // Set values to collapsingToolBar
       // collapsingToolbarLayout_.setTitle(placeTitle);
        collapsingToolbarLayout_.setExpandedTitleColor(getResources().getColor(R.color.background_floating_material_dark));
        collapsingToolbarLayout_.setCollapsedTitleTextColor(getResources().getColor(R.color.accent_material_dark));

        // Set values to textViews (Title,Description)
        TextView cardTitle = (TextView)findViewById(R.id.title);
        cardTitle.setText(placeTitle);
        TextView cardFullDescription = (TextView) findViewById(R.id.description);
        cardFullDescription.setText(placeDescription);

        // Set value to image
        ImageView imageView = (ImageView)findViewById(R.id.image);
        if (selectedPlace.getId_() == 1){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.place_square));
        }
        if (selectedPlace.getId_() == 2) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.rane));
        }

        if (selectedPlace.getId_() == 3){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.kobilin_home));
        }

        if (selectedPlace.getId_() == 4){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ladohin));
        }

        if (selectedPlace.getId_() == 5) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.theater));
        }

        if (selectedPlace.getId_() == 6){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.library));
        }
        if (selectedPlace.getId_() == 7){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.park));
        }

        if (selectedPlace.getId_() == 8){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.marin_school));
        }

        if (selectedPlace.getId_() == 9){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.court));
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
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
