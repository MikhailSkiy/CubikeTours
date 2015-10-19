package com.example.mikhail.cubike.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mikhail.cubike.R;
import com.example.mikhail.cubike.model.Achievment;
import com.example.mikhail.cubike.utily.UtilMethods;

import java.util.List;

/**
 * Created by Mikhail Valuyskiy on 19.10.2015.
 */
public class AchievmentsAdapter extends RecyclerView.Adapter<AchievmentsAdapter.ViewHolder> {

    private Context context_;
    private static List<Achievment> achievments_;
    private int rowLayout_;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView skillName;
        public ImageView skillImage;

        public ViewHolder(View view){
            super(view);
            skillName = (TextView)view.findViewById(R.id.skills_name);
            skillImage = (ImageView)view.findViewById(R.id.skills_image);

        }

    }

    public AchievmentsAdapter(List<Achievment> achievments,int rowLayout,Context context){
        this.context_ = context;
        this.rowLayout_ = rowLayout;
        this.achievments_ = achievments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout_,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.skillName.setText(achievments_.get(position).skill);


  //  Bitmap bitmap = UtilMethods.getRoundedCroppedBitmap(UtilMethods.getBitmapFromBytes(UtilMethods.getBytesFromDrawable(context_.getResources().getDrawable(R.drawable.star))),50);
        holder.skillImage.setImageDrawable(context_.getResources().getDrawable(R.drawable.star));
    }


    @Override
    public int getItemCount() {
        return achievments_ == null ? 0 : achievments_.size();
    }
}
