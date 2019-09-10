package com.example.clothing_app_mad;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    int noOfTabs;

    public PagerAdapter(FragmentManager fm, int numberofTabs){

        super(fm);
        this.noOfTabs = numberofTabs;
    }
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                All_items all_items = new All_items();
                return all_items;

            case 1:
                Gents gents = new Gents();
                return gents;

            case 2:
                Ladies ladies = new Ladies();
                return ladies;

            case 3:
                Kids kids = new Kids();
                return kids;

                 default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
