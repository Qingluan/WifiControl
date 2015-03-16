package com.qingluan.darkh.wificontroll.Widgets;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.qingluan.darkh.wificontroll.R;

import java.util.Locale;

/**
 * Created by darkh on 3/13/15.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    Context context;

    int SectionsPagesNumber = 0;

    ViewPager mViewPager;

    public SectionsPagerAdapter(ViewPager viewPager,FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
        this.mViewPager = viewPager;
    }

    public SectionsPagerAdapter(ViewPager viewPager,FragmentManager fm,Context context,int Pages) {
        super(fm);
        this.context = context;
        this.SectionsPagesNumber = Pages;
        this.mViewPager = viewPager;
    }


    @Override
    public Fragment getItem(int position) {
        return  PlaceholderFragment.newInstance(position,context,mViewPager);
    }

    @Override
    public int getCount() {
        return this.SectionsPagesNumber;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {

            case 0:
                return context.getString(R.string.title_section1);
            case 1:
                return context.getString(R.string.title_section2);
            case 2:
                return context.getString(R.string.title_section3);
            case 3:
                return context.getString(R.string.title_section4);
        }
        return null;
    }


    

}



