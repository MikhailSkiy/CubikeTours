package activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mikhail.cubike.R;
import com.example.mikhail.cubike.database.DatabaseHelper;
import com.example.mikhail.cubike.model.Place;
import com.example.mikhail.cubike.utily.UtilMethods;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;



public class DetailsActivity extends AppCompatActivity {

    private DatabaseHelper helper_ = new DatabaseHelper(this);
    private int NOTIFICATION_ID = 0;


   // DatabaseHelper helper_;
    private CollapsingToolbarLayout collapsingToolbarLayout_;
    private int selectedPlaceId_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Gets selected place id
        Intent intent = getIntent();
        if (intent.getExtras()!=null) {
            Bundle extras = intent.getExtras();
            selectedPlaceId_ = extras.getInt("PlaceId", 0);
        }

        // Get info about place from database
        helper_ = new DatabaseHelper(this);
        Place selectedPlace = helper_.getPlaceById(selectedPlaceId_);

        // Get title, description and image of selected place
        String placeTitle="";
        String placeDescription="";
        byte[] placeImage = null;
        if (selectedPlace!=null) {
            placeTitle = selectedPlace.getTitle();
            placeDescription = selectedPlace.getFullDescription();
            placeImage = selectedPlace.getIcon();
        }

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        FloatingActionButton btn= (FloatingActionButton)findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(v);
            }
        });

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
            imageView.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        }


    }

    public void sendNotification(View view) {

        // BEGIN_INCLUDE(build_action)
        /** Create an intent that will be fired when the user clicks the notification.
         * The intent needs to be packaged into a {@link android.app.PendingIntent} so that the
         * notification service can fire it on our behalf.
         */

        Intent intent = new Intent(this,  DetailsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // END_INCLUDE(build_action)

        // BEGIN_INCLUDE (build_notification)
        /**
         * Use NotificationCompat.Builder to set up our notification.
         */
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        /** Set the icon that will appear in the notification bar. This icon also appears
         * in the lower right hand corner of the notification itself.
         *
         * Important note: although you can use any drawable as the small icon, Android
         * design guidelines state that the icon should be simple and monochrome. Full-color
         * bitmaps or busy images don't render well on smaller screens and can end up
         * confusing the user.
         */
        builder.setSmallIcon(R.drawable.place_ranevskaya);

        // Set the intent that will fire when the user taps the notification.
        builder.setContentIntent(pendingIntent);

        // Set the notification to auto-cancel. This means that the notification will disappear
        // after the user taps it, rather than remaining until it's explicitly dismissed.
        builder.setAutoCancel(true);

        /**
         *Build the notification's appearance.
         * Set the large icon, which appears on the left of the notification. In this
         * sample we'll set the large icon to be the same as our app icon. The app icon is a
         * reasonable default if you don't have anything more compelling to use as an icon.
         */
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.checkhov));

        /**
         * Set the text of the notification. This sample sets the three most commononly used
         * text areas:
         * 1. The content title, which appears in large type at the top of the notification
         * 2. The content text, which appears in smaller text below the title
         * 3. The subtext, which appears under the text on newer devices. Devices running
         *    versions of Android prior to 4.2 will ignore this field, so don't use it for
         *    anything vital!
         */
        builder.setContentTitle("BasicNotifications Sample");
        builder.setContentText("Time to learn about notifications!");
        builder.setSubText("Tap to view documentation about notifications.");

        // END_INCLUDE (build_notification)

        // BEGIN_INCLUDE(send_notification)
        /**
         * Send the notification. This will immediately display the notification icon in the
         * notification bar.
         */
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
        // END_INCLUDE(send_notification)
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
