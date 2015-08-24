package com.example.mikhail.cubike.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mikhail.cubike.R;
import com.example.mikhail.cubike.model.City;

import java.util.List;

/**
 * Created by Mikhail on 23.08.2015.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context context_;
    private static List<City> cities_;
    private int rowLayout_;
    private static onCityItemListener listener_;

    public interface onCityItemListener {
        public void onClick(int id);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cityName;
        public ImageView cityImage;
        public TextView trackCountLabel;
        public TextView pointsOfInterestsLabel;
        public TextView tracksCount;
        public TextView pointsCount;
        public TextView cityDescription;

        public ViewHolder(View view) {
            super(view);
            cityName = (TextView) view.findViewById(R.id.city_name);
            cityImage = (ImageView) view.findViewById(R.id.city_image);
            trackCountLabel = (TextView) view.findViewById(R.id.track_count_label);
            pointsOfInterestsLabel = (TextView) view.findViewById(R.id.points_of_interests_label);
            tracksCount = (TextView) view.findViewById(R.id.tracks_count);
            pointsCount = (TextView) view.findViewById(R.id.points_of_interests_count);
           // cityDescription = (TextView) view.findViewById(R.id.city_description);

            cityImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        City city = cities_.get(position);
                        int id = city.getCityId();
                        if (listener_ != null) {
                            listener_.onClick(id);
                        }

                    }
                }
            });
        }
    }

    public CityAdapter(List<City> cities, int rowLayout, Context context, onCityItemListener listener) {
        this.cities_ = cities;
        this.rowLayout_ = rowLayout;
        this.context_ = context;
        this.listener_ = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout_, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Log.v("Adapter",Integer.toString(i));
        City city = cities_.get(i);
        viewHolder.cityName.setText(city.getName());
        if (city.getName() == "Taganrog") {
            viewHolder.cityImage.setImageDrawable(context_.getResources().getDrawable(R.drawable.taganrog));
        } else {
            viewHolder.cityImage.setImageDrawable(context_.getResources().getDrawable(R.drawable.rostov));
        }
        viewHolder.trackCountLabel.setText(context_.getResources().getString(R.string.track_count_label));
        viewHolder.pointsOfInterestsLabel.setText(context_.getResources().getString(R.string.points_of_interests_count_label));
        Log.v("Adapter", Integer.toString(city.getTracksCount()));
        viewHolder.tracksCount.setText(Integer.toString(city.getTracksCount()));

        viewHolder.pointsCount.setText(Integer.toString(city.getPointsCount()));
        Log.v("Adapter", Integer.toString(city.getPointsCount()));
        // viewHolder.cityDescription.setText(city.getDescription());
       // viewHolder.cityDescription.setText(context_.getResources().getString(R.string.taganrog_desc));

    }

    @Override
    public int getItemCount() {
        return cities_ == null ? 0 : cities_.size();
    }

}
