package com.example.mikhail.cubike.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
public class PlacesAdapter extends ArrayAdapter<Preview>{

    private final int VIEW_TYPE_SELECTED = 0;
    private final int VIEW_TYPE_OTHERS = 1;

    List<Preview> values_;

    private static class ViewHolder{
        TextView titleText_;
        TextView descriptionText_;
        ImageView icon_;
    }



    public PlacesAdapter(Context context,List<Preview> values){
        super(context,R.layout.place_list_item,values);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.place_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.titleText_ = (TextView) convertView.findViewById(R.id.place_title);
            viewHolder.descriptionText_ = (TextView) convertView.findViewById(R.id.place_description);

            viewHolder.icon_ = (ImageView) convertView.findViewById(R.id.place_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.titleText_.setText(getItem(position).getTitle_());
        viewHolder.descriptionText_.setText(getItem(position).getDescription_());

        if (getItem(position).getIcon_() != null ){
            Bitmap bitmap = BitmapFactory.decodeByteArray(getItem(position).getIcon_(), 0, getItem(position).getIcon_().length);
            int width = bitmap.getWidth();
          Bitmap bmp1 = UtilMethods.getRoundedCroppedBitmap(BitmapFactory.decodeByteArray(getItem(position).getIcon_(), 0, getItem(position).getIcon_().length),width);
           // Bitmap bmp1 = BitmapFactory.decodeByteArray(getItem(position).getIcon_(), 0, getItem(position).getIcon_().length);
            viewHolder.icon_.setImageBitmap(bmp1);
        }



//        //TODO Delete
//        if (position==0) {
//            viewHolder.icon_.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.octsquare));
//        }
//        if (position ==1){
//            viewHolder.icon_.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.ranevskaya));
//        }
//        if (position ==2){
//            viewHolder.icon_.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.kobilin));
//        }
//        if (position ==3){
//            viewHolder.icon_.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.ladohin));
//        }
//        if (position ==4){
//            viewHolder.icon_.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.chekhovteatr));
//        }
//        if (position ==5){
//            viewHolder.icon_.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.lib));
//        }


        return convertView;
    }
}
