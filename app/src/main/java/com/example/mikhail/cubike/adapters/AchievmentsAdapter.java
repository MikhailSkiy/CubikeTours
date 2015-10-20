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
    private int skill_;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView skillDescription;
        public TextView skillName;
        public ImageView skillImage;


        public ViewHolder(View view) {
            super(view);
            skillName = (TextView) view.findViewById(R.id.skills_name);
            skillImage = (ImageView) view.findViewById(R.id.skills_image);
            skillDescription = (TextView) view.findViewById(R.id.skills_description);

        }

    }

    public AchievmentsAdapter(List<Achievment> achievments, int rowLayout, Context context, int skill) {
        this.skill_ = skill;
        this.context_ = context;
        this.rowLayout_ = rowLayout;
        this.achievments_ = achievments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout_, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.skillName.setText(achievments_.get(position).skill);
        holder.skillDescription.setText(achievments_.get(position).description);
        setStar(holder,skill_,position);
    }

    private void setStar(ViewHolder holder, int skill, int position) {
        switch (position) {
            case 0:
                if (isFreshman(skill)) {
                    setColorStar(holder);
                } else {
                    setGreyStar(holder);
                }
                break;

            case 1:
                if (isStudent(skill)){
                    setColorStar(holder);
                } else {
                    setGreyStar(holder);
                }
                break;

            case 2:
                if (isAmateur(skill)){
                    setColorStar(holder);
                } else {
                    setGreyStar(holder);
                }
                break;

            case 3:
                if (isPhot(skill)){
                    setColorStar(holder);
                } else {
                    setGreyStar(holder);
                }
                break;

            case 4:
                if (isProfi(skill)){
                    setColorStar(holder);
                } else {
                    setGreyStar(holder);
                }
                break;

            case 5:
                if (isPaparazi(skill)){
                    setColorStar(holder);
                } else {
                    setGreyStar(holder);
                }
                break;


            default:
                break;
        }
    }

    private boolean isFreshman(int skill) {
            return true;
    }

    private boolean isStudent(int skill) {
        if (skill > 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAmateur(int skill) {
        if (skill > 9) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isPhot(int skill) {
        if (skill > 14) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isProfi(int skill) {
        if (skill > 24) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isPaparazi(int skill) {
        if (skill > 35) {
            return true;
        } else {
            return false;
        }
    }

    private void setColorStar(ViewHolder holder) {
        holder.skillImage.setImageDrawable(context_.getResources().getDrawable(R.drawable.star));
    }

    private void setGreyStar(ViewHolder holder) {
        holder.skillImage.setImageDrawable(context_.getResources().getDrawable(R.drawable.star_grey));
    }


    @Override
    public int getItemCount() {
        return achievments_ == null ? 0 : achievments_.size();
    }
}
