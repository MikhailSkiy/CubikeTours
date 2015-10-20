package com.example.mikhail.cubike.model;

import com.example.mikhail.cubike.R;

import java.util.ArrayList;
import java.util.List;

import activities.CitiesActivity;

/**
 * Created by Mikhail Valuyskiy on 19.10.2015.
 */
public class AchievmentManager {
  //  private static String[] skillsArray_ = {CitiesActivity.getContext().getResources().getString(R.string.freshman),"Amateur"};
    private static String[] skillsArray_ = {CitiesActivity.getContext().getResources().getString(R.string.freshman),
                                            CitiesActivity.getContext().getResources().getString(R.string.student),
                                            CitiesActivity.getContext().getResources().getString(R.string.amatour),
                                            CitiesActivity.getContext().getResources().getString(R.string.photo),
                                            CitiesActivity.getContext().getResources().getString(R.string.profi),
                                            CitiesActivity.getContext().getResources().getString(R.string.paparazzi)};

    private static String[] skillsDescArray_ = {CitiesActivity.getContext().getResources().getString(R.string.freshman_desc),
            CitiesActivity.getContext().getResources().getString(R.string.student_desc),
            CitiesActivity.getContext().getResources().getString(R.string.amatour_desc),
            CitiesActivity.getContext().getResources().getString(R.string.photo_desc),
            CitiesActivity.getContext().getResources().getString(R.string.profi_desc),
            CitiesActivity.getContext().getResources().getString(R.string.paparazzi_desc)};

    private static AchievmentManager instance_;
    private List<Achievment> achievmentList_;

    public static AchievmentManager getInstance(){
        if (instance_ == null){
            instance_ = new AchievmentManager();
        }
        return instance_;
    }

    public List<Achievment> getAchievments(){
        if (achievmentList_ == null){
            achievmentList_ = new ArrayList<Achievment>();

            for (int i=0;i<skillsArray_.length;i++){
                Achievment achievment = new Achievment();
                achievment.skill = skillsArray_[i];
                achievment.description = skillsDescArray_[i];
                achievmentList_.add(achievment);
            }
        }
        return achievmentList_;
    }
}
