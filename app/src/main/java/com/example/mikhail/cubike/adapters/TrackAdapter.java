package com.example.mikhail.cubike.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mikhail.cubike.R;
import com.example.mikhail.cubike.model.Preview;
import com.example.mikhail.cubike.utily.UtilMethods;

import java.util.List;

/**
 * Created by Mikhail on 19.07.2015.
 */
public class TrackAdapter extends ArrayAdapter<Preview>{

    private final int VIEW_TYPE_CURRENT_CITY= 0;
    private final int VIEW_TYPE_TRACKS = 1;

    private static class ViewHolder{
        TextView titleText_;
        TextView descriptionText_;
        TextView durationText_;
        TextView lengthText_;
        ImageView icon_;
        ImageView duration_;
        ImageView rating_;
        ImageView length_;
    }

    public TrackAdapter(Context context,List<Preview> values){
        super(context,R.layout.track_list_item,values);
    }

    @Override
   public int getItemViewType(int position){
        return (position == 0) ? VIEW_TYPE_CURRENT_CITY : VIEW_TYPE_TRACKS;
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }

//    @Override
//    public View newView(Context context, List<Track> values,ViewGroup parent){
//
//    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        ViewHolder viewHolder;


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.track_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.titleText_ = (TextView) convertView.findViewById(R.id.track_title);
            viewHolder.descriptionText_ = (TextView) convertView.findViewById(R.id.track_description);
            viewHolder.durationText_ = (TextView) convertView.findViewById(R.id.track_duration);
            viewHolder.lengthText_ = (TextView) convertView.findViewById(R.id.track_length);
            viewHolder.icon_ = (ImageView) convertView.findViewById(R.id.track_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.titleText_.setText(getItem(position).getTitle_());
        viewHolder.descriptionText_.setText(getItem(position).getDescription_());
        viewHolder.durationText_.setText(Double.toString(getItem(position).getDuration_()));
        viewHolder.lengthText_.setText(Integer.toString(getItem(position).getLength_()));

        if (getItem(position).getIcon_()!= null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(getItem(position).getIcon_(), 0, getItem(position).getIcon_().length);
            int width = bitmap.getWidth();
            Bitmap bmp1 = UtilMethods.getRoundedCroppedBitmap(BitmapFactory.decodeByteArray(getItem(position).getIcon_(), 0, getItem(position).getIcon_().length), width);

          //  Drawable a = getContext().getResources().getDrawable(R.drawable.round_place_ranevskya);
           // viewHolder.icon_.setImageDrawable(a);

            viewHolder.icon_.setImageBitmap(bmp1);
        }

        return convertView;
    }
}
